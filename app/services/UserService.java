package services;


import models.Post;
import models.User;

import java.util.List;
import java.util.Set;

/**
 * Created by saeed on 12/24/15.
 */
public interface UserService {
    List<User> findAllUser();

    User addUser(User user);

    User findUser(long id);

    Set<Post> findPostsOfUser(long id);
}
