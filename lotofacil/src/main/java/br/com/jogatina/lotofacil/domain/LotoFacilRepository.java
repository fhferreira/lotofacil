package br.com.jogatina.lotofacil.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LotoFacilRepository extends MongoRepository<JogoLotoFacil, Integer> {
	
	@Query(value = "{ 'bolas' : {$in : [?0] }}")
	public List<JogoLotoFacil> myFind(List<Integer> numeroSelecionados);

	public List<JogoLotoFacil> findByBolasIn(List<Integer> numeroSelecionados);

}
