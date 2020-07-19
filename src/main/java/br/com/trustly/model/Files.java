package br.com.trustly.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="TPSFLE")
public class Files implements Serializable {
	
	private static final long serialVersionUID = 1L;

		@Id 
		private String nameFile;

		private String nameRepository;
		
		private String type;

		@NotEmpty
		private String url;

		private String linhas;
		
		private String bytes;
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getNameRepository() {
			return nameRepository;
		}

		public void setNameRepository(String nameRepository) {
			this.nameRepository = nameRepository;
		}
		
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getLinhas() {
			return linhas;
		}

		public void setLinhas(String linhas) {
			this.linhas = linhas;
		}

		public String getBytes() {
			return bytes;
		}

		public void setBytes(String bytes) {
			this.bytes = bytes;
		}
		public String getNameFile() {
			return nameFile;
		}

		public void setNameFile(String nameFile) {
			this.nameFile = nameFile;
		}


	
		
}
