package com.vuelossony.proyecto.repository;

import com.vuelossony.proyecto.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;
    private Company company;

    @BeforeEach
    public void setup(){
        company = new Company("Ita Airways","Ita Banner", "https://www.ita-airways.com/es_ar");
    }

    @Test
    void saveCompanyTest(){
        //Configuracion previa en el metodo setup
        Company company1 = companyRepository.save(company);

        //Verificar la salida o el comportamiento en el test
        assertThat(company1).isNotNull();
        assertThat(company1.getName()).isEqualTo("Ita Airways");
    }

    @Test
    void findAllCompaniesTest(){
        //Configuracion previa, tener una compañia ya guardada
        Company company1 = new Company("Ita Airways", "Ita Banner", "https://www.ita-airways.com/es_ar");

        //Guardo las 2 compañias
        companyRepository.save(company);
        companyRepository.save(company1);
        List<Company> companyList = companyRepository.findAll();

        //Verificar la salida o el comportamiento
        assertThat(companyList).isNotNull();
        assertThat(companyList.size()).isEqualTo(2);
    }

    @Test
    void companyFindById(){
        //Configuracion previa, tener una compañia ya guardada
        companyRepository.save(company);

        //Llamar la funcionalidad de buscar la copañia por ID y la guardo en una variable
        Company companyTest = companyRepository.findById(company.getId()).get();

        //Verificar la salida o el comportamiento
        assertThat(companyTest).isNotNull();
    }

    @Test
    void companyUpdateTest(){
        //Configuracion previa, tener una compañia ya guardada
        companyRepository.save(company);

        //Llamar la funcionalidad de buscar la copañia por ID y la guardo en una variable
        Company company1 = companyRepository.findById(company.getId()).get();

        //Modificar un valor de la compañia:
        company1.setBanner("Banner prueba1");
        company1.setName("Compania prueba");
        //Guardo y actualizo
        Company companyUpdated = companyRepository.save(company1);

        //Verificar la salidad o el comportamiento, si las modificaciones fueron hechas
        assertThat(companyUpdated.getName()).isEqualTo("Compania prueba");
        assertThat(companyUpdated.getBanner()).isEqualTo("Banner prueba1");
    }

    @Test
    void companyDeleted(){
        //Configuracion previa, tener una compañia ya guardada
        companyRepository.save(company);

        //Llamar la funcionalidad, buscar la compañia por ID
        companyRepository.deleteById(company.getId());

        //Verificar la salidad o el comportamiento
        Optional<Company> deletedCompany = companyRepository.findById(company.getId());
        // Verificar que la compañía haya sido eliminada correctamente
        assertTrue(deletedCompany.isEmpty());
    }

}
