package br.com.trustly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trustly.model.Files;
/**
 * Interface responsible for searching for a files by name File
 * @author Leonardo Patrick
 *
 */
public interface FilesRepository
		extends JpaRepository<Files, Long> {
	
	Files findByNameFile(String nameFile);

}
