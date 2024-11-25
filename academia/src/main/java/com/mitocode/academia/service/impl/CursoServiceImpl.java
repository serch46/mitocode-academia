package com.mitocode.academia.service.impl;

import com.mitocode.academia.modelo.Curso;
import com.mitocode.academia.repo.ICursoRepo;
import com.mitocode.academia.repo.IGenericRepo;
import com.mitocode.academia.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends GenericCRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo cursoRepo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo(){
        return cursoRepo;
    }
}
