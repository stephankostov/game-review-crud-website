insert into User (id, username, email, password) values (USER_SEQ.nextval, 'testuser', 'user@mail.com', 'password');
insert into User (id, username, email, password) values (USER_SEQ.nextval, 'Stephan', 'stephan@mail.com', 'password');
insert into User (id, username, email, password) values (USER_SEQ.nextval, 'Chris', 'drysale@gmail.com', 'password');

insert into Developer (id, name, address) values (DEVELOPER_SEQ.nextval, 'Mojang', 'Stockholm, Sweden');

insert into Game (id, name, developer_id) values (GAME_SEQ.nextval, 'Minecraft', 1)

insert into Review (id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 2, 4, 'review...')
insert into Review (id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 3, 5, 'review...')