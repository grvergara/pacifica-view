package models;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import play.data.validation.Constraints;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
@Entity
public class MachineModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Constraints.Required
    @Column(name = "brand")
    private String Brand;

    @Constraints.Required
    @Column(name = "model")
    private String Model;


    @Column(name = "year")
    private String Year;

    //This establishes the relationship between the MachineModel and the Machine entity
    @OneToMany(mappedBy = "machineModel", cascade = CascadeType.ALL)
    private List<Machine> machines = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated")
    private Date dateUpdated;

    //public static Finder<Long, MachineModel> find = new Finder<>(MachineModel.class);

    //Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return Brand;
    }
    
    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }
    
    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
    
    // Helper method to add a machine to this model
    public void addMachine(Machine machine) {
        this.machines.add(machine);
        machine.setMachineModel(this);
    }
}