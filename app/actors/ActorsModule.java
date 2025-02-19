package actors;

import com.google.inject.AbstractModule;

public class ActorsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Actors.class).asEagerSingleton();
    }
}
