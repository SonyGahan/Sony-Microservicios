package com.vuelossony.proyecto.utils;

import com.vuelossony.proyecto.model.Vuelo;
import com.vuelossony.proyecto.model.VueloDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class VueloUtilsTest {
    @Autowired
    VueloUtils vueloUtils;

    @Test
    void detectOffersTest() {
        List<Vuelo> vuelos = new ArrayList<>();
        double maxPrice = 2000;

        vuelos.add(new Vuelo(1L, 1000.0));
        vuelos.add(new Vuelo(2L, 2000.0));
        vuelos.add(new Vuelo(3L, 1500.0));
        vuelos.add(new Vuelo(4L, 3000.0));

        List<Vuelo> offers = vueloUtils.detectOffers(vuelos, maxPrice);

        assertEquals(2, offers.size());
        assertEquals(1, offers.get(0).getId());
        assertEquals(3, offers.get(1).getId());
    }

    @Test
    void vueloMapperTest() {
        double dolarPrice = 1015; // Precio del dolar para la conversión
        VueloUtils vueloUtils = new VueloUtils(); // Asumo que vueloUtils es una instancia de VueloUtils

        Vuelo vuelo = new Vuelo();
        vuelo.setId(1L);
        vuelo.setOrigen("Roma");
        vuelo.setDestino("Buenos Aires");
        vuelo.setFechaHoraSalida("Salida");
        vuelo.setFechaHoraLlegada("Llegada");
        vuelo.setPrecioEnPesos(100);
        vuelo.setFrecuencia("Semanal");

        // Llamo al método vueloMapper directamente con un objeto Vuelo
        VueloDto vueloDto = vueloUtils.vueloMapper(vuelo, dolarPrice);

        // Verifico los datos del DTO generado
        assertEquals(1L, vueloDto.getId());
        assertEquals("Roma", vueloDto.getOrigen());
        assertEquals("Buenos Aires", vueloDto.getDestino());
        assertEquals("Salida", vueloDto.getFechaHoraSalida());
        assertEquals("Llegada", vueloDto.getFechaHoraLlegada());
        assertEquals(vuelo.getPrecioEnPesos() * dolarPrice, vueloDto.getConvertedPrice());
        assertEquals("Semanal", vueloDto.getFrecuencia());
    }
}
