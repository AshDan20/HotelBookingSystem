package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteHotelById(@PathVariable("id") long hotelId) {
    hotelService.deleteHotelById(hotelId);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Hotel getHotelById(@PathVariable("id") long hotelId) {
    return hotelService.getHotelById(hotelId);
  }

  @GetMapping(value = "/search/{cityId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getTopThreeHotels(@PathVariable("cityId") long cityId) {
    return hotelService.getTopThreeHotels(cityId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }
}
