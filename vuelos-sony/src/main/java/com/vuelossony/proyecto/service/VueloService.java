package com.vuelossony.proyecto.service;

import com.vuelossony.proyecto.model.Company;
import com.vuelossony.proyecto.model.Dolar;
import com.vuelossony.proyecto.model.Vuelo;
import com.vuelossony.proyecto.model.VueloDto;
import com.vuelossony.proyecto.repository.CompanyRepository;
import com.vuelossony.proyecto.repository.VueloRepository;
import com.vuelossony.proyecto.utils.VueloUtils;
import com.vuelossony.proyecto.configuration.VueloConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloService {
    @Autowired
    VueloRepository vueloRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    VueloUtils vueloUtils;
    @Autowired
    VueloConfiguration vueloConfiguration;

    //Muestra el listado de todos los vuelos transformados a Dto
    public List<VueloDto> listadoDeVuelos() {
        double dolarPrice = getDolar(); // Obtener el precio del dólar una sola vez
        List<Vuelo> vuelos = vueloRepository.findAll(); // Siempre devuelve una lista de vuelos
        return vueloUtils.listMapToVueloDto(vuelos, dolarPrice); // Devuelve un listado de vuelos con el precio del dolar
    }

    //Crea un nuevo vuelo
    public Optional<Vuelo> crearVuelo(Long companyId, Vuelo vuelo) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("No se pudo crear el vuelo"));

        vuelo.setCompany(company);
        return Optional.of(vueloRepository.save(vuelo));
    }

    //Busca un vuelo por ID
    public Optional<Vuelo> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id);
    }


    //Elimina un vuelo de la lista de vuelos
    public void borrarVueloPorId(Long id) {
        vueloRepository.deleteById(id);
    }


    //Modifica un vuelo de la lista
    public Optional<Vuelo> actualizarVuelo(Long companyId,Vuelo vuelo) {
        vueloRepository.save(vuelo);
        return vueloRepository.findById(vuelo.getId());
    }


    //Busca un vuelo por Origen de partida
    public List<Vuelo> getByOrigen(String origen) {
        return vueloRepository.findByOrigen(origen);
    }


    //Busca un vuelo por origen y destino
    public List<Vuelo> getByOrigenAndDestino(String origen, String destino) {
        return vueloRepository.findByOrigenAndDestino(origen, destino);
    }

    public List<Vuelo> getOffers(Double offerPrice){
        List<Vuelo> vuelos = vueloRepository.findAll();
        return vueloUtils.detectOffers(vuelos, offerPrice);
    }


    //Va privado porque solo se usa en el service
    private double getDolar() {
        Dolar dolar = vueloConfiguration.fetchDolar();
        return dolar.getPromedio();
    }

    public List<Dolar> getAllDolars() {
        return List.of(vueloConfiguration.fetchAllDolars());
    } //List.of transforma el array en lista

}
