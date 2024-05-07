package com.vuelossony.proyecto.configuration;

import com.vuelossony.proyecto.model.Dolar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VueloConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    //Muestra el valor del dolar tarjeta. Devuelve el dolar como objeto
    public Dolar fetchDolar(){
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares/tarjeta";
        return restTemplate.getForObject(apiUrl, Dolar.class); //Direccion de url y el archivo de clase, se pasa la clase no el objeto dolar.
    }

    //Muestra un array de todos los tipos de dolar,(responseEntity, respuesta completa)
    public Dolar[] fetchAllDolars(){
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares";
        return restTemplate.getForEntity(apiUrl,Dolar[].class).getBody(); //devuelve un Array,en este caso una respuesta completa, que tome solo el body
    }
}
