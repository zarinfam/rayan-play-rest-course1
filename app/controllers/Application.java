package controllers;

import models.Post;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

    public static List<Post> postList = new ArrayList<>(Arrays.asList(
            new Post(1l, "Sample post", "Sample post content", new Date())
            ,new Post(2l, "Very hot post", "Very hot post content", new Date())
            ,new Post(3l, "Short post", "Short post content", new Date())
    ));

    public static Result listPosts() {
        return ok(Json.toJson(postList));
    }

}
