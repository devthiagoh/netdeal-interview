package br.com.netdeal.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hierarchies")
public class Hierarchy {

	public static final String SEQUENCE_NAME = "hierarchies_sequence";

	@Id
	private String id;
	private Collaborators collaborator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collaborators getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborators collaborator) {
		this.collaborator = collaborator;
	}
		
}
