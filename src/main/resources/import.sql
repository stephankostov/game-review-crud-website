insert into User (user_id, username, email, password) values (USER_SEQ.nextval, 'testuser', 'user@mail.com', 'password');
insert into User (user_id, username, email, password) values (USER_SEQ.nextval, 'Stephan', 'stephan@mail.com', 'password');
insert into User (user_id, username, email, password) values (USER_SEQ.nextval, 'Chris', 'drysale@gmail.com', 'password');

insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'InnerSloth', 'Washington, USA');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'Mojang', 'Stockholm, Sweden');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'CD Projekt Red', 'Warsaw, Poland');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'Toby Fox', 'Massachusetts, USA');
insert into Developer (developer_id, name, address) values (DEVELOPER_SEQ.nextval, 'Riot Games', 'California, USA');

insert into Language (language_id, name) values (LANGUAGE_SEQ.nextval, 'Java');
insert into Language (language_id, name) values (LANGUAGE_SEQ.nextval, 'C++');
insert into Language (language_id, name) values (LANGUAGE_SEQ.nextval, 'GameMaker');

insert into Game (game_id, name, developer_id, language_id) values (GAME_SEQ.nextval, 'Among Us', 1, 2);
insert into Game (game_id, name, developer_id, language_id) values (GAME_SEQ.nextval, 'Minecraft', 2, 1);
insert into Game (game_id, name, developer_id, language_id) values (GAME_SEQ.nextval, 'Witcher 3', 3, 2);
insert into Game (game_id, name, developer_id, language_id) values (GAME_SEQ.nextval, 'Undertale', 4, 3);
insert into Game (game_id, name, developer_id, language_id) values (GAME_SEQ.nextval, 'League of Legends', 5, 2);

insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 2, 4, 'good old fashioned fun');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 1, 3, 5, 'so creative');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 3, 1, 5, 'gr8');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 2, 3, 5, 'review..');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 4, 1, 4, 'gr8');
insert into Review (review_id, game_id, user_id, rating, review) values (REVIEW_SEQ.nextval, 5, 3, 3, 'review..');



