package beans;

import java.util.LinkedList;
import java.util.List;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = -8275364031521024955L;
	private int id;
	private String login;
	private String pwd;
	private int spaceId;
	private List<Integer> spacesAvalibles = new LinkedList<Integer>();

	public User() {
	}

	public User(int id, String login, String pwd, int spaceId,
			List<Integer> spacesAvalibles) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.spaceId = spaceId;
		this.spacesAvalibles = spacesAvalibles;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	public List<Integer> getspacesAvalibles() {
		return spacesAvalibles;
	}

	public void setspacesAvalibles(List<Integer> spacesAvalibles) {
		this.spacesAvalibles = spacesAvalibles;
	}

	public int getId() {
		return this.id;
	}

	public String getLogin() {
		return this.login;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	/**
	 * Two users are equal if their logins are
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
		User other = (User) obj;
		if (id != other.id) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", pwd=" + pwd
				+ ", spaceId=" + spaceId + ", spacesAvalibles="
				+ spacesAvalibles + "]";
	}
	
}
