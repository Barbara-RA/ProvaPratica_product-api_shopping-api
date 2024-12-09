package br.edu.iftm.tspi.product_api.controller;

import lombok.RequiredArgsConstructor;
import br.edu.iftm.tspi.product_api.models.dto.CategoryDTO;
import br.edu.iftm.tspi.product_api.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable String id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        categoryService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<CategoryDTO> getAllPage(Pageable pageable) {
        return categoryService.getAllPage(pageable);
    }
}
