DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu_item;
DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000 INCREMENT BY 1;

CREATE TABLE users
(
    id         INTEGER   DEFAULT global_seq.NEXTVAL PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id          INTEGER DEFAULT global_seq.NEXTVAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    address     VARCHAR(255)
);

CREATE INDEX restaurant_id_idx
    ON restaurant (id);

CREATE TABLE vote
(
    id            INTEGER   DEFAULT global_seq.NEXTVAL PRIMARY KEY,
    user_id       INTEGER                 NOT NULL,
    restaurant_id INTEGER                 NOT NULL,
    vote_time     TIMESTAMP DEFAULT now() NOT NULL,
    vote_date     VARCHAR AS TO_CHAR(vote_time, 'DD/MM/YYYY'),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE INDEX vote_user_id_vote_time_idx
    ON vote (user_id,vote_time);
CREATE UNIQUE INDEX vote_one_per_day_idx
    ON vote (user_id,vote_date);
CREATE INDEX vote_id_idx
    ON vote (id);

CREATE TABLE menu_item
(
    id            INTEGER DEFAULT global_seq.NEXTVAL PRIMARY KEY,
    restaurant_id INTEGER        NOT NULL,
    name          VARCHAR(255)   NOT NULL,
    price         DECIMAL(10, 2) NOT NULL,
    menu_date     DATE    DEFAULT today,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX unique_menu_item_restaurant_id_menu_date_idx
    ON menu_item (restaurant_id,name,menu_date);
CREATE INDEX menu_item_id_idx
    ON menu_item (id);