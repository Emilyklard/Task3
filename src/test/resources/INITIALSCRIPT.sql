
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;

CREATE TABLE IF NOT EXISTS author
(
    id            SERIAL       NOT NULL PRIMARY KEY,
    first_name    VARCHAR(128) NOT NULL,
    last_name     VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS book
(
    id        BIGSERIAL    NOT NULL PRIMARY KEY,
    name      VARCHAR(128) NOT NUll,
    year      SMALLINT     NOT NUll,
    pages     SMALLINT     NOT NUll,
    author_id INT REFERENCES author (id) ON DELETE CASCADE NOT NULL
);

INSERT INTO author (first_name, last_name)
VALUES ('Кей', 'Хорстманн'),
       ('Стивен' ,'Кови'),
       ('Тони', 'Роббинс'),
       ('Наполеон', 'Хилл'),
       ('Роберт', 'Кийосаки'),
       ('Дейл', 'Карнеги'),
       ('Джоан', 'Роулинг');

INSERT INTO book(name, year, pages, author_id)
VALUES ('Java SE 8. Вводный курс' ,'2015', '203' , '1'),
       (' Java. Библиотека профессионала. Том 1', '2010', '1102', '1'),
       ('Java. Библиотека профессионала. Том 2', '2011', '954', '1'),
       ('7 навыков высокоэффективных людей', '1989', '396', '2'),
       ('Разбуди в себе исполина', '1991', '576', '3'),
       ('Думай и богатей', '1937', '336', '4'),
       ('"Богатый папа, бедный папа"', '1997', '352', '5'),
       ('Квадрант денежного потока', '1998', '368', '5'),
       ('Как перестать беспокоиться и начать жить', '1948', '368', '6');





