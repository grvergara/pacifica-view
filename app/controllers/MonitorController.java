package controllers;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import play.libs.F;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import javax.inject.Inject;

public class MonitorController extends Controller {
    private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public MonitorController(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    /*public WebSocket monitor() {
        return WebSocket.Json.acceptOrResult(request -> {
            return F.Either.Right(ActorFlow.actorRef(
                out -> NmeaWebSocketActor.props(out),
                actorSystem,
                materializer
            ));
        });
    }*/
}