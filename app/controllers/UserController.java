package controllers;

import models.Post;
import models.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
public class UserController {

    @Inject
    private UserService userService;

    public Result listUsers() {
        return Results.ok(Json.toJson(userService.findAllUser()));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result createUser() {
        User user = Json.fromJson(Controller.request().body().asJson(), User.class);
        return Results.ok(Json.toJson(userService.addUser(user)));
    }

    public Result getUser(long id) {
        return Results.ok(Json.toJson(userService.findUser(id)));
    }

    public Result getPostsOfUser(long id) {
        return Results.ok(Json.toJson(userService.findPostsOfUser(id)));
    }

}
