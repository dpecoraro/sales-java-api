package org.udemy.spring.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.udemy.spring.course.domain.bo.Product;
import org.udemy.spring.course.repositories.ProductDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductDAO dao;

    @GetMapping()
    public List<Product> get() {
        return dao.findAll();
    }

    @GetMapping("{id}")
    public Product get(@PathVariable Integer id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not on the list"));
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        return dao.findById(id)
                .map(d -> {
                    dao.delete(d);
                    return true;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Product post(@RequestBody
                        @Valid Product product) {
        return dao.save(product);
    }

    @PutMapping("{id}")
    public Product put(@PathVariable Integer id,
                       @RequestBody
                       @Valid Product product) {
        return dao.findById(id)
                .map(d -> {
                    product.setId(d.getId());
                    dao.save(product);
                    return product;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
