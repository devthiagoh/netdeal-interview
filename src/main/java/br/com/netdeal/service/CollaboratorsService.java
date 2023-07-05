package br.com.netdeal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.netdeal.domain.Collaborators;
import br.com.netdeal.repository.CollaboratorsRepository;

@Service
public class CollaboratorsService {
	
	@Autowired
	private CollaboratorsRepository repository;
	
	public void newCollaborator(Model model){
		model.addAttribute("collaborator", new Collaborators());
	}
	
	public void listAll(Model model){
		model.addAttribute("collaborators", repository.findAll());
	}
	
	public void save(Collaborators collaborator, Model model){
		
		if(Objects.nonNull(collaborator)){
			collaborator.setHierarchy("");
			collaborator.setComplexity("Forte");
			collaborator.setScore("25%");
			repository.save(collaborator);
			model.addAttribute("collaborator", collaborator);
		}
		
		newCollaborator(model);
		listAll(model);
	}
	
	public void getCollaborator(String id, Model model){
		
		Optional<Collaborators> collaborator =  repository.findById(id);
		
		if(Objects.nonNull(collaborator)) {
			System.out.println(id);
			System.out.println(collaborator);
			model.addAttribute("collaborator", collaborator);
		}		
	}
	
	public void delete(String id, Model model){
		
		Optional<Collaborators> collaborator =  repository.findById(id);
		
		if(Objects.nonNull(collaborator))
			repository.deleteById(id);
		
		newCollaborator(model);
		listAll(model);
	}
}
