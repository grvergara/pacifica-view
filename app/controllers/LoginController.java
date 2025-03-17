package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.UserRepository;
import models.User;
import javax.inject.Inject;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import play.data.FormFactory;

public class LoginController extends Controller {

    private final FormFactory formFactory;
    private final UserRepository userRepository;

    @Inject
    public LoginController(FormFactory formFactory, UserRepository userRepository) {
        this.formFactory = formFactory;
        this.userRepository = userRepository;
    }

    // Display the login form
    public Result showLoginForm() {
        return ok(views.html.login.render(null));
    }

    // Handle login submission
    public Result login(Http.Request request) {
        // Get form data
        String username = request.body().asFormUrlEncoded().get("username")[0];
        String password = request.body().asFormUrlEncoded().get("password")[0];

        // Find user
        User user = userRepository.findByUsername(username);
        
        if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
            // Login successful - create session
            return redirect(routes.PacificaController.index())
                   .addingToSession(request, "username", username);
        } else {
            // Login failed
            return badRequest(views.html.login.render("Invalid username or password"));
        }
    }

    // Handle logout
    public Result logout(Http.Request request) {
        return redirect(routes.LoginController.showLoginForm())
                .removingFromSession(request, "username");
    }
}
