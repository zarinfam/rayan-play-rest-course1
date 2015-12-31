package services;

import daos.UserDao;
import models.Post;
import models.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by saeed on 12/24/15.
 */

@Named
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.getAll();
    }

    @Override
    public User addUser(User user) {
        userDao.persist(user);
        return user;
    }

    @Override
    public User findUser(long id) {
        return userDao.find(id);
    }

    @Override
    public Set<Post> findPostsOfUser(long id) {
        return userDao.find(id).getPosts();
    }
}
