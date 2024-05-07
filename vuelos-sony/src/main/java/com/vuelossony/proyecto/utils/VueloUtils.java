package com.vuelossony.proyecto.utils;

import com.vuelossony.proyecto.model.Vuelo;
import com.vuelossony.proyecto.model.VueloDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VueloUtils {

    public List<Vuelo> detectOffers(List<Vuelo> vuelos, Double offerPrice) {
        return vuelos.stream()
                .filter(vuelo -> vuelo.getPrecioEnPesos() < offerPrice)
                .collect(Collectors.toList());
    }

    //Devuelve UN solo vuelo con el precio del dolar asociado del VueloDto
    public VueloDto vueloMapper(Vuelo vuelo, double dolarPrice) {
        return new VueloDto(vuelo.getId(), //Tengo que crear la instancia del VueloDto para poder traer los valores con get
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getFechaHoraSalida(),
                vuelo.getFechaHoraLlegada(),
                dolarPrice * vuelo.getPrecioEnPesos(),
                vuelo.getFrecuencia(), vuelo.getCompany());
    }

    //Devuelve un listado de vuelos con el precio del dolar ya convertido
    public List<VueloDto> listMapToVueloDto(List<Vuelo> vuelos, double dolarPrice) {
        return vuelos.stream()
                .map(vuelo -> vueloMapper(vuelo, dolarPrice))
                .collect(Collectors.toList());
    }
}
