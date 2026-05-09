package com.example.vendor.repository;

import com.example.vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    List<Vendor> findByNameContainingIgnoreCase(String name);

    List<Vendor> findByStatus(String status);

    List<Vendor> findByNameContainingIgnoreCaseAndStatus(
            String name,
            String status
    );
}