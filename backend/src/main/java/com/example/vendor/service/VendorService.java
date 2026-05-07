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

    public List<Vendor> getAll() {
        return repo.findAll();
    }

    public Vendor getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Vendor save(Vendor vendor) {
        return repo.save(vendor);
    }

    public Vendor update(Long id, Vendor newVendor) {
        return repo.findById(id).map(vendor -> {
            vendor.setName(newVendor.getName());
            vendor.setEmail(newVendor.getEmail());
            return repo.save(vendor);
        }).orElse(null);
    }

    public String delete(Long id) {
        repo.deleteById(id);
        return "Deleted vendor with id " + id;
    }
}