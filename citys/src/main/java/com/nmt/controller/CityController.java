package com.nmt.controller;

import com.nmt.model.City;
import com.nmt.model.Country;
import com.nmt.service.city.ICityService;
import com.nmt.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countrys")
    public Iterable<Country> countrys(){
        return countryService.findAll();
    }

    @GetMapping("/citys")
    public ModelAndView listCitys(@RequestParam("search") Optional<String> search, @PageableDefault(value = 3) Pageable pageable) {
        Page<City> citys;
        if(search.isPresent()) citys = cityService.findAllByNameContaining(search.get(), pageable);
        else citys = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("citys", citys);
        modelAndView.addObject("search", search);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView saveCity(@ModelAttribute("city") City city , @PageableDefault(value = 3) Pageable pageable) {
        cityService.save(city);
        Page<City> citys = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("citys", citys);
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@ModelAttribute("city") City city , @PageableDefault(value = 3) Pageable pageable) {
        cityService.save(city);
        Page<City> citys = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("citys", citys);
        return modelAndView;
    }

    @GetMapping("/show-city/{id}")
    public ModelAndView showCityForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/city");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/delete");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city") City city) {
        cityService.remove(city.getId());
        return "redirect:citys";
    }

}
