package fr.visiplus.book.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import fr.visiplus.book.entity.Book;
import fr.visiplus.book.entity.BookStatus;
import fr.visiplus.book.entity.Reservation;

public class ReservationServiceTest {

	ReservationService reservationService = new ReservationService(null, null, null);

	@Test
	public void testisReservationMoreThan() {
		// Arrange
		Book book = new Book();
		book.setId(Long.valueOf(1));
		book.setName("TestBook");
		book.setDescription("Not relevant");
		book.setStatus(BookStatus.BOOKED);

		Reservation reservation = new Reservation();
		reservation.setId(Long.valueOf(1));
		reservation.setBook(book);
		reservation.setDateResa(LocalDate.now());

		// Act
		boolean result = reservationService.isReservationMoreThan(reservation, 1);

		// Assert
		assertFalse(result);
	}

}
