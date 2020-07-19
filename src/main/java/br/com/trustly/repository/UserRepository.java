package br.com.trustly.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.trustly.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	User findById(long id);
	User findByEmail(String email);
}
