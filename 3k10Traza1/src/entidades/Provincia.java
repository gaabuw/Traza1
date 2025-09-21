package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"localidades", "pais"}) //Evitar recursion infinita
@Builder
public class Provincia {
    private Long id;
    private String nombre;
    private Pais pais;

    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();
}
