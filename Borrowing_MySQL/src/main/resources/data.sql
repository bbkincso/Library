INSERT INTO books (author, title, isbn, is_available) VALUES ('J.K.Rowling', 'Harry Potter and the Philosopher''s Stone', '9780747532743', true);
INSERT INTO books (author, title, isbn, is_available) VALUES ('J.K.Rowling', 'Harry Potter and the Chamber of Secrets', '9780747538486', true);
INSERT INTO books (author, title, isbn, is_available) VALUES ('J.K.Rowling', 'Harry Potter and the Prisoner of Azkaban', '9780747546290', true);
INSERT INTO books (author, title, isbn, is_available) VALUES ('J.K.Rowling', 'Harry Potter and the Goblet of Fire', '9780747550990', false);
INSERT INTO books (author, title, isbn, is_available) VALUES ('J.R.R.Tolkien', 'The lord of the Rings', '9780007136582', false);
INSERT INTO books (author, title, isbn, is_available) VALUES ('J.K.Rowling', 'Harry Potter and the Order of the Phoenix', '9780747561071', false);

INSERT INTO users (first_name, last_name, date_of_birth, email, phone, address) VALUES ('Betti', 'Bordas', '1989-03-01', 'betti@gmail.com', '0871112233', 'Dublin');
INSERT INTO users (first_name, last_name, date_of_birth, email, phone, address) VALUES ('Bogi', 'Bordas', '1982-12-12', 'bogi@gmail.com', '0872223344', 'Cork');
INSERT INTO users (first_name, last_name, date_of_birth, email, phone, address) VALUES ('Norbi', 'Tiborcz', '1989-03-01', 'norbi@gmail.com', '0873334455', 'Galway');

INSERT INTO borrowings (start_date, end_date, status, user_id) VALUES ('2022-01-01', '2022-01-14', 'CLOSED', 1);
INSERT INTO borrowings (start_date, end_date, status, user_id) VALUES ('2022-01-05', '2022-01-19', 'CLOSED', 2);
INSERT INTO borrowings (start_date, end_date, status, user_id) VALUES ('2022-02-10', '2022-02-24', 'CLOSED', 3);
INSERT INTO borrowings (start_date, end_date, status, user_id) VALUES ('2022-03-25', '2022-04-08', 'LATE', 1);
INSERT INTO borrowings (start_date, end_date, status, user_id) VALUES ('2022-04-01', '2022-04-14', 'ACTIVE', 2);
INSERT INTO borrowings (start_date, end_date, status, user_id) VALUES ('2022-04-10', '2022-04-24', 'ACTIVE', 3);

INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('1', '2');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('1', '3');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('2', '1');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('2', '4');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('3', '1');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('4', '5');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('4', '6');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('5', '2');
INSERT INTO borrowing_books (borrowing_id, book_id) VALUES ('6', '4');