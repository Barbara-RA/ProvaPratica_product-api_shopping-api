package br.edu.iftm.tspi.product_api.controller;

import lombok.RequiredArgsConstructor;
import br.edu.iftm.tspi.product_api.models.dto.ProductDTO;
import br.edu.iftm.tspi.product_api.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @GetMapping("/{productIdentifier}/identifier")
    public ProductDTO findByProductIdentifier(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategoryId(@PathVariable String categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @PostMapping
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        productService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getAllPage(Pageable pageable) {
        return productService.getAllPage(pageable);
    }
}
