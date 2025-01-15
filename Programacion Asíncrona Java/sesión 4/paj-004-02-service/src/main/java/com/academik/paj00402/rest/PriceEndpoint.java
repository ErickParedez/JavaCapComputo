package com.academik.paj00402.rest;

import com.academik.paj00402.service.PriceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/precios")
public class PriceEndpoint {

    @Inject
    private PriceService priceService;

    @GET
    @Path("/productos/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPrice(@PathParam("productId") Long productId) {
        return Response.ok(this.priceService.findBy(productId)).build();
    }
}
