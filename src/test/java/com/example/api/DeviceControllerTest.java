package com.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void healthReturnsUp() throws Exception {
        mvc.perform(get("/api/health"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void listDevicesReturnsThree() throws Exception {
        mvc.perform(get("/api/devices"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void getDeviceById() throws Exception {
        mvc.perform(get("/api/devices/sw-01"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.type").value("switch"));
    }

    @Test
    void getUnknownDeviceReturns404() throws Exception {
        mvc.perform(get("/api/devices/unknown"))
           .andExpect(status().isNotFound());
    }
}
