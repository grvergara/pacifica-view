package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;


public class NmeaWebSocketActor extends AbstractActor {
    private final ActorRef out;

    public static Props props(ActorRef out) {
        return Props.create(NmeaWebSocketActor.class, out);
    }

    public NmeaWebSocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(JsonNode.class, message -> {
                out.tell(message, self());
            })
            .build();
    }

    // Broadcast method to send updates to all connected clients
    public static void broadcast(JsonNode data) {
        NmeaWebSocketActorHolder.getActor().tell(data, ActorRef.noSender());
    }
}

// Singleton holder for broadcasting
class NmeaWebSocketActorHolder {
    private static ActorRef actor;

    public static void setActor(ActorRef actorRef) {
        actor = actorRef;
    }

    public static ActorRef getActor() {
        return actor;
    }
}