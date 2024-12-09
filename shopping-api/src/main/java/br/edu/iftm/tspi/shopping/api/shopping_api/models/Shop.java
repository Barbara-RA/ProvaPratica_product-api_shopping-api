package br.edu.iftm.tspi.shopping.api.shopping_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shop")
public class Shop {
    @Id
    private String id;
    private String userIdentifier;
    private LocalDateTime date;
    private List<Item> items;
    private Double total;
}
