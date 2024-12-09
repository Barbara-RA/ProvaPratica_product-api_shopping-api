package br.edu.iftm.tspi.product_api.services;

import lombok.RequiredArgsConstructor;
import br.edu.iftm.tspi.product_api.models.Product;
import br.edu.iftm.tspi.product_api.models.dto.ProductDTO;
import br.edu.iftm.tspi.product_api.repository.ProductRepository;
import br.edu.iftm.tspi.product_api.models.Category;
import br.edu.iftm.tspi.product_api.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO save(ProductDTO productDTO) {
        productDTO.setProductIdentifier(UUID.randomUUID().toString());
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product(
                null,
                productDTO.getProductIdentifier(),
                productDTO.getNome(),
                productDTO.getPreco(),
                productDTO.getDescricao(),
                category
        );

        product = productRepository.save(product);
        return ProductDTO.convert(product);
    }

    public ProductDTO findById(String id) {
        return productRepository.findById(id)
                .map(ProductDTO::convert)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductDTO update(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productDTO.getNome() != null) {
            product.setNome(productDTO.getNome());
        }
        if (productDTO.getPreco() != null) {
            product.setPreco(productDTO.getPreco());
        }
        if (productDTO.getDescricao() != null) {
            product.setDescricao(productDTO.getDescricao());
        }
        if (productDTO.getCategory() != null) {
            Category category = categoryRepository.findById(productDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        product = productRepository.save(product);
        return ProductDTO.convert(product);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public Page<ProductDTO> getAllPage(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::convert);
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        return ProductDTO.convert(productRepository.findByProductIdentifier(productIdentifier));
    }

    public List<ProductDTO> getProductsByCategoryId(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().getId().equals(category.getId()))
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }
}
