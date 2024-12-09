package br.edu.iftm.tspi.shopping.api.shopping_api.services;


import br.edu.iftm.tspi.shopping.api.shopping_api.models.Item;
import br.edu.iftm.tspi.shopping.api.shopping_api.models.Shop;
import br.edu.iftm.tspi.shopping.api.shopping_api.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    public Shop findById(String id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    public Shop save(Shop shop) {
        shop.setDate(LocalDateTime.now());
        shop.setTotal(shop.getItems().stream().mapToDouble(Item::getPrice).sum());
        return shopRepository.save(shop);
    }

    public List<Shop> getByUser(String userIdentifier) {
        return shopRepository.findByUserIdentifier(userIdentifier);
    }

    public List<Shop> getByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return shopRepository.findByDateBetween(startDate, endDate);
    }

    public List<Shop> findByProductIdentifier(String productIdentifier) {
        return shopRepository.findByItemsProductIdentifier(productIdentifier);
    }

    public List<Shop> getShopsByFilter(LocalDateTime startDate, LocalDateTime endDate, Double minValue) {
        return shopRepository.findByDateBetween(startDate, endDate).stream()
                .filter(shop -> shop.getTotal() >= minValue)
                .toList();
    }
}
