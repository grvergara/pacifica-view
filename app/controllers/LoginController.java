package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.UserRepository;
import models.User;
import javax.inject.Inject;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;


public class LoginController extends Controller {

    private final UserRepository userRepository;

    @Inject
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Display the login form
    public Result showLoginForm() {
        return ok(views.html.login.render(null));
    }

    // Handle login submission
    public Result login(Http.Request request) {
        String username = request.body().asFormUrlEncoded().get("username")[0];
        String password = request.body().asFormUrlEncoded().get("password")[0];

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPasswordHash())) {
                // Password matches, set session
                return redirect(routes.CharityController.index())
                        .addingToSession(request, "username", user.getUsername());
            }
        }

        // Authentication failed
        return badRequest(views.html.login.render("Invalid username or password"));
    }

    // Handle logout
    public Result logout(Http.Request request) {
        return redirect(routes.LoginController.showLoginForm())
                .removingFromSession(request, "username");
    }
}
