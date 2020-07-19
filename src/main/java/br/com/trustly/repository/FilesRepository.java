package br.com.trustly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trustly.model.Files;

public interface FilesRepository
		extends JpaRepository<Files, Long> {
	
	Files findByNameFile(String nameFile);

}
