import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';

import { BookReservation } from './book-reservation';
import { Book } from '../../models/book';

describe('BookReservation - integration tests', () => {

  let component: BookReservation;
  let fixture: ComponentFixture<BookReservation>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookReservation],
      providers: [
        provideHttpClient(), 
        provideHttpClientTesting() // on fournit le HttpTestingController pour pouvoir intercepter les requêtes HTTP et simuler les réponses
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(BookReservation);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController); // on injecte le HttpTestingController pour pouvoir intercepter les requêtes HTTP et simuler les réponses

    fixture.detectChanges();
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should load books and notGet on init', () => {
    //Arrange
    const mockBooks: Book[] = [
      { id: 1, name: 'Book 1', description: 'Desc', status: 'AVAILABLE' } as Book
    ];

    const mockNotGet: Book[] = [];

    // deux requêtes sont en attente car ngOnInit appelle loadBooks et loadNotGetBooks
    const req1 = httpMock.expectOne('http://localhost:8080/book');
    expect(req1.request.method).toBe('GET');

    const req2 = httpMock.expectOne('http://localhost:8080/book/notget');
    expect(req2.request.method).toBe('GET');

    // Act
    req1.flush(mockBooks); // on simule la réponse du serveur   
    req2.flush(mockNotGet); // on simule la réponse du serveur

    // Assert
    expect(component.books.length).toBe(1);
    expect(component.books[0].name).toBe('Book 1');
  });

  it('should call reserve API and refresh lists', () => {
    // Arrange
    const mockBooks: Book[] = [
      { id: 1, name: 'Book 1', description: 'Desc', status: 'AVAILABLE' } as Book
    ];

    const mockBooksBooked: Book[] = [
      { id: 1, name: 'Book 1', description: 'Desc', status: 'BOOKED' } as Book
    ];

    // deux requêtes sont en attente car on appelle loadBooks et loadNotGetBooks
    httpMock.expectOne('http://localhost:8080/book').flush(mockBooks); // on simule la réponse du serveur
    httpMock.expectOne('http://localhost:8080/book/notget').flush([]); // on simule la réponse du serveur

    // Act
    component.reserve(1);

    // une requête de réservation est attendue
    const reserveReq = httpMock.expectOne('http://localhost:8080/book/reserve/1/1');
    expect(reserveReq.request.method).toBe('POST');
    reserveReq.flush({}); // on simule la réponse du serveur

    // après la réservation, loadBooks et loadNotGetBooks sont appelés à nouveau
    httpMock.expectOne('http://localhost:8080/book').flush(mockBooksBooked);
    httpMock.expectOne('http://localhost:8080/book/notget').flush([]);

    // Assert
    expect(component.books[0].status).toBe('BOOKED');
  });

});