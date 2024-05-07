package com.sonyairlines.tickets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private VueloDto vuelo;
    private String passengerName;
    private String passengerEmail;
    private String passengerPassport;

}
