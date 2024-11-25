package com.mitocode.academia.controller;

import com.mitocode.academia.dto.EstudianteDTO;
import com.mitocode.academia.modelo.Estudiante;
import com.mitocode.academia.service.IEstudianteService;
import com.mitocode.academia.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService estudianteService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> findAll() throws Exception{
        List<EstudianteDTO> list = mapperUtil.mapList(estudianteService.findAll(), EstudianteDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> findById(@PathVariable("id") Integer id) throws Exception{
        Estudiante estudiante = estudianteService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(estudiante, EstudianteDTO.class));
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> save(@Valid @RequestBody EstudianteDTO estudianteDTO) throws Exception{
        Estudiante  estudiante = estudianteService.save(mapperUtil.map(estudianteDTO, Estudiante.class));
        return new ResponseEntity<>(mapperUtil.map(estudiante, EstudianteDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody EstudianteDTO estudianteDTO) throws Exception{
        Estudiante estudiante = estudianteService.update(id, mapperUtil.map(estudianteDTO, Estudiante.class));
        return new ResponseEntity<>(mapperUtil.map(estudiante, EstudianteDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        estudianteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/poredad")
    public ResponseEntity<List<Estudiante>> getOrdenaEstudiantePorEdad(){
        List<Estudiante> estudiantes = estudianteService.getOrdenaEstudiantePorEdad();
        return ResponseEntity.ok(estudiantes);
    }

}
