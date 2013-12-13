package beans;

import java.util.LinkedList;
import java.util.List;

public class File {
	private String fileName;
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

}
