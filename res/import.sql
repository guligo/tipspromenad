-- alter tables first where it is neccessary
alter table games_questions add sequence int null;

-- clubs
insert into clubs (name, url, latitude, longitude) values ('FK Vittus', 'http://www3.idrottonline.se/FKVittus-Orientering/Tipspromenad2014', 56.229212, 15.667211); -- 1

-- users
-- password 'qwerty'
insert into users (name, email, password, role, enabled, fbUserId, fbUserPassword) values ('John Doe', 'john.doe@gmail.com', '2FeO34RYzgb7xbt2pYxcpA==', 0, true, null, null); -- 1
-- password 'qwerty'
insert into users (name, email, password, role, enabled, fbUserId, fbUserPassword) values ('Jane Doe', 'jane.doe@gmail.com', '2FeO34RYzgb7xbt2pYxcpA==', 0, true, null, null); -- 2

-- games
insert into games (creation_date, date, name, code, state, creator_id) values (now(), now(), 'Karlskrona Game', '12345678', 0, 1); -- 1

-- questions
insert into questions (type, text) values (0, 'What is the population of Karlskrona?'); -- 1
insert into questions (type, text) values (0, 'What is the number of islands in Karlskrona?'); -- 2
insert into questions (type, text) values (0, 'What is the name of the volcano that erupted in iceland in 2010?'); -- 3
insert into questions (type) values (1); -- 4
insert into questions (type) values (2); -- 5

-- games_questions
insert into games_questions (games_id, questions_id, sequence) values (1, 1, 1);
insert into games_questions (games_id, questions_id, sequence) values (1, 2, 2);
insert into games_questions (games_id, questions_id, sequence) values (1, 3, 3);
insert into games_questions (games_id, questions_id) values (1, 4);
insert into games_questions (games_id, questions_id) values (1, 5);

-- answers
insert into answers (text, correct) values ('No people in Karlskrona', false); -- 1
insert into answers (text, correct) values ('Billion', false); -- 2
insert into answers (text, correct) values ('35,212', true); -- 3
insert into answers (text, correct) values ('1000000', false); -- 4
insert into answers (text, correct) values ('13', true); -- 5
insert into answers (text, correct) values ('1', false); -- 6
insert into answers (text, correct) values ('Eyjafjallaj�kull', true); -- 7
insert into answers (text, correct) values ('Hr�mundartindur', false); -- 8
insert into answers (text, correct) values ('Abcdefg', false); -- 9

-- questions_answers
insert into questions_answers (questions_id, answers_id) values (1, 1);
insert into questions_answers (questions_id, answers_id) values (1, 2);
insert into questions_answers (questions_id, answers_id) values (1, 3);
insert into questions_answers (questions_id, answers_id) values (2, 4);
insert into questions_answers (questions_id, answers_id) values (2, 5);
insert into questions_answers (questions_id, answers_id) values (2, 6);
insert into questions_answers (questions_id, answers_id) values (3, 7);
insert into questions_answers (questions_id, answers_id) values (3, 8);
insert into questions_answers (questions_id, answers_id) values (3, 9);
