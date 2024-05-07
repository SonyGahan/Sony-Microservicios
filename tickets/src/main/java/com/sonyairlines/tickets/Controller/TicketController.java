package com.sonyairlines.tickets.Controller;


import com.sonyairlines.tickets.Model.Ticket;
import com.sonyairlines.tickets.Model.VueloDto;
import com.sonyairlines.tickets.Service.TicketService;
import com.sonyairlines.tickets.Service.VueloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketservice;

    @Autowired
    private VueloClient vueloClient;

    @GetMapping("")
    public List<Ticket> getAllTickets() {
        return ticketservice.getAllTickets();
    }

    @GetMapping("/vuelos")
    public List<VueloDto> getAllFlights(){
        return vueloClient.getAllFlights();
    }

    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket){
        return ticketservice.addTicket(ticket);
    }
}
