package by.bsuir.entity.user.token;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.user.AbstractUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_confirmation_tokens")
@Getter
@Setter
@NoArgsConstructor
public class UserConfirmationToken extends AbstractEntity {

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @OneToOne(targetEntity = AbstractUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AbstractUser user;

    public UserConfirmationToken(AbstractUser user) {
        this.user = user;
        confirmationToken = UUID.randomUUID().toString();
    }

}
