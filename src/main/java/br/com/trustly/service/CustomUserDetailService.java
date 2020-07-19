package br.com.trustly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.trustly.model.User;
import br.com.trustly.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService{
	private final UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository  = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = Optional.ofNullable(
				userRepository.findByUsername(username)).orElseThrow(()->new UsernameNotFoundException("Username not found"));
		
		List<GrantedAuthority> authorityAdmin = AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
		List<GrantedAuthority> authorityUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername()
											, user.getPassword(), user.getAdmin()? authorityAdmin: authorityUser);
				
	}

}
