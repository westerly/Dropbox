package beans;

import java.util.LinkedList;
import java.util.List;

public class Space implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2869911833463935177L;
	private String spaceName;
	private Byte size;
	private Integer ownerId;
	private List<User> usersAllowsToRead = new LinkedList<User>();
	private List<Rights> rights;

	public Space() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Space(String spaceName, Byte size, Integer ownerId, Integer spaceId,
			List<User> usersAllowsToRead, List<Rights> rights) {
		super();
		this.spaceName = spaceName;
		this.size = size;
		this.ownerId = ownerId;
		this.usersAllowsToRead = usersAllowsToRead;
		this.rights = rights;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public Byte getSize() {
		return size;
	}

	public void setSize(Byte size) {
		this.size = size;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}


	public List<User> getUsersAllowsToRead() {
		return usersAllowsToRead;
	}

	public void setUsersAllowsToRead(List<User> usersAllowsToRead) {
		this.usersAllowsToRead = usersAllowsToRead;
	}

	public List<Rights> getRights() {
		return rights;
	}

	public void setRights(List<Rights> rights) {
		this.rights = rights;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((spaceName == null) ? 0 : spaceName.hashCode());
		result = prime * result + ownerId;
		return result;
	}

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
		Space other = (Space) obj;
		if (spaceName == null) {
			if (other.spaceName != null) {
				return false;
			}
		} else if (!spaceName.equals(other.spaceName)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Space [spaceName=" + spaceName + ", size=" + size
				+ ", ownerId=" + ownerId + ", usersAllowsToRead=" + usersAllowsToRead + ", rights="
				+ rights + "]";
	}

}
