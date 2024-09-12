package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Eleitor;
import app.service.EleitorService;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("api/eleitor")
public class EleitorController {
	
	@Autowired
	EleitorService eleitorService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody Eleitor eleitor){
		String mensagem;
		try {
			Eleitor eleitorSalvo = this.eleitorService.save(eleitor);
			if(eleitorSalvo != null) {
				mensagem = "Eleitor salvo com sucesso!";
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			} else {
				mensagem = "Erro ao salvar eleitor";
				return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@Valid @RequestBody Eleitor eleitor, @PathVariable long id){
		String mensagem;
		try {
			Eleitor eleitorAtualizado = this.eleitorService.update(eleitor, id);
			if(eleitorAtualizado != null) {
				mensagem = "Eleitor atualizado com sucesso!";
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			} else {
				mensagem = "Erro ao atualizar eleitor";
				return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Eleitor>> findAll(){
		try {
			List<Eleitor> listaEleitores = this.eleitorService.findAll();
			return new ResponseEntity<>(listaEleitores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			String mensagem = this.eleitorService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
