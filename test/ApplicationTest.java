import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import controllers.Application;
import models.Post;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.ConsoleTextArea;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;

import java.io.IOException;
import java.util.Date;
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

    @Test
    public void createPost_checkPostJsonStructure_addPostSuccessFully() {

        assertThat(Application.postList.size()).isEqualTo(3);

        start(fakeApplication());

        Post post = new Post(4l, "Test post", "Test post content", new Date());

        Result result = route(fakeRequest(POST, "/posts").withJsonBody(Json.toJson(post)));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);

        assertThat(Application.postList.size()).isEqualTo(4);

        Post createdPost = Application.postList.stream()
                .filter(p -> p.getId() == 4l).findFirst().orElse(new Post(-1l));

        assertThat(createdPost.getId()).isEqualTo(4l);
    }



}
