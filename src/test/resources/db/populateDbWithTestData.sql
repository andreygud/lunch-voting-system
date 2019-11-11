DELETE FROM vote;
DELETE FROM restaurant;
DELETE FROM menuitem;
DELETE FROM menu;

INSERT INTO vote (restaurantid, votedate, votetime, userid)
VALUES ('Joes', '2019-11-7', '10:00:00', 1),
       ('Joes', '2019-11-7', '10:10:00', 2),
       ('Cactus', '2019-11-7', '10:11:00', 3),
       ('Cactus', '2019-11-7', '10:11:00', 5),
       ('Cactus', '2019-11-7', '10:38:00', 6);

INSERT INTO menu (id, date, description)
VALUES (1, '2019-11-7', 'Joes Menu - Chicken'),
       (2, '2019-11-7', 'Cactus Menu - Meat');

INSERT INTO restaurant (id, address, description, restaurantname, menu_id)
VALUES (1, '109 8 Ave SW, Calgary, AB T2P 1B4', 'www.originaljoes.ca', 'Joes', 1),
       (2, '4653 Kingsway, Burnaby, BC V5H 4L3', 'http://www.cactusclubcafe.com/', 'Cactus', 2);

INSERT INTO menuitem (description, price, menu_id)
VALUES ('Chicken Parmesan', 23.5, 1),
       ('Classic Bowl', 18.5, 1),
       ('Sandwich of a day', 15.3, 1),
       ('Four Mushrooms Steak', 33.0, 2),
       ('Truffles Fries', 12.0, 2),
       ('Cactus Burger', 16.8, 2);

/*DELETE FROM user_roles;
DELETE FROM users;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('UserEmptyList', 'user2@gmail.com', 'user1');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002);
*/