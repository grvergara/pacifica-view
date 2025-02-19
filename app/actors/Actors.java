package actors;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import akka.actor.*;
import akka.cluster.Cluster;
import backend.*;
import play.Environment;
import play.Application;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.typesafe.config.Config;
/**
 * Module for actors used by the web front end.
 */
@Singleton
public class Actors {


    private final ActorRef regionManagerClient;


    @Inject
    public Actors(ActorSystem system, Environment env, Config config) {
        regionManagerClient = system.actorOf(RegionManagerClient.props(), "regionManagerClient");

        if (Cluster.get(system).getSelfRoles().stream().anyMatch(r -> r.startsWith("backend"))) {
            system.actorOf(RegionManager.props(), "regionManager");
        }

        if (Settings.SettingsProvider.get(system).BotsEnabled) {
            int id = 1;
            URL url = env.resource("bots/" + id + ".json");
            List<URL> urls = new ArrayList<>();
            while (url != null) {
                urls.add(url);
                id++;
                url = env.resource("bots/" + id + ".json");
            }
            system.actorOf(BotManager.props(regionManagerClient, urls));
        }
    }

    public ActorRef getRegionManagerClient() {
        return regionManagerClient;
    }

}
