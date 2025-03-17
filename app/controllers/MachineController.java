package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import play.db.Database;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.NmeaData;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import play.libs.F;
import play.libs.streams.ActorFlow;

public class MachineController extends Controller {
    private final Database db;

    @Inject
    public MachineController(Database db) {
        this.db = db;
    }

    public Result ingestData() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        }
        String machineId = json.get("machineId").asText();
        String data = json.get("data").asText();    
        // TODO: Save data to database      
        return ok("Data ingested successfully");
    }

    public Result ingestNmeaData() {
        JsonNode json = request().body().asJson();
        if (json == null || !json.has("machine_id") || !json.has("nmea_sentence")) {
            return badRequest("Expecting JSON with machine_id and nmea_sentence");
        }
    
        String machineId = json.get("machine_id").asText();
        String nmeaSentence = json.get("nmea_sentence").asText();

        java.util.Date now = new java.util.Date();
        NmeaData nmeaData = new NmeaData( "placeholderMachineId", "placeholderRawData", "placeholderRawSentence", now);
        if (nmeaData == null) return badRequest("Invalid NMEA sentence");
    
        /*String sql = "INSERT INTO nmea_data (machine_id, sentence_type, raw_sentence, latitude, longitude, status, speed, timestamp) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            db.withConnection(conn -> {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nmeaData.getMachineId());
                stmt.setString(2, nmeaData.getSentenceType());
                stmt.setString(3, nmeaData.getRawSentence());
                stmt.setObject(4, nmeaData.getLatitude());
                stmt.setObject(5, nmeaData.getLongitude());
                stmt.setString(6, nmeaData.getStatus());
                stmt.setObject(7, nmeaData.getSpeed());
                stmt.setTimestamp(8, nmeaData.getTimestamp());
                stmt.executeUpdate();
                return null;
            });
            //updateMachineLastUpdated(machineId);
    
            // Broadcast to WebSocket clients
            JsonNode broadcastData = Json.newObject()
                .put("machine_id", nmeaData.getMachineId())
                .put("latitude", nmeaData.getLatitude() != null ? nmeaData.getLatitude() : 0)
                .put("longitude", nmeaData.getLongitude() != null ? nmeaData.getLongitude() : 0)
                .put("status", nmeaData.getStatus())
                .put("speed", nmeaData.getSpeed() != null ? nmeaData.getSpeed() : 0);
            NmeaWebSocketActor.broadcast(broadcastData);
    
            return ok("NMEA data ingested for " + machineId);
        } catch (SQLException e) {
            return internalServerError("Database error: " + e.getMessage());
        }*/
        return ok("Not implemented Yet!");
    }

    public Result index() {
        return ok(views.html.machine.render());
    }
}
