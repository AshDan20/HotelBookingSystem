package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {
  ResponseEntity<List<City>> getAllCities();

  City getCityById(Long id);

  City createCity(City city);
}
