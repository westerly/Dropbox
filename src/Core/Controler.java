package Core;


import java.util.ArrayList;
import beans.SpaceData;


public class Controler {
	
	static public ArrayList getDirectParentsFilesOfNameSpace(String nameSpace) throws Exception{
		
		SpaceData spaceD = new SpaceData();
		ArrayList result = spaceD.getDirectParentsFiles(nameSpace);
		
		return result;
	}

}
