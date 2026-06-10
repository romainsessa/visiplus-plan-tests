describe('Book app', () => {

  it('should display books', () => {
    cy.visit('/');

    cy.contains('Book list');

    cy.get('.book-card').should('exist').and('have.length', 4);
  });

});

it('should reserve an available book', () => {

  cy.visit('/');

  cy.get('.book-card')
    .contains('.book-id', '3')   
    .parents('.book-card')              
    .as('availableBook');               

  cy.get('@availableBook')
    .contains('Reserve')
    .should('exist');

  cy.get('@availableBook')
    .contains('Reserve')
    .click({ force: true });

  cy.get('@availableBook')
    .find('.status')
    .should('contain', 'BOOKED');

});