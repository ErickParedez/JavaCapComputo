package com.academik.paj00402clientint.repository;

import com.academik.paj00402clientint.domain.Product;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Log
public class ProductRepository {

    public List<Product> getProducts() {

        var list = new ArrayList<Product>();

        for (long i = 1; i <= 10; i++) {
            var product = new Product();
            product.setProductId(i);
            list.add(product);
        }

        return list;
    }

}
