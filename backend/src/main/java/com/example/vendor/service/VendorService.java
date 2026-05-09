package com.example.vendor.service;

import com.example.vendor.entity.Vendor;
import com.example.vendor.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository repo;

    public VendorService(VendorRepository repo) {
        this.repo = repo;
    }

    // Get all vendors
    public List<Vendor> getAll() {
        return repo.findAll();
    }

    // Get vendor by ID
    public Vendor getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Add vendor
    public Vendor save(Vendor vendor) {
        return repo.save(vendor);
    }

    // Update vendor
    public Vendor update(Long id, Vendor newVendor) {

        return repo.findById(id).map(vendor -> {

            vendor.setName(newVendor.getName());
            vendor.setEmail(newVendor.getEmail());
            vendor.setStatus(newVendor.getStatus());
            vendor.setRating(newVendor.getRating());

            return repo.save(vendor);

        }).orElse(null);
    }

    // Delete vendor
    public String delete(Long id) {

        repo.deleteById(id);

        return "Deleted vendor with id " + id;
    }

    // Search + Filter vendors
    public List<Vendor> searchVendors(String q, String status) {

        // Search + status filter
        if (!q.isEmpty() && !status.equalsIgnoreCase("All")) {

            return repo.findByNameContainingIgnoreCaseAndStatus(
                    q,
                    status
            );
        }

        // Search only
        if (!q.isEmpty()) {

            return repo.findByNameContainingIgnoreCase(q);
        }

        // Status only
        if (!status.equalsIgnoreCase("All")) {

            return repo.findByStatus(status);
        }

        // Return all vendors
        return repo.findAll();
    }
}