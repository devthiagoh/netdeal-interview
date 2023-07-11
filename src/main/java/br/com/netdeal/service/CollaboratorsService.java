package br.com.netdeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.netdeal.domain.Collaborators;
import br.com.netdeal.domain.Hierarchy;
import br.com.netdeal.dto.CollaboratorDTO;
import br.com.netdeal.dto.ComplexityAndScoreDTO;
import br.com.netdeal.repository.CollaboratorsRepository;
import br.com.netdeal.util.Util;

@Service
public class CollaboratorsService {
	
	private static final Logger log = LogManager.getLogger(CollaboratorsService.class);
	
	private static final String VERY_STRONG = "Very Strong";
	private static final String STRONG = "Strong";
	private static final String GOOD = "Good";
	private static final String WEAK = "Weak";
	private static final String VERY_WEAK = "Very Weak";
	private static final String TOO_SHORT = "Too Short";
	
	private static Map<String, String> classes = new HashMap<>();
	
	static {
		classes.put(VERY_STRONG, "very-strong");
		classes.put(STRONG, "strong");
		classes.put(GOOD, "good");
		classes.put(WEAK, "weak");
		classes.put(VERY_WEAK, "very-weak");
		classes.put(TOO_SHORT, "too-short");
	}	
	
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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
				collaborator.setScore(getScore(dto.getPassword()));
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
		
		Collaborators collaborator =  repository.findById(id).orElseGet(null);
		
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
		ComplexityAndScoreDTO complexityAndScore = calculateComplexityAndScore(psswd);
		return complexityAndScore.getComplexity();
	}
	
	private String getScore(String psswd) {
		ComplexityAndScoreDTO complexityAndScore = calculateComplexityAndScore(psswd);
		return complexityAndScore.getScore();
	}
	
	public boolean validatePsswd(String psswd, Model model) {
		
		log.info("validatePsswd...");
		boolean valid = false;
		
		if(psswd.isEmpty()) 
			return valid;
		if(psswd.length() < 2)
			return valid;
		
		valid = validateComplexity(psswd, model);
		valid = true;
		return valid;
	}
	
	private boolean validateComplexity(String psswd, Model model) {
		
		log.info("validateComplexity...");
		boolean valid = false;
		
		ComplexityAndScoreDTO complexityAndScore = calculateComplexityAndScore(psswd);
				
		String complexity = complexityAndScore.getComplexity();
		model.addAttribute("complexity", complexity);		
		model.addAttribute("class", classes.get(complexity));
		
//		if(complexity.equals(Util.VERY_STRONG) ||
//				complexity.equals(Util.STRONG) ||
//				complexity.equals(Util.GOOD))
			valid = true;
		
		return valid;
	}
	
	
	
	private ComplexityAndScoreDTO calculateComplexityAndScore(String psswd){
		
		log.info("caluculateComplexityAndScore...");
		
		ComplexityAndScoreDTO complexityAndScore = Util.calculateComplexityAndScore(psswd);
		
		return complexityAndScore;
	}
}

