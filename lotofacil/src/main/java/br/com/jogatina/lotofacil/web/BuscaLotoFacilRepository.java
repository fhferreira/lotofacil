package br.com.jogatina.lotofacil.web;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.jogatina.lotofacil.config.MongoConfiguration;
import br.com.jogatina.lotofacil.domain.JogoLotoFacil;
import br.com.jogatina.lotofacil.domain.LotoFacilRepository;

@Component
public class BuscaLotoFacilRepository {
	
	@Autowired
	private LotoFacilRepository lotoFacilRepository;
	
	@Autowired
	private MongoConfiguration mongoConfiguration;
	
	private List<Integer> numeroSelecionados;

	private Integer de;

	private Integer ate;
	
	public List<JogoLotoFacil> buscar(String buscaNome, Integer pagina) {
		Pageable pageable = new PageRequest(pagina, 50, new Sort(Sort.Direction.ASC, "concurso"));
		
		if(buscaNome.equals("BuscaTodos")){
			return lotoFacilRepository.findAll(pageable).getContent();
		}
		
		if(buscaNome.equals("BuscaPalpite")){
			
			try {
				
				return mongoConfiguration.mongoTemplate().find(query(where("bolas").all(numeroSelecionados)).with(pageable), JogoLotoFacil.class);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(buscaNome.endsWith("DeAte")){
			try {
				//org.springframework.data.mongodb.InvalidMongoDbApiUsageException: Due to limitations of the com.mongodb.BasicDBObject, you can't add a second 'concurso' expression specified as 'concurso : { "$lt" : 1200}'. Criteria already contains 'concurso : { "$gte" : 1100}'.
				//return mongoConfiguration.mongoTemplate().find(query(where("concurso").gte(de).and("concurso").lt(ate)).with(pageable), JogoLotoFacil.class);
				
				return mongoConfiguration.mongoTemplate().find(query(where("concurso").gte(de).lt(ate)).with(pageable), JogoLotoFacil.class);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public void setNumeroSelecionados(List<Integer> numeroSelecionados) {
		this.numeroSelecionados = numeroSelecionados;
	}

	public void setDe(Integer de) {
		this.de = de;
	}

	public void setAte(Integer ate) {
		this.ate = ate;
	}

}
