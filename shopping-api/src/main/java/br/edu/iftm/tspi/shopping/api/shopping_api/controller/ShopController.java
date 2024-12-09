package br.edu.iftm.tspi.shopping.api.shopping_api.controller;

import br.edu.iftm.tspi.shopping.api.shopping_api.models.Shop;
import br.edu.iftm.tspi.shopping.api.shopping_api.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public List<Shop> getAll() {
        return shopService.getAll();
    }

    @GetMapping("/{id}")
    public Shop findById(@PathVariable String id) {
        return shopService.findById(id);
    }

    @PostMapping
    public Shop save(@RequestBody Shop shop) {
        return shopService.save(shop);
    }

    @GetMapping("/shopByUser")
    public List<Shop> getByUser(@RequestParam String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<Shop> getByDate(@RequestParam String startDate, @RequestParam String endDate) {
        return shopService.getByDate(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }

    @GetMapping("/{productIdentifier}")
    public List<Shop> findByProductIdentifier(@PathVariable String productIdentifier) {
        return shopService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/search")
    public List<Shop> getShopsByFilter(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam Double minValue) {
        return shopService.getShopsByFilter(
                LocalDateTime.parse(startDate),
                LocalDateTime.parse(endDate),
                minValue
        );
    }

    @GetMapping("/report")
    public Double getReportByDate(@RequestParam String startDate, @RequestParam String endDate) {
        return shopService.getByDate(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate))
                .stream()
                .mapToDouble(Shop::getTotal)
                .sum();
    }
}
