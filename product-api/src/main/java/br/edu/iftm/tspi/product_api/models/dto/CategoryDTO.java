package br.edu.iftm.tspi.product_api.models.dto;

import br.edu.iftm.tspi.product_api.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private String id;
    private String nome;

    public static CategoryDTO convert(Category category) {
        return new CategoryDTO(category.getId(), category.getNome());
    }
}
