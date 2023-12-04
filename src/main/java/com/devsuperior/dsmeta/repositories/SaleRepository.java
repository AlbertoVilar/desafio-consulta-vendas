package com.devsuperior.dsmeta.repositories;

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



    @Query(value = "SELECT obj, obj.seller.name, SUM(obj.amount) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller.name")
    Page<Sale> searchByDateSeller(@Param("minDate") LocalDate minDate,
                                         @Param("maxDate") LocalDate maxDate, Pageable pageable);




}


