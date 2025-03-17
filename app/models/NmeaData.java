package models;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
@Entity
public class NmeaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "machine_id")
    private String machineId;

    @Column(name = "sentence_type")
    private String sentenceType;

    @Column(name = "raw_sentence")
    private String rawSentence;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "status")
    private String status;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    /*
    @machineId:
    @sentenceType
    @rawSentence
    @timestamp
     */
    public NmeaData(String machineId, String sentenceType, String rawSentence, Date timestamp) {
        this.machineId = machineId;
        this.sentenceType = sentenceType;
        this.rawSentence = rawSentence;
        this.timestamp = timestamp;
    }
}
