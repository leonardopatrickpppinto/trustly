package br.com.trustly.model;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TPSREP")
public class Repositories {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

		@Id 
		private String nameRepository;
	   
		@OneToMany(cascade = { CascadeType.PERSIST})
		@JoinColumn(name="nameRepository")
		private List<Category> categories;
		
		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}

		public String getNameRepository() {
			return nameRepository;
		}

		public void setNameRepository(String nameRepository) {
			this.nameRepository = nameRepository;
		}				
}
