package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.custumers.SellerMinProjection;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.date " +
            "BETWEEN :minDate " +
            "AND :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<Sale> searchByDateAndName( LocalDate minDate,
                                   LocalDate maxDate,
                                   String name,
                                   Pageable pageable);



    @Query("""
            SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount))
            FROM Sale obj
            WHERE obj.date >= :min
            AND obj.date <= :max
            GROUP BY obj.seller.name
            """)
    Page<SaleSummaryDTO> searchSalesBySeller(LocalDate min, LocalDate max, Pageable pageable);




/*
@Query(nativeQuery = true, value = "SELECT seller.name, SUM(tb_sales.amount) " +
        "FROM tb_sellers " +
        "LEFT JOIN tb_sales  ON tb_sellers.id = tb_sales.seller_id " +
        "WHERE tb_sales.date BETWEEN :minDate AND :maxDate " +
        "GROUP BY tb_sellers.id, tb_sellers.name; ")
Page<SellerMinProjection> searchByDateSeller(@Param("minDate") LocalDate minDate,
                                             @Param("maxDate") LocalDate maxDate, Pageable pageable);



 */


}


