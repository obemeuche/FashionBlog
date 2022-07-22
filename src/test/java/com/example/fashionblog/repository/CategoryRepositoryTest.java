package com.example.fashionblog.repository;

import com.example.fashionblog.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ContextConfiguration(locations = {"classpath:/FashionBlog/src/main/resources/application-context.xml"})
class CategoryRepositoryTest {

    private final CategoryRepository categoryRepository;

    private final TestEntityManager entity;

    @Autowired
    CategoryRepositoryTest(CategoryRepository categoryRepository, TestEntityManager entity) {
        this.categoryRepository = categoryRepository;
        this.entity = entity;
    }

    @BeforeEach
    void setUp() {
        Category category =
                Category.builder()
                        .name("shoes")
                        .build();

        entity.persist(category);
    }

    @Test
    @DisplayName("Find Category By Id")
    public void whenFindById_thenReturnCategory(){

        Category mainCategory = categoryRepository.findById(1L).get();
        assertEquals(mainCategory.getName(), "shoes");
    }
}