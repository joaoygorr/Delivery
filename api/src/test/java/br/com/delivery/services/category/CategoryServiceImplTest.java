package br.com.delivery.services.category;

import br.com.delivery.modules.category.Category;
import br.com.delivery.modules.category.mapper.CategoryMapper;
import br.com.delivery.records.category.CategoryRecord;
import br.com.delivery.repositories.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Autowired
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("Should create a category and return the saved entity")
    void shouldCreateCategory() {
        Category input = new Category(1L, "categoria");
        when(this.categoryRepository.save(input)).thenReturn(input);

        Category result = this.categoryService.createCategory(input);

        assertNotNull(result);
        assertEquals(1L, result.getIdCategory());
        assertEquals("categoria", result.getName());

        verify(categoryRepository, times(1)).save(input);
    }

    @Test
    @DisplayName("Should return the category when the ID exists")
    void shouldReturnCategoryById() {
        Category input = new Category(1L, "categoria");
        when(this.categoryRepository.findById(1L)).thenReturn(Optional.of(input));

        Category result = this.categoryService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdCategory());
        assertEquals("categoria", result.getName());

        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when ID is null")
    void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            categoryService.getById(null);
        });
    }

    @Test
    @DisplayName("Should delete category by ID")
    void shouldDeleteCategoryById() {
        Category category = new Category(1L, "categoria");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should update category by ID")
    void shouldUpdateCategoryById() {
        Category existingCategory = new Category(1L, "Old Category");
        CategoryRecord categoryRecord = new CategoryRecord(1L,"Updated Category");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));

        Category updatedCategory = categoryService.updateCategory(1L, categoryRecord);

        assertNotNull(updatedCategory);
        assertEquals(1L, updatedCategory.getIdCategory());
        assertEquals("Updated Category", updatedCategory.getName());

        verify(categoryMapper, times(1)).updateCategory(categoryRecord, existingCategory);
    }

    @Test
    @DisplayName("Should return paginated list of categories")
    void shouldReturnPaginatedListOfCategories() {
        Category category1 = new Category(1L, "Bebidas");
        Category category2 = new Category(2L, "Lanches");

        List<Category> categories = List.of(category1, category2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> categoryPage = new PageImpl<>(categories, pageable, categories.size());

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);

        Page<Category> result = categoryService.getCategories(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("Bebidas", result.getContent().get(0).getName());
        assertEquals("Lanches", result.getContent().get(1).getName());

        verify(categoryRepository, times(1)).findAll(pageable);
    }
}