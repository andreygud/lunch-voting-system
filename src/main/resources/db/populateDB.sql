DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurant (name, description, address)
VALUES ('Cactus Club Cafe Kingsway', 'Known for good cocktails', '4653 Kingsway, Burnaby, BC'),
       ('Original Joe''s Restaurant & Bar', 'Workdays they open 11.00am', '298 Robson St, Vancouver, BC'),
       ('Hi Genki Restaurant', 'Sushi', '6680 Southoaks Crescent, Burnaby');

INSERT INTO vote (USER_ID, RESTAURANT_ID, VOTE_TIME)
VALUES ('100000', 100002, today() || ' 09:47:52.69'),
       ('100001', 100003, today() || ' 10:14:30.36');