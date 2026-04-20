package com.example.vendor.controller;

import com.example.vendor.entity.Vendor;
import com.example.vendor.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "API WORKING";
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return service.save(vendor);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return service.update(id, vendor);
    }

    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {
        return service.delete(id);
    }
}