package org.fasttrackit.onlineshop.transfer.cart;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {
    private  long id;
    private Set<ProdactInCart> products = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProdactInCart> getProducts() {
        return products;
    }

    public void setProducts(Set<ProdactInCart> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}

