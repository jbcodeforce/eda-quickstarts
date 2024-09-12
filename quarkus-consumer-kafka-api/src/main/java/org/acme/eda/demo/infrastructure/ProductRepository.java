package org.acme.eda.demo.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Singleton;

import org.acme.eda.demo.domain.Product;

@Singleton
public class ProductRepository {
    
    private static HashMap<String,Product> repo = new HashMap<String,Product>();

    public ProductRepository() {
        super();
    }

    public List<Product> getAll(){
        return new ArrayList<Product>(repo.values());
    }

    public void addProduct(Product p) {
        repo.put(p.product_id, p);
    }
}
