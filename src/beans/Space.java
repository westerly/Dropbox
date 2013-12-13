package beans;

import java.util.LinkedList;
import java.util.List;

public class Space {
	private String spaceName;
	private Byte size;
	private Integer ownerId;
	private Integer spaceId;
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
		this.spaceId = spaceId;
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

	public Integer getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
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
		result = prime * result + ((spaceId == null) ? 0 : spaceId.hashCode());
		result = prime * result
				+ ((spaceName == null) ? 0 : spaceName.hashCode());
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
		if (spaceId == null) {
			if (other.spaceId != null) {
				return false;
			}
		} else if (!spaceId.equals(other.spaceId)) {
			return false;
		}
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
				+ ", ownerId=" + ownerId + ", spaceId=" + spaceId
				+ ", usersAllowsToRead=" + usersAllowsToRead + ", rights="
				+ rights + "]";
	}

}
