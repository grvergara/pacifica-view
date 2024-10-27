package repository;

import models.User;
import io.ebean.Ebean;
import java.util.List;

public class UserRepository {

    public List<User> findAll() {
        return Ebean.find(User.class).findList();
    }

    public User findById(Long id) {
        return Ebean.find(User.class, id);
    }

    public User findByUsername(String username) {
        return Ebean.find(User.class, username);
    }

    public void save(User user) {
        Ebean.save(user);
    }

    public void delete(User user) {
        Ebean.delete(user);
    }
}
