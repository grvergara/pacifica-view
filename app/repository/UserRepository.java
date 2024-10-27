package repository;

import io.ebean.EbeanServer;
import models.User;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserRepository {

    private final EbeanServer ebeanServer;

    @Inject
    public UserRepository(EbeanServer ebeanServer) {
        this.ebeanServer = ebeanServer;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(ebeanServer.find(User.class)
                .where()
                .eq("username", username)
                .findOne());
    }

    public void save(User user) {
        ebeanServer.save(user);
    }
}
