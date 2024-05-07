package com.vuelossony.proyecto.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //Esta línea configura los mapeos CORS para todas las URL de la aplicación, especificada por el patrón "/**"
        registry.addMapping("/**")
                //Esta línea especifica los orígenes permitidos para las solicitudes CORS. En este caso, "/*" permite solicitudes desde cualquier origen.
                .allowedOrigins("/*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                //Esta línea especifica los encabezados HTTP permitidos para las solicitudes CORS. En este caso, se permite cualquier encabezado.
                .allowedHeaders("*");
    }

}
