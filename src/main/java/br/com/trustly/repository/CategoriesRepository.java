package br.com.trustly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trustly.model.Category;

public interface CategoriesRepository
		extends JpaRepository<Category, String> {
	
	Category findByType(String type);

}
