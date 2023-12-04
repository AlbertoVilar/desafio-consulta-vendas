package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public Page<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate startDate = today.minusYears(1L);

        LocalDate max;
        if (maxDate.equals("")) {
            max = today;
        } else {
            max = LocalDate.parse(maxDate);
        }

        LocalDate min;
        if (minDate.equals("")) {
            min = max.minusYears(1L);
        } else {
            min = LocalDate.parse(minDate);
        }


        Page<Sale> result = repository.searchByDateAndName(min, max, name, pageable);
        return result.map(x -> new SaleMinDTO(x));
    }

    public Page<SaleMinDTO> getSummary(String minDate, String maxDate, Pageable pageable) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate startDate = today.minusYears(1L);

        LocalDate max;
        if (maxDate.equals("")) {
            max = today;
        } else {
            max = LocalDate.parse(maxDate);
        }

        LocalDate min;
        if (minDate.equals("")) {
            min = max.minusYears(1L);
        } else {
            min = LocalDate.parse(minDate);
        }


    Page<Sale> result2 = repository.searchByDateSeller(min, max, pageable);
        return result2.map(x -> new SaleMinDTO(x));



    }

}
