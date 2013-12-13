package beans;

import java.util.LinkedList;
import java.util.List;

public class Folder {
	private String folderName;
	private Byte size;
	private Integer numberOfFiles;
	private Integer spaceId;
	private Integer folderId;
	private List<File> files = new LinkedList<File>();

	public Folder() {
		super();
	}

	public Folder(String folderName, Byte size, Integer numberOfFiles,
			Integer spaceId, Integer folderId, List<File> files) {
		super();
		this.folderName = folderName;
		this.size = size;
		this.numberOfFiles = numberOfFiles;
		this.spaceId = spaceId;
		this.folderId = folderId;
		this.files = files;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName
	 *            the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	/**
	 * @return the size
	 */
	public Byte getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Byte size) {
		this.size = size;
	}

	/**
	 * @return the numberOfFiles
	 */
	public Integer getNumberOfFiles() {
		return numberOfFiles;
	}

	/**
	 * @param numberOfFiles
	 *            the numberOfFiles to set
	 */
	public void setNumberOfFiles(Integer numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}

	/**
	 * @return the spaceId
	 */
	public Integer getSpaceId() {
		return spaceId;
	}

	/**
	 * @param spaceId
	 *            the spaceId to set
	 */
	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}

	/**
	 * @return the folderId
	 */
	public Integer getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId
	 *            the folderId to set
	 */
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	/**
	 * @return the files
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * @param files
	 *            the files to set
	 */
	public void setFiles(List<File> files) {
		this.files = files;
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
		result = prime * result + folderId;
		result = prime * result
				+ ((folderName == null) ? 0 : folderName.hashCode());
		result = prime * result + ((spaceId == null) ? 0 : spaceId.hashCode());
		return result;
	}

	/**
	 ** Two folders are equal if their names and id are
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (folderId != other.folderId)
			return false;
		if (folderName == null) {
			if (other.folderName != null)
				return false;
		} else if (!folderName.equals(other.folderName))
			return false;
		if (spaceId == null) {
			if (other.spaceId != null)
				return false;
		} else if (!spaceId.equals(other.spaceId))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Folder [folderName=" + folderName + ", size=" + size
				+ ", numberOfFiles=" + numberOfFiles + ", spaceId=" + spaceId
				+ ", folderId=" + folderId + ", files=" + files + "]";
	}

}
