-- Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах
-- (достаточно получить только имя и возраст студента) школы Хогвартс
-- вместе с названиями факультетов.
SELECT s.name, s.age, f.name
FROM student s
         LEFT JOIN faculty f on f.id = s.faculty_id;

-- Составить второй JOIN-запрос, чтобы получить только тех студентов,
-- у которых есть аватарки.
SELECT s.id, s.name, s.age, a.file_path
FROM student s
         INNER JOIN avatar a on s.id = a.student_id;