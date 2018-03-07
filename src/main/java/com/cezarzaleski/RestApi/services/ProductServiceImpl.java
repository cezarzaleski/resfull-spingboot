package com.cezarzaleski.RestApi.services;

import com.cezarzaleski.RestApi.models.Product;
import com.cezarzaleski.RestApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product find(Long id) {
        return this.productRepository.findOne(id);
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productExists = this.productRepository.findOne(id);
        if (productExists != null) {
            product.setId(productExists.getId());
            return this.productRepository.save(product);
        }
        return null;

    }

    @Override
    public void delete(Long id) {
        Product product = this.productRepository.findOne(id);
        if (product != null) {
            this.productRepository.delete(product);
        }
    }
}
