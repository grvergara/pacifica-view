package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class CounterModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public Counter provideCounter() {
        return new Counter();
    }

    @Singleton
    public class Counter {
        private final AtomicLong counter = new AtomicLong(0);

        public long getNextValue() {
            return counter.getAndIncrement();
        }
    }
}

