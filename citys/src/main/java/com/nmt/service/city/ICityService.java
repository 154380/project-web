package com.nmt.service.city;

import com.nmt.model.City;
import com.nmt.model.Country;
import com.nmt.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City> {
    Iterable<City> findAllByCountry(Country country);
    Page<City> findAll(Pageable pageable);
    Page<City> findAllByNameContaining(String name, Pageable pageable);
}
