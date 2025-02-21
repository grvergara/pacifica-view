package controllers;

import com.google.inject.Inject;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Controller;
import modules.CounterModule;
import modules.CounterModule.Counter;

public class FooController extends Controller {
    private final Counter counter;

    @Inject
    public FooController(Counter counter) {
        this.counter = counter;
    }
    
   public Result index() {
        Long count = counter.getNextValue();
        return ok(views.html.foo.render(count));
   }
}
