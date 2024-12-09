package br.edu.iftm.tspi.product_api.services;



import lombok.RequiredArgsConstructor;
import br.edu.iftm.tspi.product_api.models.Category;
import br.edu.iftm.tspi.product_api.models.dto.CategoryDTO;
import br.edu.iftm.tspi.product_api.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::convert)
                .collect(Collectors.toList());
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryRepository.save(new Category(null, categoryDTO.getNome()));
        return CategoryDTO.convert(category);
    }

    public CategoryDTO update(String id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setNome(categoryDTO.getNome());
        categoryRepository.save(category);
        return CategoryDTO.convert(category);
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    public Page<CategoryDTO> getAllPage(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDTO::convert);
    }
}
