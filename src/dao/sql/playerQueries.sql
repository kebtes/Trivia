-- CREATE TABLE Players (
--     player_id INT IDENTITY(0,1) PRIMARY KEY,
--     player_name VARCHAR(255) NOT NULL,
--     total_score INT DEFAULT 0,  
--     quizzes_played INT DEFAULT 0  
-- );

SELECT p.player_name, pq.quiz_date, pq.score
FROM PlayerQuizHistory pq
JOIN players p ON pq.player_id = p.player_id;



SELECT * FROM Players



DELETE From Players
WHERE player_id = 32

UPDATE Players
SET player_name = 'comeone'
WHERE player_id = 8;
