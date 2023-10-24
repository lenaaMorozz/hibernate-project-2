package com.mer.service;

import com.mer.model.dao.*;
import com.mer.model.entity.film.Film;
import com.mer.model.entity.film.FilmText;
import com.mer.model.entity.film.Rating;
import com.mer.model.entity.film.SpecialFeatures;
import com.mer.model.entity.movieRental.*;
import com.mer.util.UtilClass;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class MovieService {
    private final UtilClass utilClass;
    private final ActorDAO actorDAO;
    private final FilmDAO filmDAO;
    private final LanguageDAO languageDAO;
    private final FilmTextDAO filmTextDAO;
    private final CategoryDAO categoryDAO;
    private final AddressDAO addressDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final StaffDAO staffDAO;
    private final CustomerDAO customerDAO;
    private final InventoryDAO inventoryDAO;
    private final RentalDAO rentalDAO;
    private final PaymentDAO paymentDAO;
    private final StoreDAO storeDAO;

    public MovieService() {
        utilClass = new UtilClass();

        actorDAO = new ActorDAO(utilClass.getSessionFactory());
        filmDAO = new FilmDAO(utilClass.getSessionFactory());
        languageDAO = new LanguageDAO(utilClass.getSessionFactory());
        filmTextDAO = new FilmTextDAO(utilClass.getSessionFactory());
        categoryDAO = new CategoryDAO(utilClass.getSessionFactory());
        addressDAO = new AddressDAO(utilClass.getSessionFactory());
        cityDAO = new CityDAO(utilClass.getSessionFactory());
        countryDAO = new CountryDAO(utilClass.getSessionFactory());
        staffDAO = new StaffDAO(utilClass.getSessionFactory());
        customerDAO = new CustomerDAO(utilClass.getSessionFactory());
        inventoryDAO = new InventoryDAO(utilClass.getSessionFactory());
        rentalDAO = new RentalDAO(utilClass.getSessionFactory());
        paymentDAO = new PaymentDAO(utilClass.getSessionFactory());
        storeDAO = new StoreDAO(utilClass.getSessionFactory());
    }

    public Customer createCustomer() {
        try (Session session = getCurrentSession()){
            Transaction transaction = session.beginTransaction();
            Store store = storeDAO.getById(1);

            City city = cityDAO.getByName("Adana");

            Address address = Address.builder()
                    .address("20 Inn Str")
                    .phone("3883-4332-33")
                    .city(city)
                    .district("Alberta")
                    .build();
            addressDAO.save(address);

            Customer customer = Customer.builder()
                    .address(address)
                    .firstName("Lena")
                    .lastName("Mer")
                    .store(store)
                    .isActive(true)
                    .email("email@mail.com")
                    .build();

            customerDAO.save(customer);

            transaction.commit();
            return customer;
        }
    }

    public void returnRental(int id) {
        try (Session currentSession = getCurrentSession()) {
            Transaction transaction = currentSession.beginTransaction();
            Rental rental = rentalDAO.getById(id);
            rental.setReturn_date(LocalDateTime.now());
            rentalDAO.update(rental);
            transaction.commit();
        }
    }

    public void addNewFilmForRental() {
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Film film = Film.builder()
                    .title("New film")
                    .description("new description")
                    .releaseYear(2023)
                    .language(languageDAO.getById(1))
                    .rentalDuration((byte) 6)
                    .rentalRate(new BigDecimal("0.99"))
                    .length((short) 100)
                    .replacementCost(new BigDecimal("20.98"))
                    .rating(Rating.G.getRating())
                    .categorySet(categoryDAO.getItems(0, 3))
                    .actorSet(actorDAO.getItems(5, 5))
                    .build();

            Set<SpecialFeatures> specialFeatures = Set.of(SpecialFeatures.Commentaries, SpecialFeatures.Trailers);
            film.setSpecialFeatures(specialFeatures);

            filmDAO.save(film);

            FilmText filmText = FilmText.builder()
                    .id(film.getId())
                    .title(film.getTitle())
                    .description(film.getDescription())
                    .build();

            filmTextDAO.save(filmText);

            Store store = storeDAO.getById(1);

            Inventory inventory = Inventory.builder()
                    .film(film)
                    .store(store)
                    .build();

            inventoryDAO.save(inventory);
            transaction.commit();
        }
    }

    public void rentalFilm() {
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Rental rental = Rental.builder()
                    .rental_date(LocalDateTime.now())
                    .inventory(inventoryDAO.getById(1))
                    .customer(customerDAO.getById(3))
                    .staff(staffDAO.getById(2))
                    .build();
            rentalDAO.save(rental);

            Film film = inventoryDAO.getById(1).getFilm();

            Payment payment = Payment.builder()
                    .customer(rental.getCustomer())
                    .staff(rental.getStaff())
                    .rental(rental)
                    .amount(film.getRentalRate())
                    .paymentDate(LocalDateTime.now())
                    .build();
            paymentDAO.save(payment);

            transaction.commit();
        }
    }

    private Session getCurrentSession() {
        return utilClass.getSessionFactory().getCurrentSession();
    }

}
