package by.bsuir.repository.api.user;

import by.bsuir.entity.user.AbstractUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository extends AbstractUserBaseRepository<AbstractUser> {

}
