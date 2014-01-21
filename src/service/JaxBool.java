package service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name = "JaxBool")
@XmlType(propOrder = {"res", "error"})
public class JaxBool {

	private boolean res;
	private String error;

	@XmlElement 
	public boolean getRes() {
		return this.res;
	}

	public void setRes(boolean pRes) {
		this.res = pRes;
	}

	@XmlElement  
	public String getError() {
		return this.error;
	}

	public void setError(String pError) {
		this.error = pError;
	}

}
