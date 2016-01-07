package controllers;

import models.Post;
import models.User;
import play.libs.Json;
import play.mvc.*;
import services.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
//@Security.Authenticated(Secured.class)
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

    public Result upload() {
        Http.MultipartFormData body = Controller.request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        copyFile(picture.getFile(), picture.getFilename());
        return Results.ok("File uploaded");
    }

    private static void copyFile(File fileToCopy, String fileName) {

        File newFile = new File("/home/saeed/projects/rayan/doc/course/src/rayan-play-rest-course1/public/" + fileName);

        try {
            try (FileInputStream inputStream = new FileInputStream(fileToCopy);
                 FileOutputStream outputStream = new FileOutputStream(newFile)
            ) {
                FileChannel inChannel = inputStream.getChannel();
                FileChannel outChannel = outputStream.getChannel();
                inChannel.transferTo(0, fileToCopy.length(), outChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
