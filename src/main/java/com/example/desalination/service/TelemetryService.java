package com.example.desalination.service;

import com.example.desalination.entity.Telemetry;
import com.example.desalination.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is the heart of the simulated IoT module.
 * When a telemetry reading is submitted from the UI, this service:
 *   1. Saves the raw reading.
 *   2. Adds the produced water into the Inventory (DESALINATED_WATER).
 *   3. Adds the recovered minerals into the Inventory (INDUSTRIAL_SALT).
 *   4. Compares intake pressure against the train's pressure limit and,
 *      if it is exceeded, raises a HIGH PRESSURE alert.
 */
@Service
public class TelemetryService {

    @Autowired
    private TelemetryRepository telemetryRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private AlertService alertService;

    public List<Telemetry> findAll() {
        return telemetryRepository.findAll();
    }

    public Telemetry findById(Long id) {
        return telemetryRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        telemetryRepository.deleteById(id);
    }

    /** Called when a telemetry form is submitted. Contains all the business logic described above. */
    public Telemetry submitTelemetry(Telemetry telemetry) {
        Telemetry saved = telemetryRepository.save(telemetry);

        if (telemetry.getWaterProduced() != null) {
            inventoryService.addQuantity("DESALINATED_WATER", "m3", telemetry.getWaterProduced());
        }
        if (telemetry.getMineralRecovered() != null) {
            inventoryService.addQuantity("INDUSTRIAL_SALT", "kg", telemetry.getMineralRecovered());
        }

        if (telemetry.getRoTrain() != null
                && telemetry.getRoTrain().getMembranePressureLimit() != null
                && telemetry.getIntakePressure() != null
                && telemetry.getIntakePressure() > telemetry.getRoTrain().getMembranePressureLimit()) {

            String message = "HIGH PRESSURE ALERT: " + telemetry.getRoTrain().getTrainName()
                    + " reading " + telemetry.getIntakePressure() + " bar exceeds limit "
                    + telemetry.getRoTrain().getMembranePressureLimit() + " bar";
            alertService.createAlert(message, "HIGH");
        }

        return saved;
    }

    /** Sum of all water produced across every telemetry record - used on the Dashboard. */
    public double getTotalWaterProduced() {
        return telemetryRepository.findAll().stream()
                .mapToDouble(t -> t.getWaterProduced() == null ? 0 : t.getWaterProduced())
                .sum();
    }

    /** Sum of all mineral recovered across every telemetry record - used on the Dashboard. */
    public double getTotalMineralRecovered() {
        return telemetryRepository.findAll().stream()
                .mapToDouble(t -> t.getMineralRecovered() == null ? 0 : t.getMineralRecovered())
                .sum();
    }
}
