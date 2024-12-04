package edu.academik.awjservice.rest;

import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import edu.academik.awjservice.request.CreateBookRequest;
import edu.academik.awjservice.request.UpdatePriceBookRequest;
import edu.academik.awjservice.restclient.HelloRestClient;
import edu.academik.awjservice.service.BookService;
import edu.academik.awjservice.service.CreateBookService;
import edu.academik.awjservice.service.UpdatePriceBookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/books")
public class BookEndpoint {

    @Inject
    private BookService bookService;

    @Inject
    private CreateBookService createBookService;

    @Inject
    private UpdatePriceBookService updatePriceBookService;

    @Inject
    @RestClient
    private HelloRestClient helloRestClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        var result = this.bookService.findAll();
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        var result = this.bookService.findByIdAsDTO(id);
        return Response.ok(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid CreateBookRequest request) {
        var createBookResponse = this.createBookService.create(request);
        return Response.ok(createBookResponse).build();
    }

    @PUT
    @Path("/prices")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UpdatePriceBookRequest request) {
        this.updatePriceBookService.update(request);
        return Response.ok().build();
    }

    @GET
    @Path("/hello")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        var message = this.helloRestClient.getHello();

        return Response.ok(Map.of("message", message)).build();
    }

    @GET
    @Path("/hello/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("name") String name) {

        var message = this.helloRestClient.getHello(name);

        return Response.ok(Map.of("message", message)).build();
    }
}
