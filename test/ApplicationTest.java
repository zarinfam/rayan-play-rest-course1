import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import models.Post;
import org.junit.Test;
import play.mvc.Result;

import java.io.IOException;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class ApplicationTest {

    @Test
    public void listPosts_getAllPostValues_ReturnList() throws IOException {
        start(fakeApplication());

        Result result = route(fakeRequest(GET, "/posts"));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);

        List<Post> posts = new ObjectMapper().readValue(contentAsString(result),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));

        assertThat(posts.size()).isEqualTo(3);
    }


}
