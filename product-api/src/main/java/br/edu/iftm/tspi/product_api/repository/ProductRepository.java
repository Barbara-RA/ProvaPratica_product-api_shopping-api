package br.edu.iftm.tspi.product_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.edu.iftm.tspi.product_api.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductIdentifier(String productIdentifier);
}
