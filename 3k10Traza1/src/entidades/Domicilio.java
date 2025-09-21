package entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"localidad", "sucursal"}) //Evitar recursion
@Builder
public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer cp;

    private Sucursal sucursal;
    private Localidad localidad;
}
