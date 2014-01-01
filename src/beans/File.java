package beans;

import java.util.LinkedList;
import java.util.List;

public class File implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -529994213106061035L;
	private int Id;
	private String fileName;
	private Integer id_folder_parent;
	private String name_space_parent;
	private Byte size;
	private Integer folderId;
	private Integer fileId;
	private List<String> tags = new LinkedList<String>();

	public File() {
		super();
	}

	public File(String fileName, Byte size, Integer fileId, Integer folderId,
			List<String> tags) {
		super();
		this.fileName = fileName;
		this.size = size;
		this.folderId = folderId;
		this.fileId = fileId;
		this.tags = tags;
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

	public Integer getFolderId() {
		return folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
		result = prime * result + folderId;
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
		if (folderId != other.folderId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "File [fileName=" + fileName + ", size=" + size + ", folderId="
				+ folderId + "]";
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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
