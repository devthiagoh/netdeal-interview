package br.com.netdeal.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.netdeal.domain.Collaborators;
import br.com.netdeal.service.CollaboratorsService;

@Controller
@RequestMapping(value = "collaborators")
public class CollaboratorsController {

	private static final Logger log = LogManager.getLogger(CollaboratorsController.class);
	
	@Autowired
	private CollaboratorsService service;
	
	@GetMapping("list")
	public String list(Model model) {		
		
		service.newCollaborator(model);		
		service.listAll(model);
		
		return "collaborators";
	}
	
	@ResponseBody
	@PostMapping(value = "save", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String save(@Validated @RequestBody Collaborators collaborator, Model model) {
		
		try {
			service.save(collaborator, model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao salvar colaborador!" +e.getMessage());
		}	    
		
		return "collaborators";
	}
	
	@GetMapping(value = "{id}/edit")
	public String edit(@PathVariable String id, Model model) {
		
		try {

			if (Objects.nonNull(id))
				service.getCollaborator(id, model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao consultar colaborador!" +e.getMessage());
		}	    
		
		return "collaborators";
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
}
