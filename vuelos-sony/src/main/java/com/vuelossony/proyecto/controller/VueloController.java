package com.vuelossony.proyecto.controller;

import com.vuelossony.proyecto.model.Dolar;
import com.vuelossony.proyecto.model.Vuelo;
import com.vuelossony.proyecto.model.VueloDto;
import com.vuelossony.proyecto.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vuelos")
@CrossOrigin("/*")
public class VueloController {

    @Autowired
    VueloService vueloService;

    //Devuelve el listado de vuelos completo de la clase DTO
    @GetMapping("")
    public List<VueloDto> getListadoDeVuelos(){
        return vueloService.listadoDeVuelos();
    }

    //Agrega un nuevo vuelo al listado
    @PostMapping("/agregar")
    public Optional<Vuelo> createVuelo(@PathVariable Long companyId, @RequestBody Vuelo vuelo){
        return vueloService.crearVuelo(companyId, vuelo);
    }

    //Busca y devuelve un vuelo segun su ID
    @GetMapping("/{id}")
    public Optional<Vuelo> findVueloById(@PathVariable Long id){
        return vueloService.buscarVueloPorId(id);
    }

    //Busca y elimina un vuelo segun su ID
    @DeleteMapping("/eliminar/{id}")
    public void deleteVuelo(@PathVariable Long id){
        vueloService.borrarVueloPorId(id);
    }

    //Busca un vuelo segun su ID, lo modifica y lo guarda en la lista de vuelos
    @PutMapping("/actualizar")
    public Optional<Vuelo> updateVuelo(@PathVariable Long companyId, @RequestBody Vuelo vuelo){
        return vueloService.actualizarVuelo(companyId, vuelo);
    }


    //Busca un vuelo segun su punto de partida
    @GetMapping("/origen")
    public List<Vuelo> findVueloByOrigen(@RequestParam String origen){
        return vueloService.getByOrigen(origen);
    }


    //Busca un vuelo segun su origen y destino
    @GetMapping("/ubicacion")
    public List<Vuelo> findVueloByUbicacion(@RequestParam String origen, @RequestParam String destino){
        return vueloService.getByOrigenAndDestino(origen, destino);
    }

    //Muestra un listado de vuelos de acuerdo a si su precio es menor o igual al precio indicado
    @GetMapping("/ofertas")
    public List<Vuelo> findListadoDeOfertas(@RequestParam double precio){
        return  vueloService.getOffers(precio);
    }

    //Muestra un listado de todos los tipos de dolares de la Api
    @GetMapping("/alldolars")
    public List<Dolar> getAllDolars(){
        return vueloService.getAllDolars();
    }

}
