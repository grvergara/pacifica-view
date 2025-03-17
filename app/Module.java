import actors.StocksActor;
import actors.UserActor;
import actors.UserParentActor;
import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;

import java.time.Clock;

import static java.time.Clock.systemUTC;

public class Module extends AbstractModule implements AkkaGuiceSupport {

    @Override
    protected void configure() {
        bind(Clock.class).toInstance(systemUTC());
        //Important!! To implement DI
        bindActor(UserParentActor.class, "userParentActor");
        bindActor(StocksActor.class, "stocksActor");
        bindActorFactory(UserActor.class, UserActor.Factory.class);
    }
}
