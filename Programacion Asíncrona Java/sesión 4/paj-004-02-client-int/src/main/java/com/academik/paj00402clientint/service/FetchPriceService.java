package com.academik.paj00402clientint.service;

import com.academik.paj00402clientint.domain.Product;
import com.academik.paj00402clientint.repository.ProductRepository;
import com.academik.paj00402clientint.resources.client.PriceRestClient;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@Log
public class FetchPriceService {

    @Inject
    private ProductRepository productRepository;

    @RestClient
    @Inject
    private PriceRestClient priceRestClient;

    @Resource
    private ManagedExecutorService managedExecutorService;

    @Inject
    private UpdatePriceService updatePriceService;

    public void fetch() {
        log.info("Actualizando precios ...");
        this.productRepository.getProducts().forEach(product -> CompletableFuture.runAsync(() -> this.handlePrice(product)));
    }

    private void handlePrice(Product product) {
        CompletableFuture.supplyAsync(() -> this.priceRestClient.findPrice(product.getProductId()), this.managedExecutorService)
                .thenAccept(price -> this.updatePriceService.updatePrice(price))
                .exceptionally(ex -> {
                    this.updatePriceService.updatePriceToZero(product.getProductId(), ex.getMessage());
                    return null;
                });
    }
}
