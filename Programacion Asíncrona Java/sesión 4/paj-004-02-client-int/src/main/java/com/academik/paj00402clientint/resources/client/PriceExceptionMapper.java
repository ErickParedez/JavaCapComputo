package com.academik.paj00402clientint.resources.client;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class PriceExceptionMapper implements ResponseExceptionMapper<RuntimeException> {
    
    @Override
    public RuntimeException toThrowable(Response response) {
        if (response.hasEntity()) {
            return new RuntimeException(response.getEntity().toString());
        }

        return new RuntimeException("Error en comunicación con servicio.");
    }
}
