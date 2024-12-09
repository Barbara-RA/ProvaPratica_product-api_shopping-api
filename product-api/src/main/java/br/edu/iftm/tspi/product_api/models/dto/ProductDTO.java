package br.edu.iftm.tspi.product_api.models.dto;


import br.edu.iftm.tspi.product_api.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String productIdentifier;
    private String nome;
    private Double preco;
    private String descricao;
    private CategoryDTO category;

    public static ProductDTO convert(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductIdentifier(),
                product.getNome(),
                product.getPreco(),
                product.getDescricao(),
                CategoryDTO.convert(product.getCategory())
        );
    }
}
