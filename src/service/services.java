package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.File;
import beans.FileData;

import com.sun.jersey.spi.resource.Singleton;

@Path("service")
@Singleton
public class services {

	public services() {

	}

	@GET
	@Path("space/{nameSpace}")
	@Produces(MediaType.TEXT_HTML)
	public String getUserSpaceHtml(@PathParam("nameSpace") String nameSpace) {
		return "<b>"+ nameSpace +"</b>";
	}

	@GET
	@Path("test/")
	@Produces(MediaType.TEXT_HTML)
	public String test() {
		return "<b> nameSpace </b>";
	}

	@GET
	@Path("getFile/{fileId}")
	@Produces(MediaType.TEXT_HTML)
	public String getFilesById(@PathParam("fileId") int fileId) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesById(fileId);
		return "<b>" + result.get(1).getFileName() + "</b>";
	}

}
