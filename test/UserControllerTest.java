import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import controllers.UserController;
import daos.UserDao;
import models.Post;
import models.User;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import services.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class UserControllerTest {

    @Test
    public void listUsers_getAllUserValues_ReturnListOfUsers() throws IOException {
        GlobalTest globalTest = new GlobalTest();

        start(fakeApplication(globalTest));

        globalTest.applicationContext.getBean(UserService.class).addUser(new User("Ali"));
        globalTest.applicationContext.getBean(UserService.class).addUser(new User("Mohammad"));

        Result result = route(fakeRequest(GET, "/users"));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);

        List<User> users = new ObjectMapper().readValue(contentAsString(result),
                TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));

        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void createUser_addUser_ReturnCreatedUser() throws IOException {
        GlobalTest globalTest = new GlobalTest();

        start(fakeApplication(globalTest));

        assertThat(globalTest.applicationContext.getBean(UserService.class).findAllUser().size()).isEqualTo(0);

        Result result = route(fakeRequest(POST, "/users")
                .withJsonBody(Json.toJson(new User("Ali"))));

        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);

        User user = Json.fromJson(Json.parse(contentAsString(result)), User.class);

        List<User> allUser = globalTest.applicationContext.getBean(UserService.class).findAllUser();

        assertThat(allUser.size()).isEqualTo(1);

        assertThat(allUser.get(0).getId()).isNotNull();
        assertThat(allUser.get(0).getUserName()).isEqualTo("Ali");

    }

    @Test
    public void getUser_getSpecificUser_ReturnAUser() throws IOException {
        GlobalTest globalTest = new GlobalTest();

        start(fakeApplication(globalTest));

        globalTest.applicationContext.getBean(UserService.class).addUser(new User("Ali"));
        User sentUser = globalTest.applicationContext.getBean(UserService.class).addUser(new User("Mohammad"));
        globalTest.applicationContext.getBean(UserService.class).addUser(new User("Javad"));

        Result result = route(fakeRequest(GET, "/users/"+sentUser.getId()));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);

        User returnedUser = Json.fromJson(Json.parse(contentAsString(result)), User.class);

        assertThat(returnedUser.getId()).isEqualTo(sentUser.getId());
        assertThat(returnedUser.getUserName()).isEqualTo(sentUser.getUserName());

    }

}
