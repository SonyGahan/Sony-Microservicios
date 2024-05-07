package com.vuelossony.proyecto.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.vuelossony.proyecto.model.Company;
import com.vuelossony.proyecto.model.Vuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Evita una autoconfiguracion
public class VueloRepositoryTest {
    @Autowired
    private VueloRepository vueloRepository;
    private Vuelo vuelo;
    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup(){
        vuelo = new Vuelo("Roma", "Buenos Aires","16-03-2024","17-03-2024",2600,"Semanal");
    }

    @Test
    void saveVueloTest(){
        //Configuracion previa en el metodo setup
        // Creo una instancia de compañía y guardo en la base de datos
        Company company = new Company(1L,"AirEuropa", "AirEuropa Banner", "https://www.aireuropa.com/");
        Company savedCompany = companyRepository.save(company);

        // Agrego la compañia al vuelo
        vuelo.setCompany(savedCompany);

        //Llamar la funcionalidad del metodo del repositorio, save
        Vuelo vueloBD = vueloRepository.save(vuelo);

        //Verificar la salida o el comportamiento en el test
        assertThat(vueloBD).isNotNull(); //Ver que el vuelo no esta vacio
        assertThat(vueloBD.getId()).isGreaterThan(0); //Ver que el ID es mayor a cero
        assertThat(vueloBD.getCompany()).isEqualTo(savedCompany);
    }

    @Test
    void vueloFindByIdTest(){
        //Configuracion previa, tener un vuelo ya guardado
        vueloRepository.save(vuelo);
        //Llamar la funcionalidad de buscar vuelo por ID y lo guardo en una variable
        Vuelo vueloBD = vueloRepository.findById(vuelo.getId()).get();
        //Verificar la salida o el comportamiento
        assertThat(vueloBD).isNotNull(); //Ver que el vuelo no esta vacio
    }

    @Test
    void vueloFindAllTest(){
        //Configuracion previa, seteo un vuelo nuevo vuelo2
        Vuelo vuelo2 = new Vuelo("Firenze", "Dubai","28-05-2024","29-05-2024",4200.0,"Diaria");
        //Guardo los dos vuelos
        vueloRepository.save(vuelo);
        vueloRepository.save(vuelo2);

        //Llamar la funcionalidad, busco todos los vuelos cargados
        List<Vuelo> vueloList = vueloRepository.findAll();

        //Verificar la salida o el comportamiento
        assertThat(vueloList).isNotNull(); //Ver que el vuelo no esta vacio
        assertThat(vueloList.size()).isEqualTo(2); //El largo de la lista es igual a la cantidad de vuelos
    }

    @Test
    void vueloDeleteById(){
        //Configuracion previa, tener un vuelo seteado
        vueloRepository.save(vuelo);
        //Llamar la funcionalidad, buscar el vuelo por ID
        vueloRepository.deleteById(vuelo.getId());

        //Verificar la salidad o el comportamiento
        Optional<Vuelo> deletedVuelo = vueloRepository.findById(vuelo.getId()); //Uso el optional porque ya borre ese ID y me devolvera un null. Valores que pueden ser nulos
        assertThat(deletedVuelo).isEmpty(); //Verifica que el vuelo esta vacio
    }

    @Test
    void vueloUpdateTest(){
        //Configuracion previa, tener un vuelo guardado
        vueloRepository.save(vuelo);
        //Buscar el vuelo por ID y guardarlo el vuelo en una variable
        Vuelo vueloBD = vueloRepository.findById(vuelo.getId()).get();

        //Modificar el vuelo, de Roma a Buenos Aires cambia por:
        vueloBD.setOrigen("Buenos Aires");
        vueloBD.setDestino("Milano");

        Vuelo vueloUpdated = vueloRepository.save(vueloBD); //Actualiza el vuelo

        //Verificar la salidad o el comportamiento, si las modificaciones fueron hechas, origen y destino
        assertThat(vueloUpdated.getOrigen()).isEqualTo("Buenos Aires");
        assertThat(vueloUpdated.getDestino()).isEqualTo("Milano");
    }

    @Test
    void vueloFindByOrigenTest(){
        //Configuracion previa, tener un vuelo ya guardado
        Vuelo vuelo2 = new Vuelo("Firenze", "Dubai","28-05-2024","29-05-2024",4200.0,"Diaria");
        //Guardo los dos vuelos
        vueloRepository.save(vuelo);
        vueloRepository.save(vuelo2);
        //Llamar la funcionalidad de buscar vuelo por ORIGEN y lo guardo en una variable
        List<Vuelo> vueloList = vueloRepository.findByOrigen(vuelo.getOrigen());
        //Verificar la salida o el comportamiento
        assertThat(vueloList).isNotNull(); //Ver que la lista de vuelos no esta vacia
        assertThat(vueloList.size()).isEqualTo(1); //Verificar que solo hay un vuelo en la lista
        assertThat(vueloList.get(0).getOrigen()).isEqualTo("Roma"); //Verificar que el origen del primer vuelo es "Roma"
    }

    @Test
    void vuelofindByOrigenAndDestinoTest(){
        //Configuracion previa, tener un vuelo ya guardado
        Vuelo vuelo2 = new Vuelo("Firenze", "Dubai","28-05-2024","29-05-2024",4200.0,"Diaria");
        //Guardo los dos vuelos
        vueloRepository.save(vuelo);
        vueloRepository.save(vuelo2);

        //Llamar la funcionalidad de buscar vuelo por Origen y Destino
        List<Vuelo> vueloList = vueloRepository.findByOrigenAndDestino(vuelo.getOrigen(), vuelo.getDestino());

        //Verificar la salida o el comportamiento
        assertThat(vueloList).isNotNull(); //Ver que la lista de vuelos no esta vacia
        assertThat(vueloList.size()).isEqualTo(1); //Verificar que solo hay un vuelo en la lista
        assertThat(vueloList.get(0).getOrigen()).isEqualTo("Roma"); //Verificar que el origen del primer vuelo es "Roma"
        assertThat(vueloList.get(0).getDestino()).isEqualTo("Buenos Aires"); // Verificar que el destino del primer vuelo es "Londres"
    }
}
