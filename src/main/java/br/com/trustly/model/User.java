package br.com.trustly.model;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="SIMUSU")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

		@Id 
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		@NotEmpty
		private String username;
		@NotEmpty
		private String email;
		@NotEmpty
		private String endereco;
		@NotEmpty
		private String password;
		private Boolean admin;
		
		public Boolean getAdmin() {
			return admin;
		}
		public void setAdmin(Boolean admin) {
			this.admin = admin;
		}
	
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return username;
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isEnabled() {
			// TODO -generated method stub
			return true;
		}

		
}
