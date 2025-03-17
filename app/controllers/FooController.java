package controllers;

import com.google.inject.Inject;
import org.webjars.play.WebJarsUtil;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Controller;
import modules.CounterModule;
import modules.CounterModule.Counter;

public class FooController extends Controller {
    private final Counter counter;
    private final WebJarsUtil webJarsUtil;

    @Inject
    public FooController(Counter counter, WebJarsUtil webJarsUtil) {
        this.counter = counter;
        this.webJarsUtil = webJarsUtil;
    }
    
   public Result index() {
        Long count = counter.getNextValue();
        return ok(views.html.foo.render(count, webJarsUtil));
   }
}
