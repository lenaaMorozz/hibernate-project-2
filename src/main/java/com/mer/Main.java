package com.mer;


import com.mer.model.entity.movieRental.Customer;
import com.mer.service.MovieService;

public class Main {
    public static void main(String[] args) {
        MovieService movieService = new MovieService();
        Customer customer = movieService.createCustomer();
        movieService.returnRental(13161);
        movieService.addNewFilmForRental();
        movieService.rentalFilm();
    }
}
