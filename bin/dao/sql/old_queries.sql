CREATE DATABASE TRIVIA

-- CREATE TABLE PlayerQuizHistory (
--     history_id INT IDENTITY(0,1) PRIMARY KEY,
--     player_id INT,
--     quiz_date DATE NOT NULL,
--     score INT NOT NULL,
--     FOREIGN KEY (player_id) REFERENCES Players(player_id)
-- );

SELECT @@SERVERNAME AS ServerName;

SELECT
    fk.name AS ForeignKeyName
FROM
    sys.foreign_keys AS fk
JOIN
    sys.tables AS t ON fk.parent_object_id = t.object_id
WHERE
    t.name = 'PlayerQuizHistory';



ALTER TABLE PlayerQuizHistory
DROP CONSTRAINT FK__PlayerQui__playe__66603565;

SELECT 1 From PlayerQuizHistory
WHERE player_id = 1


ALTER TABLE PlayerQuizHistory
ADD CONSTRAINT FK__PlayerQui__playe__66603565 -- Use the same name or a new one
FOREIGN KEY (player_id) REFERENCES Players(player_id) ON DELETE CASCADE;



SELECT COUNT(*) AS total_rows
FROM PlayerQuizHistory;

select player_id FROM PlayerQuizHistory

-- DROP TABLE Players;
-- DROP TABLE PlayerQuizHistory;


-- CREATE TABLE Questions (
--     question_id INT IDENTITY(0,1) PRIMARY KEY,
--     question_text TEXT NOT NULL,
--     correct_answer TEXT NOT NULL,
--     category VARCHAR(255)  -- Example: 'Geography', 'Math', etc.
-- );

ALTER TABLE Questions
ADD CONSTRAINT chk_question_ask_type
CHECK (question_ask_type IN ('MCQ', 'FBQ', 'IQ'));

ALTER TABLE Questions
ADD question_choices VARCHAR(200);

ALTER TABLE Questions
ADD question_ask_type VARCHAR(50);

ALTER TABLE Questions
DROP CONSTRAINT chk_question_ask_type;

TRUNCATE TABLE Questions;
TRUNCATE TABLE Players;

DELETE FROM Players;
DELETE FROM PlayerQuizHistory

Select * from Players
Select * from Questions
Select * from PlayerQuizHistory

-- SELECT p.player_name, p.quizzes_played, pq.quiz_date, pq.score 
-- FROM PlayerQuizHistory pq 
-- JOIN Players p ON pq.player_id = p.player_id

-- Select Players.player_name

ALTER TABLE Players ADD CONSTRAINT unique_player_name UNIQUE (player_name);