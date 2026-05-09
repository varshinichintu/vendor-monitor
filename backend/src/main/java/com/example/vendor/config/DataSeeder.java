package com.example.vendor.config;

import com.example.vendor.entity.Vendor;
import com.example.vendor.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final VendorRepository vendorRepository;

    public DataSeeder(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {

        if (vendorRepository.count() == 0) {

            vendorRepository.save(new Vendor("ABC Traders", "abc@gmail.com", "ACTIVE", 4.5));
            vendorRepository.save(new Vendor("XYZ Suppliers", "xyz@gmail.com", "INACTIVE", 3.8));
            vendorRepository.save(new Vendor("Tech Mart", "tech@mart.com", "ACTIVE", 4.2));
        }
    }
}