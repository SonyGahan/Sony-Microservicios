package com.vuelossony.proyecto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class VueloTest {
    private static Vuelo vuelo;

    @Test
    public void setAndGetOrigenTest(){
        String testedOrigen = "EZE";
        vuelo.setOrigen(testedOrigen);
        Assertions.assertEquals(vuelo.getOrigen(), testedOrigen);
    }

    @Test
    public void  setAndGetDestinoTest(){
        String testedDestino = "COR";
        vuelo.setDestino(testedDestino);
        Assertions.assertEquals(vuelo.getDestino(), testedDestino);
    }

    @BeforeAll
    public static void setUp(){
        vuelo = new Vuelo();
    }

}