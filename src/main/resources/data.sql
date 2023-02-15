CREATE TABLE users(
  id bigint auto_increment primary key not null,
  name varchar(15) not null,
  email varchar(15) not null,
  password varchar(15) not null,
  data DATE
);

INSERT INTO users(name, email, password, data)
VALUES ('login', 'email', 'password', '2023-02-15'), ('login', 'email', 'password', '2023-02-15'), ('login', 'email', 'password', '2023-02-15');

CREATE TABLE quotes(
    id bigint auto_increment primary key not null,
    text varchar not null,
    author_id bigint not null,
    data DATE,
    score int
);

INSERT INTO quotes (text, author_id, data, score)
VALUES ('first quote', 1, '2023-02-15', 0), ('second quote', 2, '2023-02-15', 0), ('third quote', 3, '2023-02-15', 0);

CREATE TABLE votes(
   id bigint auto_increment primary key not null,
   up boolean not null,
   quote_id bigint not null
);

INSERT INTO votes (up, quote_id)
VALUES (true, 2), (true, 1), (false, 3);