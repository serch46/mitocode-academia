package com.mitocode.academia.service.impl;

import com.mitocode.academia.modelo.Matricula;
import com.mitocode.academia.repo.IMatriculaRepo;
import com.mitocode.academia.repo.IGenericRepo;
import com.mitocode.academia.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends GenericCRUDImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepo matriculaRepo;

    @Override
    protected IGenericRepo<Matricula, Integer> getRepo(){
        return matriculaRepo;
    }

    @Override
    public Map<String, List<String>> getCursoEstudiante() {
        // Obtiene todas las matrículas desde el repositorio.
        return matriculaRepo.findAll()
                .stream() // Convierte la lista de matrículas en un flujo para aplicar operaciones funcionales.
                .flatMap(matricula ->
                        // Por cada matrícula, se obtiene un flujo de sus detalles (DetalleMatricula).
                        matricula.getDetalleMatricula().stream()
                                // Se mapea cada DetalleMatricula a una entrada de mapa (clave-valor):
                                // Clave: el nombre del curso asociado al detalle.
                                // Valor: el nombre completo del estudiante asociado a la matrícula.
                                .map(detalle -> Map.entry(
                                        detalle.getCurso().getNombre(), // Nombre del curso como clave.
                                        matricula.getEstudiante().getNombre() + " " + matricula.getEstudiante().getApellido() // Nombre completo del estudiante como valor.
                                ))
                )
                // Agrupa las entradas por clave (nombre del curso).
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey, // Agrupa por la clave, que es el nombre del curso.
                        Collectors.mapping(
                                Map.Entry::getValue, // Extrae el valor (nombre completo del estudiante).
                                Collectors.toList() // Recolecta todos los valores en una lista.
                        )
                ));
    }

}
