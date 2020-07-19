package br.com.trustly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.trustly.model.User;
import br.com.trustly.repository.UserRepository;

@Repository
public class ImplementsUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserRepository usr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user  = usr.findByUsername(username); 
		// TODO Auto-generated method stub
		
		if(user == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
			
		}
		return user;
	}

}
