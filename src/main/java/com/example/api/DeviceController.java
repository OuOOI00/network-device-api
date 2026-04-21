package com.example.api;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class DeviceController {

    // In-memory store (simulates network device inventory)
    private final Map<String, Device> devices = new LinkedHashMap<>();

    public DeviceController() {
        devices.put("sw-01", new Device("sw-01", "switch", "192.168.1.1", "online"));
        devices.put("sw-02", new Device("sw-02", "switch", "192.168.1.2", "online"));
        devices.put("rt-01", new Device("rt-01", "router", "10.0.0.1",   "offline"));
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "network-device-api");
    }

    @GetMapping("/devices")
    public Collection<Device> listDevices() {
        return devices.values();
    }

    @GetMapping("/devices/{id}")
    public Device getDevice(@PathVariable String id) {
        Device d = devices.get(id);
        if (d == null) throw new NoSuchElementException("Device not found: " + id);
        return d;
    }

    @PostMapping("/devices")
    public Device addDevice(@RequestBody Device device) {
        devices.put(device.id(), device);
        return device;
    }

    @PutMapping("/devices/{id}/status")
    public Device updateStatus(@PathVariable String id, @RequestParam String status) {
        Device existing = devices.get(id);
        if (existing == null) throw new NoSuchElementException("Device not found: " + id);
        Device updated = new Device(existing.id(), existing.type(), existing.ip(), status);
        devices.put(id, updated);
        return updated;
    }

    @DeleteMapping("/devices/{id}")
    public Map<String, String> deleteDevice(@PathVariable String id) {
        devices.remove(id);
        return Map.of("deleted", id);
    }

    public record Device(String id, String type, String ip, String status) {}
}
