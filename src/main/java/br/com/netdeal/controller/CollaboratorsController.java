package br.com.netdeal.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.netdeal.dto.CollaboratorDTO;
import br.com.netdeal.service.CollaboratorsService;

@Controller
@RequestMapping(value = "collaborators")
public class CollaboratorsController {

	private static final Logger log = LogManager.getLogger(CollaboratorsController.class);
	
	@Autowired
	private CollaboratorsService service;
	
	@GetMapping("list")
	public String list(Model model) {		
		
		try {
			service.list(model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao listar colaboradores!" +e.getMessage());
		}
		return "collaborators";
	}
	
	@ResponseBody
	@PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@Validated @RequestBody CollaboratorDTO dto, Model model) {
		
		try {
			service.save(dto, model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao salvar colaborador!" +e.getMessage());
		}	    
		
		return "collaborators";
	}
	
	@ResponseBody
	@GetMapping(value = "{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> edit(@PathVariable String id, Model model) {
		
		try {
			if (Objects.nonNull(id))
				service.getCollaborator(id, model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao consultar colaborador!" +e.getMessage());
		}	    
		
		return ResponseEntity.ok(model);
	}
	
	@DeleteMapping(value = "{id}/delete")
	public String delete(@PathVariable String id, Model model) {
		
		try {
			service.delete(id, model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao excluir colaborador!" +e.getMessage());
		}	    
		
		return "collaborators";
	}

	@ResponseBody
	@GetMapping(value = "{psswd}/validate",	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> validate(@PathVariable String psswd, Model model) {
		
		try {
			if (Objects.nonNull(psswd))
				service.validatePsswd(psswd, model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao validar colaborador!" +e.getMessage());
		}
		
		return ResponseEntity.ok(model);
	}
}
