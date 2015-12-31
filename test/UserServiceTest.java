import configs.AppConfig;
import models.User;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import services.UserService;

import javax.inject.Inject;

import static org.fest.assertions.Assertions.assertThat;


@ContextConfiguration(classes={AppConfig.class, DataConfigTest.class})
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject
    private UserService userService;

    @Test
    public void createUser_() {
        assertThat(userService.findAllUser().size()).isEqualTo(0);
        User user = new User("Saeed");
        userService.addUser(user);

        assertThat(user.getId()).isNotNull();
        assertThat(userService.findAllUser().size()).isEqualTo(1);
    }


}