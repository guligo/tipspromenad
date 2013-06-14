-- alter tables first where it is neccessary
alter table games_questions add sequence int null;

-- users
insert into users (email, username, password, role, enabled) values ('qwerty@qwer.ty', 'qwerty', '2FeO34RYzgb7xbt2pYxcpA==', 0, true); -- 1

-- games
insert into games (creation_date, date, name, creator_id, status) values (now(), now(), 'Karlskrona Game', 1, 4); -- 1

-- questions
insert into questions (text) values ('What is the population of Karlskrona?'); -- 1
insert into questions (text) values ('What is the number of islands in Karlskrona?'); -- 2

-- games_questions
insert into games_questions (games_id, questions_id, sequence) values (1, 1, 1);
insert into games_questions (games_id, questions_id, sequence) values (1, 2, 2);

-- answers
insert into answers (text, correct) values ('No people in Karlskrona', false); -- 1
insert into answers (text, correct) values ('Billion', false); -- 2
insert into answers (text, correct) values ('35,212', true); -- 3
insert into answers (text, correct) values ('1000000', false); -- 4
insert into answers (text, correct) values ('13', true); -- 5
insert into answers (text, correct) values ('1', false); -- 6

-- questions_answers
insert into questions_answers (questions_id, answers_id) value (1, 1);
insert into questions_answers (questions_id, answers_id) value (1, 2);
insert into questions_answers (questions_id, answers_id) value (1, 3);
insert into questions_answers (questions_id, answers_id) value (2, 4);
insert into questions_answers (questions_id, answers_id) value (2, 5);
insert into questions_answers (questions_id, answers_id) value (2, 6);
