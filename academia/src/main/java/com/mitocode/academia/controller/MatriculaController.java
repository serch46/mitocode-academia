package com.mitocode.academia.controller;

import com.mitocode.academia.dto.MatriculaDTO;
import com.mitocode.academia.modelo.Matricula;
import com.mitocode.academia.service.IMatriculaService;
import com.mitocode.academia.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final IMatriculaService matriculaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll() throws Exception{
        List<MatriculaDTO> list = mapperUtil.mapList(matriculaService.findAll(), MatriculaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable("id") Integer id) throws Exception{
        Matricula matricula = matriculaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(matricula, MatriculaDTO.class));
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> save(@Valid @RequestBody MatriculaDTO matriculaDTO) throws Exception{
        Matricula  matricula = matriculaService.save(mapperUtil.map(matriculaDTO, Matricula.class));
        return new ResponseEntity<>(mapperUtil.map(matricula, MatriculaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody MatriculaDTO matriculaDTO) throws Exception{
        Matricula matricula = matriculaService.update(id, mapperUtil.map(matriculaDTO, Matricula.class));
        return new ResponseEntity<>(mapperUtil.map(matricula, MatriculaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        matriculaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cursoestudiante")
    public ResponseEntity<Map<String,List<String>>> findCursoestudiante() throws Exception{
        Map<String,List<String>> cursoEstudiante = matriculaService.getCursoEstudiante();
        return ResponseEntity.ok(cursoEstudiante);
    }

}
