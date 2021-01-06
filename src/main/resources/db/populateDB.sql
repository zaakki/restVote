DELETE
FROM VOTE;
DELETE
FROM DISH;
DELETE
FROM RESTAURANT;
DELETE
FROM MENU;
DELETE
FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, PASSWORD, EMAIL, REGISTERED)
VALUES ('User', '{noop}user_password', 'user@email.com', '2019-04-23 10:00:00'),
       ('Admin', '{noop}admin_password', 'admin@email.com', '2019-04-23 12:00:00');

INSERT INTO user_roles (user_id, role)
VALUES (100000, 'USER'),
       (100001, 'USER'),
       (100001, 'ADMIN');

INSERT INTO RESTAURANT (NAME)
VALUES ('BurgerKing'),
       ('KFC'),
       ('McDonalds');

INSERT INTO MENU (MENU_DATE, RESTAURANT_ID)
VALUES ('2020-11-19', 100002),
       ('2020-11-20', 100003),
       (current_date, 100003),
       (current_date, 100004),
       ('2020-06-12', 100002);

INSERT INTO DISH (NAME, PRICE, MENU_ID)
VALUES ('Steak', 100000, 100005),
       ('Hamburger', 10000, 100006),
       ('Bugs', 20000, 100007),
       ('McSteak', 11100, 100007),
       ('McVine', 22200, 100007),
       ('Takoburger', 33300, 100008);

INSERT INTO VOTE (VOTE_DATE, USER_ID, MENU_ID)
VALUES ('2020-11-20', 100000, 100006),
       ('2020-11-20', 100001, 100006),
       (current_date, 100000, 100007);