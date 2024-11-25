package com.mitocode.academia.service.impl;

import com.mitocode.academia.modelo.Estudiante;
import com.mitocode.academia.repo.IEstudianteRepo;
import com.mitocode.academia.repo.IGenericRepo;
import com.mitocode.academia.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends GenericCRUDImpl<Estudiante,Integer> implements IEstudianteService {

    private final IEstudianteRepo estudianteRepo;

    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return estudianteRepo;
    }

    @Override
    public List<Estudiante> getOrdenaEstudiantePorEdad() {
        return estudianteRepo.findAll()
                .stream()
                .sorted((e1, e2)-> Integer.compare(e2.getEdad(), e1.getEdad()))
                .collect(Collectors.toList());
    }
}
