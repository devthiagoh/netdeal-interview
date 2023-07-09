package br.com.netdeal.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.netdeal.domain.Collaborators;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollaboratorDTO {
	
	private String id;
	private String name;
	private String password;
	private String score;
	private String complexity;
	private List<Collaborators> collaborators;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public List<Collaborators> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Collaborators> collaborators) {
		this.collaborators = collaborators;
	}
	
}
