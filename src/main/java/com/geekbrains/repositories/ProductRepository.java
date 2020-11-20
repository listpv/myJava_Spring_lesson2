package com.geekbrains.repositories;

import com.geekbrains.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList;

    public ProductRepository() {
    }

    @PostConstruct
    public void init(){
        this.productList = new ArrayList<>();
        productList.add(new Product(1l, "Clothes", "Woman", "Gucci", 100.0));
        productList.add(new Product(2l, "Shoes", "Woman", "Prada", 110.0));
        productList.add(new Product(3l, "Clothes", "Man", "Iceberg", 50.0));
        productList.add(new Product(4l, "Shoes", "Man", "Hogan", 70.0));
        productList.add(new Product(5l, "Clothes", "Women", "Chanel", 90.0));
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(productList.size()+1L);
            productList.add(product);
            return product;
        } else {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == product.getId()) {
                    productList.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update product");
    }

    // метод, позволяющий найти продукт по id.
    public Product findById(Long id){
        for(Product product : productList){
            if(product.getId() == id){
                return product;
            }
        }
        throw new RuntimeException("Product not found");

    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(productList);
    }

    // удаление продукта по id.
    public boolean removeProduct(Long id){
        Product product = findById(id);
        if(product == null){
            return false;
        }
        return productList.remove(product);
    }

    // долбавление продукта.
    public void addProduct(Product product){
        productList.add(product);
    }
}
