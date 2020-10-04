package org.fasttrackit.onlineshop.transfer;

import javax.validation.constraints.NotNull;

public class AddProdactToCartRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long productId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "AddProdactToCartRequest{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
