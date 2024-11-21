package edu.academik.awjservice.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "hello-rest-client")
@Path("/data/hello")
@Consumes(MediaType.TEXT_PLAIN)
public interface HelloRestClient {

    @GET
    String getHello();

    @GET
    @Path("/{name}")
    String getHello(@PathParam("name") String name);

}
