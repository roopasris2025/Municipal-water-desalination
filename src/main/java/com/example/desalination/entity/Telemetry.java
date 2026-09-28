package com.example.desalination.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * One reading submitted manually from the UI, simulating a sensor reading
 * taken from a particular RO Train on a particular date.
 *
 * Relationship: MANY telemetry rows belong to ONE train.
 * @ManyToOne  -> this is the OWNING side; the "ro_train_id" foreign key column
 *                lives in this table (telemetry).
 * @JoinColumn -> explicitly names that foreign key column.
 */
@Entity
@Table(name = "telemetry")
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ro_train_id")
    private ROTrain roTrain;

    private Double intakePressure;   // bar
    private Double salinity;         // ppm
    private Double waterProduced;    // m3
    private Double mineralRecovered; // kg
    private LocalDate recordDate;

    public Telemetry() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ROTrain getRoTrain() { return roTrain; }
    public void setRoTrain(ROTrain roTrain) { this.roTrain = roTrain; }

    public Double getIntakePressure() { return intakePressure; }
    public void setIntakePressure(Double intakePressure) { this.intakePressure = intakePressure; }

    public Double getSalinity() { return salinity; }
    public void setSalinity(Double salinity) { this.salinity = salinity; }

    public Double getWaterProduced() { return waterProduced; }
    public void setWaterProduced(Double waterProduced) { this.waterProduced = waterProduced; }

    public Double getMineralRecovered() { return mineralRecovered; }
    public void setMineralRecovered(Double mineralRecovered) { this.mineralRecovered = mineralRecovered; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
}
