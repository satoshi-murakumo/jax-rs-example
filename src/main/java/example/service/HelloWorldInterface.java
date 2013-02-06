package example.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public interface HelloWorldInterface {

    @GET
    @Path("/{param}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public abstract HelloResponse get(@PathParam("param") String message);

}