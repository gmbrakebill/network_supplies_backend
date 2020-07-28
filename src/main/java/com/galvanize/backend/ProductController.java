package com.galvanize.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public Iterable<Product> getAll()
    {
        return this.productRepository.findAll();
    }
    @PostMapping("/add-product")
    public String createProduct(@RequestBody Product product)
    {
        this.productRepository.save(product);
        return"Product added";
    }
    @DeleteMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
        this.productRepository.deleteById(id);
        return "product deleted";
    }
}
