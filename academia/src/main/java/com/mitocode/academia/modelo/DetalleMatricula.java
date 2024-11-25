package com.mitocode.academia.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetalleMatricula;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_curso", foreignKey = @ForeignKey(name = "FK_DETAIL_CURSO"))
    private Curso curso;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_matricula", foreignKey = @ForeignKey(name = "FK_DETAIL_MATRICULA"))
    private Matricula matricula;

    @Column(nullable = false)
    private String aula;
}
