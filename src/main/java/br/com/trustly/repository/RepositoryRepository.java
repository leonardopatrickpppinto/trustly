package br.com.trustly.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.com.trustly.model.Repositories;
/**
 * Interface responsible for searching for a Repositories by name repository
 * @author Leonardo Patrick
 *
 */
public interface RepositoryRepository extends CrudRepository<Repositories, String> {
	
	List<Repositories> findByNameRepository(String nameRepository);
}
