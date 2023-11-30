package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.date " +
            "BETWEEN :minDate " +
            "AND :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<Sale> searchByDateAndName(
                            @Param("minDate") String minDate,
                            @Param("maxDate") String maxDate,
                            @Param("name") String name,
                            Pageable pageable);


}
