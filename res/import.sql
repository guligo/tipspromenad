-- alter tables first where it is neccessary
alter table games_questions add sequence int null;

-- clubs
insert into clubs (name, url, latitude, longitude) values ('FK Vittus', 'http://www3.idrottonline.se/FKVittus-Orientering/Tipspromenad2014', 56.229212, 15.667211); -- 1
insert into clubs (name, url, latitude, longitude) values ('FK Herkules', 'http://www6.idrottonline.se/FKHerkules/Resultat/Tipspromenader', 57.653280, 12.055938); -- 2
insert into clubs (name, url, latitude, longitude) values ('Bokenäs IF', 'http://www2.idrottonline.se/BokenasIF/Tipspromenad', 58.338793, 11.639292); -- 3
insert into clubs (name, url, latitude, longitude) values ('Korpen södertälje', 'http://www8.idrottonline.se/KorpforeningSodertalje-Korpen/Vuxen/Bingoochtipspromenad', 59.211817, 17.626083); -- 4
insert into clubs (name, url, latitude, longitude) values ('Eksjö SOK', 'http://www1.idrottonline.se/EksjoSOK/Klubbstugan/Tipspromenad', 57.675768, 15.002164); -- 5
insert into clubs (name, url, latitude, longitude) values ('Korpen Åseda', 'http://www2.idrottonline.se/KorpforeningAseda-Korpen/Sektioner/Tipspromenad', 57.142671, 15.151852); -- 6
insert into clubs (name, url, latitude, longitude) values ('Sya SK - Skido', 'http://www6.idrottonline.se/SyaSK-Skidor/Foreningen/Tipspromenad', 58.331318, 15.210679); -- 7
insert into clubs (name, url, latitude, longitude) values ('OK Skogsvargarna - Orientering', 'http://www7.idrottonline.se/OKSkogsvargarna-Orientering/Omforeningen/Arbetsrum/Tipspromenader', 58.347283, 12.430095 ); -- 8
insert into clubs (name, url, latitude, longitude) values ('Korpen Landskrona', 'http://www.korpen-landskrona.se/0/15/tipspromenad', 55.873608, 12.827214); -- 9
insert into clubs (name, url, latitude, longitude) values ('Gränna-Bygdens OK - Orientering', 'http://www3.idrottonline.se/Granna-BygdensOK-Orientering/Tipspromenad', 58.032672, 14.480481); -- 10
insert into clubs (name, url, latitude, longitude) values ('Korpen Värnamo', 'http://www7.idrottonline.se/KorpforeningVarnamo-Korpen/TipspromenadiApladalen', 57.186593, 14.046468); -- 11
insert into clubs (name, url, latitude, longitude) values ('Norsholms IF ', 'http://www4.idrottonline.se/NorsholmsIF/Arkiv/Gamlanyheter/Tipspromenad', 58.506138, 15.972820); -- 12
insert into clubs (name, url, latitude, longitude) values ('Kils OK - Orientering ', 'http://www3.idrottonline.se/KilsOK-Orientering/Tipspromenad', 58.068881, 11.730593); -- 13
insert into clubs (name, url, latitude, longitude) values ('Stenstorps IF - Fotbol', 'http://www5.idrottonline.se/StenstorpsIF-Fotboll/Klubbarrangemang/Tipspromenad', 58.269236, 13.726239); -- 14
insert into clubs (name, url, latitude, longitude) values ('Träslövsläge IF', 'http://www2.idrottonline.se/TraslovslageIF/Foreningen/Tipspromenad', 57.058234, 12.273408); -- 15
insert into clubs (name, url, latitude, longitude) values ('OK Gränsen ', 'http://www5.idrottonline.se/OKGransen/Tipspromenader', 55.691609, 13.071751); -- 16
insert into clubs (name, url, latitude, longitude) values ('Råda BK - Fotbol', 'http://www6.idrottonline.se/RadaBK-Fotboll/Tipspromenad', 58.503505, 13.157077); -- 17
insert into clubs (name, url, latitude, longitude) values ('Gerdskens BK - Fotboll', 'http://www1.idrottonline.se/GerdskensBK-Fotboll/Tipspromenader', 57.921842, 12.540301); -- 18
insert into clubs (name, url, latitude, longitude) values ('Skillingaryds FK ', 'http://www2.idrottonline.se/SkillingarydsFK/Tipspromenad2013', 57.428470, 14.068704); -- 19
insert into clubs (name, url, latitude, longitude) values ('Kimstad GoIF - Skidor ', 'http://www4.idrottonline.se/KimstadGOIF-Skidor/Tipspromenad', 58.558068, 15.955803); -- 20
insert into clubs (name, url, latitude, longitude) values ('Tipspromenad i Smålandsstenar', 'http://www2.visit-gislaved.se/sv/evenemanget/a514177/tipspromenad-i-smalandsstenar/detaljer?page=2', 57.174596, 13.428831); -- 21
insert into clubs (name, url, latitude, longitude) values ('Korpen Karlskoga', 'http://www3.idrottonline.se/KorpforeningKarlskoga-Korpen/VaraAktiviteter/TIPSPROMENADER', 59.328634, 14.536414); -- 22
insert into clubs (name, url, latitude, longitude) values ('Korpen Töreboda', 'http://www2.idrottonline.se/KorpforeningToreboda-Korpen', 58.702885, 14.127162); -- 23
insert into clubs (name, url, latitude, longitude) values ('Korpen Växjö', 'http://www.korpenvaxjo.se', 56.905858, 14.771368); -- 24
insert into clubs (name, url, latitude, longitude) values ('IKHP', 'http://www.ikhp.se/tipspromenad.aspx', 57.808409, 14.282071); -- 25
insert into clubs (name, url, latitude, longitude) values ('Korpen Göterborg', 'http://www5.idrottonline.se/KorpforeningGoteborg-Korpen/Promenader/Tipspromenader', 57.717887, 11.999571); -- 26
insert into clubs (name, url, latitude, longitude) values ('Korpen Eskilstuna', 'http://www.eskilstuna.korpen.se/tipsromenad.htm', 59.351343, 16.509941); -- 27
insert into clubs (name, url, latitude, longitude) values ('Korpförening Motion o Fritid', 'http://www.korpmf.se', 56.064903, 12.692453); -- 28
insert into clubs (name, url, latitude, longitude) values ('Korpen Laholm', 'http://www.laget.se/LAHOLMSKORPEN', 56.521196, 12.951856); -- 29
insert into clubs (name, url, latitude, longitude) values ('Korpen Hultsfred', 'http://www.freewebs.com/hultsfredskorpen/tips.htm', 57.492021, 15.863405); -- 30
insert into clubs (name, url, latitude, longitude) values ('Korpen Kalmar', 'http://www3.idrottonline.se/KorpforeningKalmar-Korpen/Tipspromenad', 56.649738, 16.327086); -- 31
insert into clubs (name, url, latitude, longitude) values ('Ulricehamns IF', 'http://www2.idrottonline.se/UlricehamnsIF/Tipspromenader', 58.021346, 13.569172); -- 32
insert into clubs (name, url, latitude, longitude) values ('Sol Tranås - Orientering', 'http://www2.idrottonline.se/SolTranas-Orientering/Tipspromenad', 58.006033, 15.012013); -- 33
insert into clubs (name, url, latitude, longitude) values ('Korpen Vetlanda', 'http://www2.idrottonline.se/KorpforeningVetlanda-Korpen/Tipspromenad', 57.409389, 15.046114); -- 34
insert into clubs (name, url, latitude, longitude) values ('IK Wilske ', 'http://www5.idrottonline.se/IKWilske/Tipspromenader', 58.175040, 13.521858); -- 35
insert into clubs (name, url, latitude, longitude) values ('SOK Aneby', 'http://www1.idrottonline.se/SOKAneby-Orientering/Arrangemang/Tipspromenader', 57.835787, 14.824646); -- 36
insert into clubs (name, url, latitude, longitude) values ('Skene SoIS', 'http://www6.idrottonline.se/SkeneSimoIS/Tipspromenader', 57.508457, 12.633878); -- 37
insert into clubs (name, url, latitude, longitude) values ('Korpen Hässleholm', 'http://www3.idrottonline.se/KorpforeningHassleholm-Korpen/Tipsochbingo', 56.151420, 13.746930); -- 38
insert into clubs (name, url, latitude, longitude) values ('Ljungskile Friluftsklubb', 'http://www.ljungskilefriluftsklubb.se/skogs-tipspromenader', 58.234191, 11.983032); -- 39
insert into clubs (name, url, latitude, longitude) values ('Kinnaströms Sportklubb', 'http://www.kinnastromssk.se', 57.524947, 12.663593); -- 40
insert into clubs (name, url, latitude, longitude) values ('OK Kolmården', 'http://okkolmarden.com/index.php/regular/show/tipspromenad', 58.690470, 16.347755); -- 41
insert into clubs (name, url, latitude, longitude) values ('Husqvarna Innebandy Klubb', 'http://www.svenskalag.se/userpage.asp?teamID=6139&UserPageID=17989', 57.789992, 14.271538); -- 42
insert into clubs (name, url, latitude, longitude) values ('Sjömarkens IF ', 'http://www.laget.se/sjomarkensif/Page/120507', 57.717746, 12.841693); -- 43
insert into clubs (name, url, latitude, longitude) values ('OK Skogshjortarna Alingsås', 'http://www.skogshjortarna.com/TipsPromenad.aspx', 57.925416, 12.564884); -- 44
insert into clubs (name, url, latitude, longitude) values ('Norrby IF', 'http://www.norrbyif.se/tipspromenad-242', 57.721517, 12.918141); -- 45
insert into clubs (name, url, latitude, longitude) values ('Onsjö GK', 'http://www.onsjogk.com', 58.334244, 12.331755); -- 46
insert into clubs (name, url, latitude, longitude) values ('IF Hagen', 'http://www.ifhagen.com/orientering.asp?category=tipspromenad_ol', 58.402089, 13.817700); -- 47
insert into clubs (name, url, latitude, longitude) values ('Jönköpings GK', 'http://www.jonkopingsgk.se', 57.751094, 14.140559); -- 48
insert into clubs (name, url, latitude, longitude) values ('Sparsörs AIK', 'http://www.sparsorsaik.nu/Page/176981', 57.803298, 13.012878); -- 49
insert into clubs (name, url, latitude, longitude) values ('Mariedals IK', 'http://www.mariedal.se/?sid=1&s=2&id=236', 57.706319, 12.977286); -- 50
insert into clubs (name, url, latitude, longitude) values ('HK Drott Halmstad', 'http://www.hkdrott.se', 56.674134, 12.888933); -- 51
insert into clubs (name, url, latitude, longitude) values ('Hestra IF', 'http://www.hestraif.se/index.php?page=tipspromenader', 57.749400, 12.887575); -- 52

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
