-- users
insert into users (email, username, password, role, enabled) values ('guligo@one.lv', 'guligo', 'gulbis', 0, true); -- 1

-- sports
insert into sports (name, image) values ('Gym', 'local/img/sports/gym.png'); -- 1
insert into sports (name, image) values ('Running', 'local/img/sports/running.png'); -- 2
insert into sports (name, image) values ('Swimming', 'local/img/sports/swimming.png'); -- 3
insert into sports (name, image) values ('Cycling', 'local/img/sports/cycling.png'); -- 4
insert into sports (name, image) values ('Home training', 'local/img/sports/home-training.png'); -- 5

-- predefined measurement units
insert into measurement_units (name, abbreviation) values ('minute', 'min.'); -- 1
insert into measurement_units (name, abbreviation) values ('second', 'sec.'); -- 2
insert into measurement_units (name, abbreviation) values ('hour', 'hr.'); -- 3
insert into measurement_units (name, abbreviation) values ('kilogram', 'kg'); -- 4
insert into measurement_units (name, abbreviation) values ('centimeter', 'cm'); -- 5
insert into measurement_units (name, abbreviation) values ('meter', 'm'); -- 6
insert into measurement_units (name, abbreviation) values ('kilometer', 'km'); -- 7
insert into measurement_units (name, abbreviation) values ('repeats', 'reps.'); -- 8

-- prefedined attributes
insert into attributes (name, measurementUnit_id, user_id) values ('push-ups', 8, null); -- 1
insert into performance_attributes (id, sport_id) values (1, 1); -- 1
insert into attributes (name, measurementUnit_id, user_id) values ('pull-ups', 8, null); -- 2
insert into performance_attributes (id, sport_id) values (2, 1); -- 2
insert into attributes (name, measurementUnit_id, user_id) values ('distance', 7, null); -- 3
insert into performance_attributes (id, sport_id) values (3, 4); -- 3
