package com.mitocode.academia.service;

import com.mitocode.academia.modelo.Estudiante;

import java.util.List;

public interface IEstudianteService extends IGenericCRUDService<Estudiante, Integer> {

    List<Estudiante> getOrdenaEstudiantePorEdad();
}
