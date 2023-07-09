package br.com.netdeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger log = LogManager.getLogger(CollaboratorsService.class);
	
	private static final String VERY_STRONG = "Very Strong";
	private static final String STRONG = "Strong";
	private static final String GOOD = "Good";
	private static final String WEAK = "Weak";
	private static final String VERY_WEAK = "Very Weak";
	private static final String TOO_SHORT = "Too Short";
	
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
	
	public void save(CollaboratorDTO dto, Model model) throws Exception{
		
		if(Objects.nonNull(dto)){
			
			boolean valid = validatePsswd(dto.getPassword(), model);
			
			if(valid) {				
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
				collaborator.setComplexity(getComplexity(dto.getPassword()));
				collaborator.setScore(calculateScore(dto.getPassword()));
				repository.save(collaborator);
			} else {
				throw new Exception();
			}
			initModel(model);
		}
	}
	
	public void getCollaborator(String id, Model model) throws JsonProcessingException{
		
		Collaborators collaborator =  repository.findById(id).get();
		
		if(Objects.nonNull(collaborator)) {
			initModel(model);
			CollaboratorDTO dto = new CollaboratorDTO();
			dto.setId(collaborator.getId());
			dto.setName(collaborator.getName());
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
	
	private String getComplexity(String psswd) {
		Map<Boolean, String> complexity = calculateComplexity(psswd);
		return complexity.get(true);
	}
	
	public boolean validatePsswd(String psswd, Model model) {
		
		log.info("validatePsswd...");
		boolean valid = false;
		
		if(psswd.isEmpty()) 
			return valid;
		if(psswd.length() < 2)
			return valid;
		
		valid = validateComplexity(psswd, model);
		valid = calculateScore(psswd, model);

		return valid;
	}
	
	private boolean validateComplexity(String psswd, Model model) {
		
		log.info("validateComplexity...");
		boolean valid = false;
		
		Map<Boolean, String> complexity = calculateComplexity(psswd);
		
		if(Objects.nonNull(complexity.get(true)) && 
				(complexity.get(true).equals(VERY_STRONG) || 
				 complexity.get(true).equals(STRONG)) ||
				 complexity.get(true).equals(GOOD))
			valid = true;
		
		log.info(complexity.get(true));
		String collaboratorComplexity = complexity.get(true);
		model.addAttribute("complexity", collaboratorComplexity);
		return valid;
	}
	
	private Map<Boolean, String> calculateComplexity(String psswd) {
		log.info("calculateComplexity...");
		
		Map<Boolean, String> complexity = new HashMap<>();
		
		if(psswd.length() > 15)
			complexity.put(true, VERY_STRONG);		
		if(psswd.length() > 8 && psswd.length() <= 15)
			complexity.put(true, STRONG);
		if(psswd.length() > 5 && psswd.length() <= 8)
			complexity.put(true, GOOD);
		if(psswd.length() == 0 && psswd.length() <= 5)
			complexity.put(false, WEAK);
		if(psswd.length() == 0 )
			complexity.put(false, VERY_WEAK);
		
		return complexity;
	}
	
	private boolean calculateScore(String psswd, Model model) {
		boolean valid = false;
		
		String score = calculateScore(psswd);
		
		if(score.equals("valid"));
			valid = true;
			
		return valid;
	}
	
	private String calculateScore(String psswd) {
		log.info("calculateScore...");
		String score = "";
		
		return score;
	}
}
