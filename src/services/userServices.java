package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

@Path("users")
@Singleton
public class userServices {
	
	public userServices(){
		
	}
	
	
	@GET
	@Path("space/{nameSpace}")
	@Produces(MediaType.TEXT_HTML)
	public String getUserSpaceHtml(@PathParam("nameSpace") String nameSpace){
		return "<b> nameSpace </b>";
	}

}
