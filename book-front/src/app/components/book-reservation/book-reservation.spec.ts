import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { BookReservation } from './book-reservation';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/book';

describe('BookReservation', () => {

  let component: BookReservation;
  let fixture: ComponentFixture<BookReservation>;

  beforeEach(async () => {

    // on crée un mock de BookService pour éviter les appels HTTP réels
    const mockBookService = {
      getAllBooks: jest.fn().mockReturnValue(of([])),
      notGet: jest.fn().mockReturnValue(of([])),
      reserve: jest.fn().mockReturnValue(of({}))
    };

    await TestBed.configureTestingModule({
      imports: [BookReservation],
      providers: [
        { provide: BookService, useValue: mockBookService } // on injecte le mock
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(BookReservation); 
    component = fixture.componentInstance; 
    fixture.detectChanges(); // on déclenche la détection de changement pour initialiser le composant
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return false if book is BORROWED', () => {
    // Arrange
    const book: Book = {
      id: 1,
      status: 'BORROWED'
    } as Book;
    // Act
    const result = component.canReserve(book);
    // Assert
    expect(result).toBe(false);
  });

  it('should return true if book is AVAILABLE', () => {
    // Arrange
    const book: Book = {
      id: 1,
      status: 'AVAILABLE'
    } as Book;
    // Act
    const result = component.canReserve(book);
    // Assert
    expect(result).toBe(true);
  });

  it('should return true if book is BOOKED and in notGetBooks', () => {
    // Arrange
    component.notGetBooks = [
      { id: 1 } as Book
    ];
    const book: Book = {
      id: 1,
      status: 'BOOKED'
    } as Book;
    // Act
    const result = component.canReserve(book);
    // Assert
    expect(result).toBe(true);
  });

  it('should return false if book is BOOKED and not in notGetBooks', () => {
    // Arrange
    const book: Book = {
      id: 1,
      status: 'BOOKED'
    } as Book;
    // Act
    const result = component.canReserve(book);
    // Assert
    expect(result).toBe(false);
  });

});