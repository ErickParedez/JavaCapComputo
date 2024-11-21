package edu.academik.demo2;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @GET
    public String sayHello() {
        return "Hello World";
    }

    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return "Hello World:  " + name;
    }
}
