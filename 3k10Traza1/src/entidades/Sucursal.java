package entidades;

import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "empresa") //Para evitar recursion
@Builder
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Matriz;

    private Domicilio domicilio;//Los hacemos bidireccionales
    private Empresa empresa;    //

}
