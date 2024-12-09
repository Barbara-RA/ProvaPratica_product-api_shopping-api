package br.edu.iftm.tspi.shopping.api.shopping_api.repository;


import br.edu.iftm.tspi.shopping.api.shopping_api.models.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {
    List<Shop> findByUserIdentifier(String userIdentifier);

    List<Shop> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Shop> findByItemsProductIdentifier(String productIdentifier);
}
