package controllers;

import play.db.Database;
import play.mvc.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Secured;
import play.libs.Json;
import play.mvc.Security;

import javax.inject.Inject;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.sql.Connection;
import java.sql.SQLException;
public class HealthController extends Controller {

    private final Database db;

    @Inject
    public HealthController(Database db) {
        this.db = db;
    }

    @Security.Authenticated(Secured.class)
    public Result checkDatabaseConnection() {
        
        ObjectNode result = Json.newObject();
        
        try (Connection conn = db.getConnection()) {
            boolean isValid = conn.isValid(3); // Timeout after 3 seconds
            
            if (isValid) {
                result.put("status", "UP");
                result.put("message", "Database connection successful");
                result.put("database", db.getUrl());
                return ok(result);
            } else {
                result.put("status", "DOWN");
                result.put("message", "Database connection failed");
                return ok(result);
            }
            
        } catch (SQLException e) {
            result.put("status", "ERROR");
            result.put("message", "Database connection error: " + e.getMessage());
            return ok(result);
        }
    }

    @Security.Authenticated(Secured.class)
    public Result checkHealth() {
        ObjectNode result = Json.newObject();
        ObjectNode dbStatus = Json.newObject();
        ObjectNode systemStatus = Json.newObject();
        
        // Check Database
        try (Connection conn = db.getConnection()) {
            boolean isValid = conn.isValid(3);
            dbStatus.put("status", isValid ? "UP" : "DOWN");
            dbStatus.put("url", db.getUrl());
        } catch (SQLException e) {
            dbStatus.put("status", "ERROR");
            dbStatus.put("error", e.getMessage());
        }
        
        // System Information
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        systemStatus.put("heapMemoryUsage", memoryBean.getHeapMemoryUsage().getUsed());
        systemStatus.put("nonHeapMemoryUsage", memoryBean.getNonHeapMemoryUsage().getUsed());
        systemStatus.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        systemStatus.put("javaVersion", System.getProperty("java.version"));
        
        // Combine all checks
        result.put("timestamp", System.currentTimeMillis());
        result.set("database", dbStatus);
        result.set("system", systemStatus);
        
        // Overall status
        boolean isHealthy = dbStatus.get("status").asText().equals("UP");
        result.put("status", isHealthy ? "UP" : "DOWN");
        
        return isHealthy ? ok(result) : ok(result);
    }
}
