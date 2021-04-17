package by.bsuir.service.impl;


import by.bsuir.dto.mapper.florist.FloristMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.dto.model.florist.FloristRequestDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.email.service.core.EmailSenderService;
import by.bsuir.entity.florist.Florist;
import by.bsuir.entity.florist.FloristStatistic;
import by.bsuir.entity.user.User;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.florist.FloristRepository;
import by.bsuir.repository.api.user.UserRepository;
import by.bsuir.security.service.api.UserSecurityService;
import by.bsuir.service.api.FloristService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class FloristServiceImpl implements FloristService {

    private final FloristMapperDTO floristMapper;
    private final FloristRepository floristRepository;

    private final UserSecurityService accountClient;
    private final CommonServiceHelper commonServiceHelper;

    private final UserRepository userRepository;//TODO

    private final EmailSenderService emailService;

    @Override
    @Transactional
    public FloristDTO save(FloristRequestDTO floristRequestDTO) {

        log.info("in save method florist service");
        log.info("florist {}", floristRequestDTO);

        UserDTO userDTO = accountClient.registerUser(floristRequestDTO.getUserSignUpRequest());

        log.info("Userid={}", userDTO.getId());

        User user = userRepository.getOne(userDTO.getId());

        Florist florist = getFlorist(floristRequestDTO, user);
        FloristStatistic floristStatistic = new FloristStatistic();
        floristStatistic.setFloristRatingSum(0D);
        floristStatistic.setCompletedOrdersCount(0);
        floristStatistic.setFloristRatingSum(0D);
        florist.setFloristStatistic(floristStatistic);

        Florist added = floristRepository.save(florist);

        return floristMapper.toDto(added);
    }


    @Override
    @Transactional
    public FloristDTO update(FloristDTO floristDTO) {
        log.info("in update method florist service");
        log.info("florist {}", floristDTO);

        Florist florist = getFloristOrThrowException(floristDTO.getId());

        florist.setExperience(floristDTO.getExperience());
        florist.setSalary(floristDTO.getSalary());
        florist.getUser().setPhoneNumber(floristDTO.getUser().getPhoneNumber());

        return floristMapper.toDto(florist);
    }

    @Override
    @Transactional
    public FloristDTO findById(Long id) {

        log.info("in findById method florist service id={}", id);
        Florist florist = getFloristOrThrowException(id);

        return floristMapper.toDto(florist);
    }

    @Override
    public PageWrapper<FloristDTO> findAll(int page, int size) {
        log.info("in findAll method florist service page={} size={}", page, size);

        Pageable pageable = commonServiceHelper.getPageable(page, size);

        Page<Florist> florists = floristRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        floristMapper.toDtoList(florists.toList()),
                        florists.getTotalPages(),
                        florists.getTotalElements());
    }

    private Florist getFloristOrThrowException(final Long id) {
        return floristRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("Florist with id={} not found!", id);
                            return new ResourceNotFoundException("Florist with id=" + id + " not found!");
                        }
                );
    }

    private Florist getFlorist(final FloristRequestDTO floristRequestDTO, final User user) {
        Florist florist = new Florist();
        florist.setUser(user);
        florist.setShopId(floristRequestDTO.getShopId());
        florist.setExperience(floristRequestDTO.getExperience());
        florist.setSalary(floristRequestDTO.getSalary());

        return florist;
    }


}
