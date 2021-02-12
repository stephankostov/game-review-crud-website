insert into User (user_id, username, email, password) values (USER_SEQ.nextval, 'testuser', 'user@mail.com', 'password');
insert into User (user_id, username, email, password) values (USER_SEQ.nextval, 'Stephan', 'stephan@mail.com', 'password');
insert into User (user_id, username, email, password) values (USER_SEQ.nextval, 'Chris', 'drysale@gmail.com', 'password');

insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'InnerSloth', 'Washington, USA');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'Mojang', 'Stockholm, Sweden');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'CD Projekt Red', 'Warsaw, Poland');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'Toby Fox', 'Massachusetts, USA');

insert into Game (game_id, name, developer_id) values (GAME_SEQ.nextval, 'Among Us', 1);
insert into Game (game_id, name, developer_id) values (GAME_SEQ.nextval, 'Minecraft', 2);
insert into Game (game_id, name, developer_id) values (GAME_SEQ.nextval, 'Witcher 3', 3);
insert into Game (game_id, name, developer_id) values (GAME_SEQ.nextval, 'Undertale', 4);

insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 1, 1, 'trash');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 2, 4, 'good old fashioned fun');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 3, 5, 'best game EVER');


