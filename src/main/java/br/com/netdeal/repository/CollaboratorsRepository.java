package br.com.netdeal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.netdeal.domain.Collaborators;

@Repository
public interface CollaboratorsRepository extends MongoRepository<Collaborators, String> {

}
