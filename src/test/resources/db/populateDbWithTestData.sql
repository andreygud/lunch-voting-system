DELETE FROM vote;

INSERT INTO vote (restaurantid, votedate, votetime, userid)
VALUES ('Joes', '2019-11-7', '10:00:00', 1),
       ('Joes', '2019-11-7', '10:10:00', 2),
       ('Cactus', '2019-11-7', '10:11:00', 3),
       ('Cactus', '2019-11-7', '10:11:00', 5),
       ('Cactus', '2019-11-7', '10:38:00', 6);


/*DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('UserEmptyList', 'user2@gmail.com', 'user1');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2015-05-30 10:00:00', 'Завтрак', 500, 100000),
       ('2015-05-30 13:00:00', 'Обед', 1000, 100000),
       ('2015-05-30 20:00:00', 'Ужин', 500, 100000),
       ('2015-05-31 0:00:00', 'Еда на граничное значение', 100, 100000),
       ('2015-05-31 10:00:00', 'Завтрак', 500, 100000),
       ('2015-05-31 13:00:00', 'Обед', 1000, 100000),
       ('2015-05-31 20:00:00', 'Ужин', 510, 100000),
       ('2015-06-01 14:00:00', 'Админ ланч', 510, 100001),
       ('2015-06-01 21:00:00', 'Админ ужин', 1500, 100001);
*/