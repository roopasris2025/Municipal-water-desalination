package com.example.desalination.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * One Reverse-Osmosis processing line in the plant.
 * A plant usually has several trains running in parallel.
 *
 * Relationship: ONE train can have MANY telemetry readings over time.
 * @OneToMany(mappedBy = "roTrain") -> the "many" side (Telemetry) owns the foreign key.
 *   mappedBy tells Hibernate "the join column is defined on the Telemetry entity,
 *   I am just the read-only inverse side here".
 */
@Entity
@Table(name = "ro_train")
public class ROTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Train name is required")
    private String trainName;

    private Double capacityPerDay;     // m3/day

    @Enumerated(EnumType.STRING)
    private TrainStatus status;

    private Double membranePressureLimit; // bar

    @OneToMany(mappedBy = "roTrain")
    private List<Telemetry> telemetryRecords = new ArrayList<>();

    public ROTrain() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public Double getCapacityPerDay() { return capacityPerDay; }
    public void setCapacityPerDay(Double capacityPerDay) { this.capacityPerDay = capacityPerDay; }

    public TrainStatus getStatus() { return status; }
    public void setStatus(TrainStatus status) { this.status = status; }

    public Double getMembranePressureLimit() { return membranePressureLimit; }
    public void setMembranePressureLimit(Double membranePressureLimit) { this.membranePressureLimit = membranePressureLimit; }

    public List<Telemetry> getTelemetryRecords() { return telemetryRecords; }
    public void setTelemetryRecords(List<Telemetry> telemetryRecords) { this.telemetryRecords = telemetryRecords; }
}
