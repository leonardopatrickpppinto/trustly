package br.com.trustly.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FilesId implements Serializable {
	/**
	 * Pks
	 */
	private static final long serialVersionUID = 1L;

	private String nameFile;
	private String type;
    private String nameRepository;

    public FilesId() { }
     
	public FilesId(String nameFile, String type, String nameRepository) {
		this.nameFile = nameFile;
		this.type = type;
		this.nameRepository = nameRepository;
	}
     
	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameFile == null) ? 0 : nameFile.hashCode());
		result = prime * result + ((nameRepository == null) ? 0 : nameRepository.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilesId other = (FilesId) obj;
		if (nameFile == null) {
			if (other.nameFile != null)
				return false;
		} else if (!nameFile.equals(other.nameFile))
			return false;
		if (nameRepository == null) {
			if (other.nameRepository != null)
				return false;
		} else if (!nameRepository.equals(other.nameRepository))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


}