package br.com.netdeal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.netdeal.domain.Collaborators;
import br.com.netdeal.domain.Hierarchy;
import br.com.netdeal.dto.CollaboratorDTO;
import br.com.netdeal.repository.CollaboratorsRepository;

@Service
public class CollaboratorsService {
	
	@Autowired
	private CollaboratorsRepository repository;
	
	public void list(Model model){
		initModel(model);
	}
	
	public void newCollaborator(Model model){
		model.addAttribute("collaborator", new CollaboratorDTO());
	}
	
	public void editCollaborator(Model model){
		
		if(Objects.isNull(model.getAttribute("editCollaborator")))
			model.addAttribute("editCollaborator", new CollaboratorDTO());
	}
	
	public void listAll(Model model){
		model.addAttribute("collaborators", repository.findAll());
	}
	
	public void save(CollaboratorDTO dto, Model model){
		
		if(Objects.nonNull(dto)){
			
			Collaborators collaborator = new Collaborators();			

			List<Collaborators> collaborators = dto.getCollaborators();
			collaborator.setHierarchies(new ArrayList<>());
			if(Objects.nonNull(collaborators))
				collaborators.forEach( c -> {
					Hierarchy h = new Hierarchy();
					h.setCollaborator(c);
					collaborator.getHierarchies().add(h);
				});
			collaborator.setId(dto.getId());
			collaborator.setName(dto.getName());
			collaborator.setPassword(dto.getPassword());
			collaborator.setComplexity("Forte");
			collaborator.setScore("25%");
			repository.save(collaborator);
			model.addAttribute("collaborator", collaborator);
		}
		
		initModel(model);
	}
	
	public void getCollaborator(String id, Model model) throws JsonProcessingException{
		
		Collaborators collaborator =  repository.findById(id).get();
		
		if(Objects.nonNull(collaborator)) {
			initModel(model);
			CollaboratorDTO dto = new CollaboratorDTO();
			dto.setId(collaborator.getId());
			dto.setName(collaborator.getName());
			dto.setScore(collaborator.getScore());
			dto.setComplexity(collaborator.getComplexity());

			List<Collaborators> collaborators = new ArrayList<>();
			collaborator.getHierarchies().forEach( hierarchy -> {
				Collaborators c = new Collaborators();
				c.setId(hierarchy.getCollaborator().getId());
				c.setName(hierarchy.getCollaborator().getName());
				collaborators.add(c);
			});
			dto.setCollaborators(collaborators);
			model.addAttribute("editCollaborator", dto);
		}		
	}
	
	public void delete(String id, Model model){
		
		Optional<Collaborators> collaborator =  repository.findById(id);
		
		if(Objects.nonNull(collaborator))
			repository.deleteById(id);
		
		initModel(model);
	}
	
	public void initModel(Model model){
		newCollaborator(model);
		editCollaborator(model);
		listAll(model);		
	}
}
