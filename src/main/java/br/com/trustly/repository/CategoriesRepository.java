package br.com.trustly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trustly.model.Category;
/**
 * Interface responsible for searching for a category by type
 * @author Leonardo Patrick
 *
 */
public interface CategoriesRepository
		extends JpaRepository<Category, String> {
	
	Category findByType(String type);

}
