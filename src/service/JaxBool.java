package service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JaxBool {
	
    private boolean res;
    private String error;

    public boolean getRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

