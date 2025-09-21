package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"domicilios", "provincia"}) //Evitar recursion infinita
@Builder
public class Localidad {
    private Long id;
    private String nombre;
    private Provincia provincia;

    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();

}
