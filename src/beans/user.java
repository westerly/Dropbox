package beans;

import java.beans.*;


public class user implements java.io.Serializable {
	

	private static final long serialVersionUID = -8275364031521024955L;
	private int id;
	private String login;
	private String pwd;
	
	
	public user(){}
	
	
	public int getId(){
		return this.id;
	}

	public String getLogin(){
		return this.login;
	}
	
	public String getPwd(){
		return this.pwd;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
}
