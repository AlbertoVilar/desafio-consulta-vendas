package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private SellerMinDTO sellerMinDTO;

	
	public SaleMinDTO(Long id, Double amount, LocalDate date, SellerMinDTO sellerMinDTO) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerMinDTO = sellerMinDTO;
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		sellerMinDTO = new SellerMinDTO(entity.getSeller());
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public SellerMinDTO getSellerMinDTO() {
		return sellerMinDTO;
	}
}
