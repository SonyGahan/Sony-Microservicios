package com.sonyairlines.tickets.Service;

import com.sonyairlines.tickets.Model.VueloDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "vuelos-api")
public interface VueloClient {
    @GetMapping("/vuelos")
    List<VueloDto> getAllFlights();
}
