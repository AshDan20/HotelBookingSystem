package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
  public class CityController {
  private final CityService cityService;

  @Autowired
  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<City>> getAllCities() {
    return cityService.getAllCities();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public City getCityById(@PathVariable ("id") long cityId) {
    return cityService.getCityById(cityId);
  }



  @PostMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public City createCity(@RequestBody City city) {
    return cityService.createCity(city);
  }
}
