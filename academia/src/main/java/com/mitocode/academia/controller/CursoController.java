package com.mitocode.academia.controller;

import com.mitocode.academia.dto.CursoDTO;
import com.mitocode.academia.modelo.Curso;
import com.mitocode.academia.service.ICursoService;
import com.mitocode.academia.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService cursoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() throws Exception{
        List<CursoDTO> list = mapperUtil.mapList(cursoService.findAll(), CursoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable("id") Integer id) throws Exception{
        Curso curso = cursoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(curso, CursoDTO.class));
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@Valid @RequestBody CursoDTO cursoDTO) throws Exception{
        Curso  curso = cursoService.save(mapperUtil.map(cursoDTO, Curso.class));
        return new ResponseEntity<>(mapperUtil.map(curso, CursoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CursoDTO cursoDTO) throws Exception{
        Curso curso = cursoService.update(id, mapperUtil.map(cursoDTO, Curso.class));
        return new ResponseEntity<>(mapperUtil.map(curso, CursoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
