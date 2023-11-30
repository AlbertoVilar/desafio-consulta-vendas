package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {
    private String name;

    public SellerMinDTO(String name) {
        this.name = name;
    }

    public SellerMinDTO(Seller entity) {

        name = entity.getName();
    }


    public String getName() {
        return name;
    }
}
