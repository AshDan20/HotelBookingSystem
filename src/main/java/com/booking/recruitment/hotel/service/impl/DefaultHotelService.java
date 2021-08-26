package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;

  @Autowired
  DefaultHotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  /**
   * get top 3 hotels from city
   * @param cityId
   * @return
   */
  @Override
  public List<Hotel> getTopThreeHotels(Long cityId) {
    System.out.println("getting top 3 hotels which has the highest rating");
    return hotelRepository.findAll().stream()
            .filter(hotel -> hotel.getCity().getId().equals(cityId))
            .filter(hotel -> !hotel.isDeleted()) //hotel should not be deleted
            .sorted((hotel1, hotel2) -> {
              if (null == hotel1.getRating() || null == hotel2.getRating()) { //if rating is null it throws NPE...so checked it
                return 0;
              }
              return hotel2.getRating().compareTo(hotel1.getRating());
              })
            .limit(3)
            .collect(Collectors.toList());
  }

  @Override
  public Hotel getHotelById(Long hotelId) {
   return hotelRepository
           .findById(hotelId)
           .orElseThrow(() -> new ElementNotFoundException("the requested hotel is not found"));
  }

  @Override
  public void deleteHotelById(Long hotelId) {
    Optional<Hotel> tempHotel = hotelRepository.findById(hotelId);

    if(tempHotel != null){
      tempHotel.get().setDeleted(true);
      hotelRepository.save(tempHotel.get());
    }else {
      throw new ElementNotFoundException("hotel does not exists");
    }
  }

  public double haversine(Hotel h1, Hotel h2)
  {
    double lat1 = h1.getLatitude();
    double lon1 =h1.getLongitude();
    double lat2 =h2.getLatitude();
    double lon2 =h2.getLongitude();
    // distance between latitudes and longitudes
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);

    // convert to radians
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    // apply formulae
    double a = Math.pow(Math.sin(dLat / 2), 2) +
            Math.pow(Math.sin(dLon / 2), 2) *
                    Math.cos(lat1) *
                    Math.cos(lat2);
    double rad = 6371;
    double c = 2 * Math.asin(Math.sqrt(a));
    return rad * c;
  }
}
