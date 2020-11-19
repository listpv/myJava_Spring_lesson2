package com.geekbrains.services;

import com.geekbrains.model.Product;
import com.geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.saveOrUpdate(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    //добавление продукта.
    public void addProduct(Product product){
        productRepository.addProduct(product);
    }

    // удаление продукта.
    public boolean removeProduct(Long id){
        return productRepository.removeProduct(id);
    }

    // количество продуктов.
    public Integer sumOfProducts(){
        return productRepository.findAll().size();
    }

    // цена всех продуктов в корзине.
    public Double totalPrise(){
        Double totalSum = 0.0;
        for(Product product : productRepository.findAll()){
            totalSum += product.getPrice();
        }
        return totalSum;
    }

    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
