package com.example.vendor.controller;

import com.example.vendor.entity.Vendor;
import com.example.vendor.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
@CrossOrigin(origins = "http://localhost:5173")
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }

    // Test API
    @GetMapping("/test")
    public String test() {
        return "API WORKING";
    }

    // Get all vendors
    @GetMapping
    public List<Vendor> getAllVendors() {
        return service.getAll();
    }

    // Search + Filter vendors
    @GetMapping("/search")
    public List<Vendor> searchVendors(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "All") String status
    ) {

        return service.searchVendors(q, status);
    }

    // Get vendor by ID
    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {

        return service.getById(id);
    }

    // Add vendor
    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {

        return service.save(vendor);
    }

    // Update vendor
    @PutMapping("/{id}")
    public Vendor updateVendor(
            @PathVariable Long id,
            @RequestBody Vendor vendor
    ) {

        return service.update(id, vendor);
    }

    // Delete vendor
    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {

        return service.delete(id);
    }
}