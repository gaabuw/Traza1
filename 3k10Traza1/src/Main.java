import entidades.*;
import repositorios.InMemoryRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //Inicializamos el repositorio inMemoryRepository
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();
        System.out.println("~~~~~~~~~~ SISTEMA INICIADO ~~~~~~~~~~");
        //Creamos un país
        Pais argentina = Pais.builder().nombre("Argentina")
                .build();
        //Creamos dos provincias
        Provincia buenosAires = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .pais(argentina) //relacionamos con el pais
                .build();
        Provincia cordoba = Provincia.builder()
                .id(2L)
                .nombre("Córdoba")
                .pais(argentina) //relacionamos con el pais
                .build();
        //Creamos localidades para cada provincia
        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("Caba")
                .provincia(buenosAires) //relacionamos con Baires
                .build();
        Localidad laPlata = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .provincia(buenosAires) //relacionamos con Baires
                .build();
        Localidad cordobaCapital = Localidad.builder()
                .id(3L)
                .nombre("Córdoba Capital")
                .provincia(cordoba)
                .build();
        Localidad villaCarlosPaz = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .provincia(cordoba)
                .build();
        //Creamos domicilios
        Domicilio dom1 = Domicilio.builder()
                .id(1L)
                .calle("Rodriguez")
                .numero(256)
                .cp(1001)
                .localidad(caba)
                .build();
        Domicilio dom2 = Domicilio.builder()
                .id(2L)
                .calle("Belgrano")
                .numero(102)
                .cp(1049)
                .localidad(laPlata)
                .build();
        Domicilio dom3 = Domicilio.builder()
                .id(3L)
                .calle("Lugones")
                .numero(23)
                .cp(5014)
                .localidad(cordobaCapital)
                .build();
        Domicilio dom4 = Domicilio.builder()
                .id(4L)
                .calle("Rodriguez")
                .numero(341)
                .cp(2473)
                .localidad(villaCarlosPaz)
                .build();

        //Sucursales para Baires
        Sucursal s1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal 1")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(true)
                .domicilio(dom1)
                .build();
        Sucursal s2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal 2")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(false)
                .domicilio(dom2)
                .build();
        //Sucursales para Cordoba
        Sucursal s3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal 3")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(true)
                .domicilio(dom3)
                .build();
        Sucursal s4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal 4")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .es_Casa_Matriz(false)
                .domicilio(dom4)
                .build();

        //Creamos empresas y asociamos sucursales
        Empresa empresa1 = Empresa.builder()
                .nombre("Empresa 1")
                .razonSocial("Razon Social 1")
                .cuit(301231234)
                .logo("imagenes/empresa1.png")
                .sucursales(new HashSet<>(Set.of(s1, s2)))
                .build();
        Empresa empresa2 = Empresa.builder()
                .nombre("Empresa 2")
                .razonSocial("Razon Social 2")
                .cuit(275566889)
                .logo("imagenes/empresa2.png")
                .sucursales(new HashSet<>(Set.of(s3, s4)))
                .build();

        //Asignamos empresa a sucursales para la bidireccionalidad
        s1.setEmpresa(empresa1);
        s2.setEmpresa(empresa1);
        s3.setEmpresa(empresa2);
        s4.setEmpresa(empresa2);

        //Guardamos las empresas en el repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        //Mostramos las empresas
        System.out.println("\nLista de Empresas:");
        List<Empresa> empresaList = empresaRepository.findAll();
        empresaList.forEach(System.out::println);

        //Buscamos una empresa por ID
        Optional<Empresa> empresaEncontrada = empresaRepository.findById(2L);
        empresaEncontrada.ifPresent(e -> System.out.println("\nEmpresa encontrada por ID 1:" + e));

        //Buscamos una empresa por NOMBRE
        List<Empresa> empresaNombre = empresaRepository.genericFindByField("nombre", "Empresa 2");
        System.out.println("Empresas encontrada por nombre 'Empresa 1': ");
        empresaNombre.forEach(System.out::println);

        //Actualizamos datos de una empresa buscando por su ID. Modificamos el cuit
        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .nombre("Empresa 1 Actualizada")
                .razonSocial("Razon Social 1")
                .cuit(271247983)
                .logo("imagenes/empresa1.png")
                .sucursales(empresa1.getSucursales())
                .build();

        empresaRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("\nEmpresa luego de actualizarla: " + e));

        //Eliminamos una empresa buscando su ID
        empresaRepository.genericDelete(2L);
        Optional<Empresa> empresaEliminada = empresaRepository.findById(2L);
        if(empresaEliminada.isEmpty()){
            System.out.println("\nLa Empresa con ID 2 ha sido eliminada.");
        }


    }
}