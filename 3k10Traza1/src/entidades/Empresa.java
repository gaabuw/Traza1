package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "sucursales") //Excluimos sucurssales para evitar recursion infinita
@Builder
public class Empresa {
    private Long id; //Agregamos un ID
    private String nombre;
    private String razonSocial;
    private Integer cuit;
    private String logo;

    @Builder.Default //Para que se inicialice aunque usemos lombock
    private Set<Sucursal> sucursales = new HashSet<>();
}
