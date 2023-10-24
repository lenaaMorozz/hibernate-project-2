package com.mer;

import com.mer.model.dao.*;
import com.mer.model.entity.film.*;
import com.mer.model.entity.movieRental.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;
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


    public Main() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Film.class);
        configuration.addAnnotatedClass(FilmText.class);
        configuration.addAnnotatedClass(Language.class);
        configuration.addAnnotatedClass(Rating.class);
        configuration.addAnnotatedClass(SpecialFeatures.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Actor.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(Staff.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Inventory.class);
        configuration.addAnnotatedClass(Rental.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);

    }

    public static void main(String[] args) {
        Main main = new Main();
//        Customer customer = main.createCustomer();
//        main.returnRental(13351);
//        main.addNewFilmForRental();
        main.rentalFilm();
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
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

    private void returnRental(int id) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            Transaction transaction = currentSession.beginTransaction();
            Rental rental = rentalDAO.getById(id);
            rental.setReturn_date(LocalDateTime.now());
            rentalDAO.update(rental);
            transaction.commit();
        }
    }

    private void addNewFilmForRental() {
        try (Session session = sessionFactory.getCurrentSession()) {
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

    private void rentalFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
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
}
