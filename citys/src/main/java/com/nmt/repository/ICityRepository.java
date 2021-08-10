package com.nmt.repository;

import com.nmt.model.City;
import com.nmt.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    Iterable<City> findAllByCountry(Country country);
    Page<City> findAllByNameContaining(String name, Pageable pageable);
}
