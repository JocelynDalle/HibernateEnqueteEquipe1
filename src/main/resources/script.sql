INSERT INTO fact (wording) VALUES
("Voyage Clermont-Fd - Barcelone"),
("Soupe de pâtes alphabet"),
("Skis Rosignol");

INSERT INTO department (name, number) VALUES
("Ain", 01),
("Allier", 03),
("Ardèche", 07),
("Cantal", 15),
("Drôme", 26),
("Isère", 38),
("Loire", 42),
("Haute-Loire", 42),
("Puy-De-Dôme", 63),
("Rhône", 69),
("Savoie", 73),
("Haute-Savoie", 74);

INSERT INTO partnersite (name, url) VALUES
("20minutes.fr", "http://20minutes.fr"),
("Le Monde", "http://lemonde.fr"),
("La Montagne", "http://lamontagne.fr"),
("Topito", "http://topito.com");

INSERT INTO survey (DTYPE, date, name, price, script) VALUES
('SurveyPhone','2015-10-02', "Enquête sur les soupes dans la Loire", 1500, "Bonjour, aimez-vous les soupes ???"), 
('SurveyPhone','2016-09-22', "Enquête sur les skis dans le Puy-De-Dôme", 3800, "Bonjour, aimez-vous les skis ???"),
('SurveyPhone','2017-01-09', "Enquête sur les Voyages vers Barcelone", 3300, "Bonjour, aimez-vous les avions ???");

INSERT INTO survey (DTYPE, date, name, price) VALUES
('SurveyInternet','2015-10-05', "Enquête sur les soupes dans la Loire", 750), 
('SurveyInternet','2016-09-25', "Enquête sur les skis dans le Puy-De-Dôme", 1900),
('SurveyInternet','2017-01-14', "Enquête sur les Voyages vers Barcelone", 1700);

INSERT INTO survey_partnersite VALUES
(4, 1),
(4, 2),
(5, 3),
(6, 1),
(6, 3),
(6, 4);

INSERT INTO question (wording, survey_id) VALUES 
("Que pensez-vous des soupes alphabet en général ?", 1),
("Que pensez-vous des soupes alphabet en général ?", 4),
("Préférez-vous les pâtes ou les légumes ?", 1),
("Pratiquez-vous le ski régulièrement ?", 2),
("Êtes-vous déjà allé à Barcelone ?", 3);

INSERT INTO criteria (quota, department_id, fact_id, survey_id) VALUES 
(100, 7, 2, 1),
(250, 9, 3, 2),
(200, 1, 1, 3);




