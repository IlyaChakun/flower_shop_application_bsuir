package by.bsuir.service;

import by.bsuir.dto.FloristDTO;
import by.bsuir.dto.FloristRequestDTO;
import by.bsuir.dto.mapper.FloristMapperDTO;
import by.bsuir.dto.user.UserDTO;
import by.bsuir.entity.Florist;
import by.bsuir.remoteclients.AccountClient;
import by.bsuir.repository.FloristRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class FloristServiceImpl implements FloristService {

    private final FloristMapperDTO floristMapper;
    private final FloristRepository floristRepository;

    private final AccountClient accountClient;

    @Override
    @Transactional
    public FloristDTO save(FloristRequestDTO floristRequestDTO) {

        log.info("in save method florist service");

        UserDTO userDTO = accountClient.register(floristRequestDTO.getUserSignUpRequest());

        log.info("Userid={}", userDTO.getId());

        Florist florist = getFlorist(floristRequestDTO, userDTO.getId());

        Florist added = floristRepository.save(florist);

        return floristMapper.toDto(added);
    }

    private Florist getFlorist(final FloristRequestDTO floristRequestDTO, final Long userId) {
        Florist florist = new Florist();
        florist.setUserId(userId);
        florist.setShopId(floristRequestDTO.getShopId());
        florist.setExperience(floristRequestDTO.getExperience());

        return florist;
    }
}
