package com.academik.paj00402clientint.rest;

import com.academik.paj00402clientint.service.FetchPriceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/precios")
public class PriceEndpoint {

    @Inject
    private FetchPriceService fetchPriceService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchPrices() {
        this.fetchPriceService.fetch();
        return Response.ok().build();
    }
}
