package com.example.desalination.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * A warning message created automatically by the system,
 * e.g. when telemetry pressure exceeds a train's safe limit.
 */
@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String severity;      // e.g. HIGH, MEDIUM, LOW
    private LocalDateTime createdAt;

    public Alert() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
