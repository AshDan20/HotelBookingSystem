package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.Hotel;

import java.util.List;

public interface HotelService {
  List<Hotel> getAllHotels();

  List<Hotel> getHotelsByCity(Long cityId);

  List<Hotel> getTopThreeHotels(Long cityId);

  Hotel getHotelById(Long hotelId);

  Hotel createNewHotel(Hotel hotel);

  void deleteHotelById(Long hotelId);
}
