package auth;

import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Security;
import controllers.routes;
import java.util.Optional;

public class Secured extends Security.Authenticator {
    
    @Override
    public Optional<String> getUsername(Request request) {
        return Optional.ofNullable(request.session().get("username")) ;
    }

    @Override
    public Result onUnauthorized(Request request) {
        return redirect(routes.LoginController.showLoginForm())
               .flashing("error", "Please login to access this page");
    }
} 