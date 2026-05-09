package com.example.vendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllVendors() throws Exception {
        mockMvc.perform(get("/vendors"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVendorById() throws Exception {
        mockMvc.perform(get("/vendors/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateVendor() throws Exception {
        String body = "{\"name\":\"Test\",\"email\":\"test@test.com\"}";

        mockMvc.perform(post("/vendors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete("/vendors/1"))
                .andExpect(status().isOk());
    }
}