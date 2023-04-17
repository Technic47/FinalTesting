TRUNCATE
    animalservicetest.public.animals RESTART IDENTITY CASCADE;
TRUNCATE
    animalservicetest.public.food RESTART IDENTITY CASCADE;
TRUNCATE
    animalservicetest.public.moves RESTART IDENTITY CASCADE;
TRUNCATE
    animalservicetest.public.counter RESTART IDENTITY CASCADE;

INSERT INTO animalservicetest.public.animals(dtype, id, name)
VALUES ('Cat', DEFAULT, 'Cat1'),
       ('Dog', DEFAULT, 'Dog1'),
       ('Humster', DEFAULT, 'Humster1'),
       ('Horse', DEFAULT, 'Horse1'),
       ('Donkey', DEFAULT, 'Donkey1'),
       ('Camel', DEFAULT, 'Camel1');

INSERT INTO animalservicetest.public.counter(animal_type, count)
VALUES ('Cat', 1),
       ('Dog', 1),
       ('Humaster', 1),
       ('Horse', 1),
       ('Donkey', 1),
       ('Camel', 1);

INSERT INTO animalservicetest.public.moves(id, name)
VALUES (DEFAULT, 'Move1'),
       (DEFAULT, 'Move2'),
       (DEFAULT, 'Move3');

INSERT INTO animalservicetest.public.food(id, name)
VALUES (DEFAULT, 'Food1'),
       (DEFAULT, 'Food2'),
       (DEFAULT, 'Food3');