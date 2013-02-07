/**
 *
 */
package example.controller.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import example.controller.response.HelloResponse;

@Path("/hello")
public class HelloWorldService {

    @Inject
    @Named("opning message")
    protected String opningMessage;

    @GET
    @Path("/world/{param}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public HelloResponse get(final @PathParam("param") String message) {

        HelloResponse result = new HelloResponse(opningMessage, message);

        return result;
    }

    @GET
    @Path("/worldasync")
    @Produces(MediaType.TEXT_PLAIN)
    public void getAsync(final @Suspend(10000) AsynchronousResponse response) {
        // XXX: this method not work

        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Response jaxrs = Response.ok("(message)").type(MediaType.TEXT_PLAIN).build();
                    response.setResponse(jaxrs);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

}
