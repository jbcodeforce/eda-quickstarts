package org.acme.eda.demo.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.eda.demo.domain.Product;
import org.acme.eda.demo.domain.ProductService;


@Path("/api/v1/products")
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return service.getProducts();
    }
}