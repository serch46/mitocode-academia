package com.mitocode.academia.service;

import com.mitocode.academia.modelo.Matricula;

import java.util.List;
import java.util.Map;

public interface IMatriculaService extends IGenericCRUDService<Matricula, Integer> {
    Map<String, List<String>> getCursoEstudiante();
}
