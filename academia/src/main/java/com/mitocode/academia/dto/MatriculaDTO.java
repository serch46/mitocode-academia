package com.mitocode.academia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDTO {
    private Integer idMatricula;

    @NotNull
    private LocalDate fechaMatricula;

    @NotNull
    @JsonIncludeProperties(value = {"idEstudiante"})
    private EstudianteDTO estudiante;

    @NotNull
    private boolean estado;

    @NotNull
    @JsonManagedReference
    private List<DetalleMatriculaDTO> detalleMatricula;

}
