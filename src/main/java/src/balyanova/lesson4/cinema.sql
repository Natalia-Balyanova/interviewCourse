--создание схемы
CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

--создание таблицы `films`
CREATE TABLE `cinema`.`films` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(150) NOT NULL,
  `length` INT NOT NULL,
  PRIMARY KEY (`id`));

--переименовываю колонку, забыла, что слово `length` зарезервировано
  ALTER TABLE `cinema`.`films`
  CHANGE COLUMN `length` `length_minutes` INT(11) NOT NULL ;

--создание таблицы `sessions`
 CREATE TABLE `cinema`.`sessions` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `film_id` INT NOT NULL,
   `price` INT NOT NULL,
   `start_time` DATETIME NOT NULL,
   PRIMARY KEY (`id`),
   INDEX `fk_sessions_films_idx` (`film_id` ASC),
   CONSTRAINT `fk_sessions_films`
     FOREIGN KEY (`film_id`)
     REFERENCES `cinema`.`films` (`id`)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION);

--создание таблицы `sold_tickets`
CREATE TABLE `cinema`.`sold_tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `place_number` INT NOT NULL,
  `session_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_sessions_idx` (`session_id` ASC),
  CONSTRAINT `fk_tickets_sessions`
    FOREIGN KEY (`session_id`)
    REFERENCES `cinema`.`sessions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

--вносим данные в таблицу 'films'
INSERT INTO `cinema`.`films` (`id`, `title`, `length_minutes`) VALUES ('1', 'Uncharted', '120');
INSERT INTO `cinema`.`films` (`id`, `title`, `length_minutes`) VALUES ('2', 'LA-LA-Land', '120');
INSERT INTO `cinema`.`films` (`id`, `title`, `length_minutes`) VALUES ('3', 'Finnik', '90');
INSERT INTO `cinema`.`films` (`id`, `title`, `length_minutes`) VALUES ('4', 'Room 203', '90');
INSERT INTO `cinema`.`films` (`id`, `title`, `length_minutes`) VALUES ('5', 'Paw Patrol', '60');

