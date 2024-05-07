package com.vuelossony.proyecto.service;

import com.vuelossony.proyecto.exceptions.ResourceNotFoundException;
import com.vuelossony.proyecto.model.Company;
import com.vuelossony.proyecto.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    //Crea una nueva compañia (save arroja excepciones más específicas como DataAccessException)
    public Company crearCompany(Company company) throws ResourceNotFoundException {
        try {
            return companyRepository.save(company);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Company", "save", company.getId());
        }
    }

    //Muestra el listado de compañias
    public List<Company> listadoDeCompanies() {
        return companyRepository.findAll();
    }

    //Busca una compañia por ID
    public Optional<Company> buscarCompanyPorId(Long id) {
        return companyRepository.findById(id);
    }

    //Elimina una compañia de la lista de compañias
    public void borrarCompanyPorId(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("company","id",id));
        companyRepository.deleteById(company.getId());
    }

    //Modifica una compañia de la lista
    public Optional<Company> actualizarCompany(Company company) {
        companyRepository.save(company);
        return companyRepository.findById(company.getId());
    }
}
