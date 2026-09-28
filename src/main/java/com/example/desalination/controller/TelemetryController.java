package com.example.desalination.controller;

import com.example.desalination.entity.Telemetry;
import com.example.desalination.service.ROTrainService;
import com.example.desalination.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Web UI for submitting simulated sensor readings.
 * On save, all business logic (inventory update + pressure alert check)
 * happens inside TelemetryService.submitTelemetry(), keeping the controller thin.
 */
@Controller
@RequestMapping("/telemetry")
public class TelemetryController {

    @Autowired
    private TelemetryService telemetryService;

    @Autowired
    private ROTrainService roTrainService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("telemetryList", telemetryService.findAll());
        return "telemetry/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("telemetry", new Telemetry());
        model.addAttribute("trains", roTrainService.findAll());
        return "telemetry/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("telemetry") Telemetry telemetry) {
        telemetryService.submitTelemetry(telemetry);
        return "redirect:/telemetry";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        telemetryService.deleteById(id);
        return "redirect:/telemetry";
    }
}
