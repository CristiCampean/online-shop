package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistence.ProdactRepository;
import org.fasttrackit.onlineshop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private  static final Logger LOGGER =LoggerFactory.getLogger(ProductService.class);

    private final ProdactRepository prodactRepository;
@Autowired
    public ProductService(ProdactRepository prodactRepository) {
        this.prodactRepository = prodactRepository;
    }
    public Product createProduct(SaveProductRequest request){
    LOGGER.info(" Creating product{}", request);
    Product product = new Product();
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());
    product.setImageUrl(request.getImageUrl());

        return prodactRepository.save(product);

    }

    public Product getProduct(long id){
    LOGGER.info("Retrieving product{}", id);

    return prodactRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Product"+ id+ "does not exist"));
    }
}
