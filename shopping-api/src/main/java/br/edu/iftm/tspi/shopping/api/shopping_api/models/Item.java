package br.edu.iftm.tspi.shopping.api.shopping_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String productIdentifier;
    private Double price;
}
