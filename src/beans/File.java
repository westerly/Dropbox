package beans;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class File implements java.io.Serializable {
	/**
	 * 
	 */
	//private static final long serialVersionUID = -529994213106061035L;
	@XmlElement private int Id;
	@XmlElement private String fileName;
	@XmlElement private Integer id_folder_parent;
	@XmlElement private String name_space_parent;
	@XmlElement private Byte size;

	public File() {
		super();
	}

	public File(String fileName, Byte size, Integer fileId, Integer folderId,
			List<String> tags) {
		super();
		this.fileName = fileName;
		this.size = size;
		this.id_folder_parent = folderId;
		this.Id = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Byte getSize() {
		return size;
	}

	public void setSize(Byte size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + id_folder_parent;
		return result;
	}

	/**
	 * Two files are equal if their names and id are
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		File other = (File) obj;
		if (fileName == null) {
			if (other.fileName != null) {
				return false;
			}
		} else if (!fileName.equals(other.fileName)) {
			return false;
		}
		if (id_folder_parent != other.id_folder_parent) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "File [fileName=" + fileName + ", size=" + size + ", folderId="
				+ id_folder_parent + "]";
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer fileId) {
		this.Id = fileId;
	}

	public Integer getId_folder_parent() {
		return id_folder_parent;
	}

	public void setId_folder_parent(Integer id_folder_parent) {
		this.id_folder_parent = id_folder_parent;
	}

	public String getName_space_parent() {
		return name_space_parent;
	}

	public void setName_space_parent(String name_space_parent) {
		this.name_space_parent = name_space_parent;
	}

}