--вносим данные в таблицу `sessions`
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('1', '5', '100', '2022-05-03 9:00:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('2', '1', '200', '2022-05-03 11:00:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('3', '3', '200', '2022-05-03 12:30:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('4', '2', '300', '2022-05-03 14:30:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('5', '1', '300', '2022-05-03 16:00:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('6', '4', '300', '2022-05-03 17:30:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('7', '5', '300', '2022-05-03 19:00:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('8', '3', '300', '2022-05-03 20:30:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('9', '4', '400', '2022-05-03 22:00:00');
INSERT INTO `cinema`.`sessions` (`id`, `film_id`, `price`, `start_time`) VALUES ('10', '2', '400', '2022-05-03 23:30:00');

--вносим данные в таблицу `sold_tickets`
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('1', '10', '1');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('2', '11', '1');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('3', '5', '2');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('4', '6', '2');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('5', '7', '3');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('6', '1', '4');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('7', '2', '4');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('8', '6', '5');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('9', '1', '6');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('10', '2', '6');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('11', '3', '6');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('12', '9', '7');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('13', '10', '7');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('14', '20', '8');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('15', '21', '8');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('16', '1', '9');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('17', '2', '9');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('18', '8', '10');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('19', '9', '10');
INSERT INTO `cinema`.`sold_tickets` (`id`, `place_number`, `session_id`) VALUES ('20', '10', '10');

--Выполняем запросы.
1: ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

--создаем вью с данными по расписанию
CREATE VIEW SessionInfo AS
SELECT id, film_id, start_time, LEAD(start_time, 1) OVER (ORDER BY start_time) AS next_start,
LEAD(film_id, 1) OVER (ORDER BY start_time) AS next_film_id,
date_add(start_time, INTERVAL (SELECT films.length_minutes FROM films
WHERE films.id = sessions.film_id) minute) AS end_time
FROM sessions;

--сам запрос
SELECT films.title as film_1, SessionInfo.start_time as start_time_1,
films.length_minutes as length_1,
(SELECT films.title FROM films where SessionInfo.next_film_id = films.id) as film_2,
SessionInfo.next_start as start_time_2,
(SELECT films.length_minutes FROM films where SessionInfo.next_film_id = films.id) as length_2
FROM SessionInfo
INNER JOIN films ON
SessionInfo.film_id = films.id
WHERE end_time > next_start;

2: перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
--здесь у меня только один фильм получился
SELECT films.title AS film_1, SessionInfo.start_time,
 films.length_minutes AS length_1, SessionInfo.next_start,
 TIMESTAMPDIFF(MINUTE, SessionInfo.end_time, SessionInfo.next_start) AS pauses
 FROM SessionInfo
 INNER JOIN films ON SessionInfo.film_id = films.id
 HAVING pauses > 30 ORDER BY pauses;

3:список фильмов, для каждого — с указанием общего числа посетителей за все время,
среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

--для этого запроса создадим 2 вьюшки
CREATE VIEW VisitCounters AS
SELECT films.title, COUNT(sold_tickets.place_number) AS visitors, sold_tickets.session_id
FROM sold_tickets
INNER JOIN sessions
ON sold_tickets.session_id = sessions.id
INNER JOIN films
ON sessions.film_id = films.id
GROUP BY sold_tickets.session_id;

CREATE VIEW Average AS
SELECT title, AVG(visitors) AS average FROM VisitCounters GROUP BY title;

--здесь будем округлять количество посетителей, оставив 1 цифру после запятой
SELECT films.title, COUNT(sold_tickets.place_number) AS number_of_visitors, SUM(sessions.price) AS profit,
ROUND(AVG(Average.average), 1) AS average_visitors_count
FROM sold_tickets
INNER JOIN sessions
ON sold_tickets.session_id = sessions.id
INNER JOIN films
ON sessions.film_id=films.id
INNER JOIN Average
ON films.title = Average.title
GROUP BY films.title
UNION
SELECT 'итого', COUNT(sold_tickets.place_number) AS number_of_visitors, SUM(sessions.price) AS profit,
ROUND(AVG(Average.average), 1) AS average_visitors_count
FROM sold_tickets
INNER JOIN sessions
ON sold_tickets.session_id = sessions.id
INNER JOIN films
ON sessions.film_id = films.id
INNER JOIN Average
ON films.title = Average.title;

4: число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT COUNT(sold_tickets.place_number), SUM(sessions.price), MIN(sessions.start_time), MAX(sessions.start_time)
FROM sold_tickets INNER JOIN sessions
ON sessions.id = sold_tickets.session_id
WHERE start_time BETWEEN '2022-05-03 09%' AND '2022-05-03 15%'
UNION
SELECT COUNT(sold_tickets.place_number), SUM(sessions.price), MIN(sessions.start_time), MAX(sessions.start_time)
FROM sold_tickets INNER JOIN sessions
ON sessions.id = sold_tickets.session_id
WHERE start_time BETWEEN '2022-05-03 15%' AND '2022-05-03 18%'
UNION
SELECT COUNT(sold_tickets.place_number), SUM(sessions.price), MIN(sessions.start_time), MAX(sessions.start_time)
FROM sold_tickets INNER JOIN sessions
ON sessions.id = sold_tickets.session_id
WHERE start_time BETWEEN '2022-05-03 18%' AND '2022-05-03 21%'
UNION
SELECT COUNT(sold_tickets.place_number), SUM(sessions.price), MIN(sessions.start_time), MAX(sessions.start_time)
FROM sold_tickets INNER JOIN sessions
ON sessions.id = sold_tickets.session_id
WHERE start_time BETWEEN '2022-05-03 21%' AND '2022-05-03 23:59%';
