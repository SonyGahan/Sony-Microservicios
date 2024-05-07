package com.vuelossony.proyecto.controller;

import com.vuelossony.proyecto.exceptions.ResourceNotFoundException;
import com.vuelossony.proyecto.model.Company;
import com.vuelossony.proyecto.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    //Agrega una nueva compañia al listado
    @PostMapping("/addcompany")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        try {
            Company createdCompany = companyService.crearCompany(company);
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear la compañía", ex);
        }
    }

    //Devuelve el listado de compañias
    @GetMapping("")
    public List<Company> getListadoDeCompanies(){
        return companyService.listadoDeCompanies();
    }

    //Busca y devuelve una compañia segun su ID
    public Company findCompanyById(@PathVariable Long id){
        return companyService.buscarCompanyPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    //Busca y elimina una compañia segun su ID
    @DeleteMapping("/deletecompany/{id}")
    public String deleteCompany(@PathVariable Long id){
        try{
            companyService.borrarCompanyPorId(id);
            return "Compania eliminada con exito";
        } catch (ResourceNotFoundException e) {
            e.printStackTrace(); //Muestra todo el detalle del error
            return "No existe la compania con el Id seleccionado";
        }

    }

    //Busca una compañia segun su ID, la modifica y la guarda en la lista de compañias
    @PutMapping("/updatecompany")
    public Optional<Company> updateCompany(@RequestBody Company company){
        return companyService.actualizarCompany(company);
    }

}
