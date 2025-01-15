package com.academik.paj00402clientint.resources.client;


import com.academik.paj00402clientint.resources.dto.PriceDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.io.Serializable;

@RegisterRestClient(configKey = "price-rest-client")
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterProvider(PriceExceptionMapper.class)
public interface PriceRestClient extends Serializable {

    @GET
    @Path("/precios/productos/{productId}")
    PriceDTO findPrice(@PathParam("productId") Long productId);
}
