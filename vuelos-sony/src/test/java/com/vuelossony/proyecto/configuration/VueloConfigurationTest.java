package com.vuelossony.proyecto.configuration;


import com.vuelossony.proyecto.model.Dolar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class VueloConfigurationTest {

    @Autowired
    VueloConfiguration vueloConfiguration;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void fetchDolarTest() {
        Dolar dummyDolar = new Dolar();
        dummyDolar.setVenta(1200);
        dummyDolar.setCompra(1300);

        when(restTemplate.getForObject(anyString(), eq(Dolar.class))).thenReturn(dummyDolar);

        Dolar dolar = vueloConfiguration.fetchDolar();
        assertNotNull(dolar);
        assertEquals(1250, dolar.getPromedio());
    }
}
