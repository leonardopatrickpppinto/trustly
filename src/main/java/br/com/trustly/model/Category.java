package br.com.trustly.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TPSCAT")
public class Category {
	
		@Id 
		private String type;

		private String nameRepository;
	    
		private Integer line =0;
		
		private Double bytes = 0.0D;

		private Integer qtd;
		
		@OneToMany(cascade = { CascadeType.PERSIST})
		@JoinColumn(name="nameRepository")
		private List<Files> files = new ArrayList<Files>();
		
		public Integer getLine() {
			return line;
		}

		public void setLine(Integer linhas) {
			this.line = linhas;
		}

		public Double getBytes() {
			return bytes;
		}

		public void setBytes(Double bytes) {
			this.bytes = bytes;
		}

		public String getNameRepository() {
			return nameRepository;
		}

		public void setNameRepository(String nameRepository) {
			this.nameRepository = nameRepository;
		}
		
		public List<Files> getFiles() {
			return files;
		}
		
		public void setFiles(List<Files> files) {
			this.files = files;
		}
		
	    public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		public Integer getQtd() {
			return qtd;
		}

		public void setQtd(Integer qtd) {
			this.qtd = qtd;
		}
		

}
