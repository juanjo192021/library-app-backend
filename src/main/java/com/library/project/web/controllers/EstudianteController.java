package com.library.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.web.models.Estudiante;
import com.library.project.web.services.IEstudianteService;
import com.library.project.web.services.dto.EstudianteDTO;
import com.library.project.web.services.dto.EstudianteSaveDTO;
import com.library.project.web.services.dto.EstudianteUpdateDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/estudiantes/")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;
	
	@GetMapping("listar")
	public ResponseEntity<Object> listEstudiantes() {
		try {
			
			List<Estudiante> response = this.estudianteService.getListEstudiantes();
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/estudiante/{id}")
	public ResponseEntity<Object> idEstudiante(@PathVariable Long id) {
		try {
			EstudianteDTO response = this.estudianteService.buscarPorId(id);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> saveEstudiante(@RequestBody EstudianteSaveDTO estudiante) {
		try {
			EstudianteDTO response = this.estudianteService.guardar(estudiante);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<Object> updateEstudiante(@RequestBody EstudianteUpdateDTO estudiante) {
		try {
			EstudianteDTO response = this.estudianteService.update(estudiante);	
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}	
	}
	
}
