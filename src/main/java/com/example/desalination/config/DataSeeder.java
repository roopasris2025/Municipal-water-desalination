package com.example.desalination.config;

import com.example.desalination.entity.*;
import com.example.desalination.repository.*;
import com.example.desalination.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Runs once automatically every time the application starts (CommandLineRunner.run()
 * is called by Spring Boot right after the context is ready).
 * Only inserts demo rows the FIRST time the app runs against an empty database,
 * so restarting the app does not keep duplicating records.
 *
 * This gives a new student something to look at immediately:
 *  - RO Train 1 (capacity 10000 m3/day, pressure limit 60 bar)
 *  - A normal telemetry reading (55 bar - no alert)
 *  - A high-pressure telemetry reading (72 bar - creates a HIGH PRESSURE alert)
 *  - Basic master data: one authority, one supplier, one mineral buyer, and the 3 products
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private ROTrainRepository roTrainRepository;
    @Autowired private TelemetryService telemetryService;
    @Autowired private MunicipalAuthorityRepository municipalAuthorityRepository;
    @Autowired private SupplierRepository supplierRepository;
    @Autowired private MineralBuyerRepository mineralBuyerRepository;
    @Autowired private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (roTrainRepository.count() > 0) {
            return; // demo data already present, do nothing
        }

        // ---- RO Train ----
        ROTrain train1 = new ROTrain();
        train1.setTrainName("RO Train 1");
        train1.setCapacityPerDay(10000.0);
        train1.setStatus(TrainStatus.ACTIVE);
        train1.setMembranePressureLimit(60.0);
        roTrainRepository.save(train1);

        // ---- Master data ----
        MunicipalAuthority authority = new MunicipalAuthority();
        authority.setName("City Municipal Water Authority");
        authority.setContactPerson("R. Kumar");
        authority.setPhone("9000000001");
        authority.setEmail("water.authority@example.com");
        authority.setAddress("Municipal Office, Main Road");
        municipalAuthorityRepository.save(authority);

        Supplier supplier = new Supplier();
        supplier.setSupplierName("AquaMembrane Suppliers Pvt Ltd");
        supplier.setContactPerson("S. Raj");
        supplier.setPhone("9000000002");
        supplier.setEmail("sales@aquamembrane.example.com");
        supplierRepository.save(supplier);

        MineralBuyer buyer = new MineralBuyer();
        buyer.setBuyerName("Coastal Minerals Trading Co");
        buyer.setContactPerson("P. Devi");
        buyer.setPhone("9000000003");
        buyer.setEmail("purchase@coastalminerals.example.com");
        mineralBuyerRepository.save(buyer);

        Product water = new Product();
        water.setProductName("Potable Desalinated Water");
        water.setProductType("WATER");
        water.setUnit("m3");
        water.setPrice(new BigDecimal("50.00"));
        productRepository.save(water);

        Product salt = new Product();
        salt.setProductName("Industrial Grade Salt Byproduct");
        salt.setProductType("MINERAL");
        salt.setUnit("kg");
        salt.setPrice(new BigDecimal("8.00"));
        productRepository.save(salt);

        Product membrane = new Product();
        membrane.setProductName("RO Membrane Replacement");
        membrane.setProductType("MEMBRANE");
        membrane.setUnit("piece");
        membrane.setPrice(new BigDecimal("15000.00"));
        productRepository.save(membrane);

        // ---- Sample telemetry: normal reading, no alert ----
        Telemetry normalReading = new Telemetry();
        normalReading.setRoTrain(train1);
        normalReading.setIntakePressure(55.0);
        normalReading.setSalinity(35000.0);
        normalReading.setWaterProduced(4500.0);
        normalReading.setMineralRecovered(500.0);
        normalReading.setRecordDate(LocalDate.now().minusDays(1));
        telemetryService.submitTelemetry(normalReading);

        // ---- Sample telemetry: high pressure reading -> triggers HIGH PRESSURE ALERT ----
        Telemetry highPressureReading = new Telemetry();
        highPressureReading.setRoTrain(train1);
        highPressureReading.setIntakePressure(72.0);
        highPressureReading.setSalinity(36000.0);
        highPressureReading.setWaterProduced(4200.0);
        highPressureReading.setMineralRecovered(480.0);
        highPressureReading.setRecordDate(LocalDate.now());
        telemetryService.submitTelemetry(highPressureReading);
    }
}
