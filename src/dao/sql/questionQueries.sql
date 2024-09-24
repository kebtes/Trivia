SELECT TOP 1 * FROM Questions
WHERE category = 'General'
ORDER BY NEWID();






SELECT * FROM Questions
WHERE category = 'Music';

DELETE TOP (100) FROM Questions;

DELETE FROM Questions
WHERE CAST(correct_answer AS VARCHAR(MAX)) IN ('True', 'False');


SELECT * From Questtions
DELETE FROM Questions
WHERE CAST(correct_answer AS VARCHAR(MAX)) IN ('1,000,000');



-- Math Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('What is the value of the integral of x dx from 0 to 4?', '8', 'Math', 'MCQ', '8, 16, 4, 12'),
('If a rectangle has a length of 10 units and a width of 5 units, what is its area?', '50 square units', 'Math', 'MCQ', '15 square units, 50 square units, 100 square units, 25 square units'),
('What is the sum of the angles in a triangle?', '180 degrees', 'Math', 'FBQ', NULL),
('What is 15% of 200?', '30', 'Math', 'MCQ', '25, 30, 35, 40'),
('If x = 2, what is the value of 3x + 4?', '10', 'Math', 'MCQ', '8, 10, 12, 14');

-- General Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('Which is the largest ocean on Earth?', 'Pacific Ocean', 'General', 'MCQ', 'Atlantic Ocean, Indian Ocean, Arctic Ocean, Pacific Ocean'),
('What is the largest mammal in the world?', 'Blue Whale', 'General', 'MCQ', 'Elephant, Blue Whale, Giraffe, Great White Shark'),
('The Great Wall of China was primarily built for ____.', 'Defense', 'General', 'FBQ', NULL),
('Who wrote the play "Romeo and Juliet"?', 'William Shakespeare', 'General', 'MCQ', 'Charles Dickens, William Shakespeare, Mark Twain, Jane Austen'),
('Which country is known as the Land of the Rising Sun?', 'Japan', 'General', 'MCQ', 'China, Japan, Thailand, South Korea');

-- Movie Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('Which animated film features a young girl named Moana?', 'Moana', 'Movie', 'MCQ', 'Frozen, Moana, Tangled, Zootopia'),
('Who directed the movie "Inception"?', 'Christopher Nolan', 'Movie', 'MCQ', 'Steven Spielberg, Christopher Nolan, Quentin Tarantino, Martin Scorsese'),
('Which film features the quote "Ill be back"?', 'The Terminator', 'Movie', 'MCQ', 'Die Hard, The Matrix, The Terminator, Predator'),
('What is the highest-grossing film of all time (as of 2021)?', 'Avatar', 'Movie', 'MCQ', 'Avengers: Endgame, Avatar, Titanic, Star Wars: The Force Awakens'),
('In "The Godfather", who is the head of the Corleone family?', 'Vito Corleone', 'Movie', 'FBQ', NULL);

-- Music Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('Who was the first female artist to win a Grammy Award for Album of the Year?', 'Billie Eilish', 'Music', 'MCQ', 'Taylor Swift, Adele, Billie Eilish, Whitney Houston'),
('What is the highest-selling album of all time?', 'Thriller', 'Music', 'MCQ', 'Back in Black, The Dark Side of the Moon, Thriller, Rumours'),
('Which song begins with the lyrics "Is this the real life? Is this just fantasy?"', 'Bohemian Rhapsody', 'Music', 'FBQ', NULL),
('Who sang the hit song "Rolling in the Deep"?', 'Adele', 'Music', 'MCQ', 'Beyoncé, Adele, Taylor Swift, Lady Gaga'),
('What genre of music is characterized by the use of heavy guitar riffs and strong beats?', 'Rock', 'Music', 'MCQ', 'Jazz, Classical, Rock, Hip Hop');



-- Movie Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('Which actor portrayed Jack Dawson in "Titanic"?', 'Leonardo DiCaprio', 'Movie', 'MCQ', 'Brad Pitt, Johnny Depp, Leonardo DiCaprio, Matt Damon'),
('What is the name of the fictional African country in "Black Panther"?', 'Wakanda', 'Movie', 'MCQ', 'Narnia, Wakanda, Elbonia, Genosha'),
('In "Finding Nemo", what type of fish is Nemo?', 'Clownfish', 'Movie', 'MCQ', 'Goldfish, Clownfish, Angelfish, Betta'),
('Which movie features the song "Let It Go"?', 'Frozen', 'Movie', 'MCQ', 'Tangled, Frozen, Moana, Zootopia'),
('What year was the first "Harry Potter" film released?', '2001', 'Movie', 'FBQ', NULL);

-- Music Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('Who is the lead singer of the band U2?', 'Bono', 'Music', 'MCQ', 'Mick Jagger, Bono, Freddie Mercury, Chris Martin'),
('Which song by Whitney Houston became a worldwide hit in 1992?', 'I Will Always Love You', 'Music', 'MCQ', 'Greatest Love of All, I Will Always Love You, How Will I Know, Saving All My Love for You'),
('Which famous music festival takes place in the California desert?', 'Coachella', 'Music', 'MCQ', 'Lollapalooza, Glastonbury, Coachella, Bonnaroo'),
('Who is known for the hit single "Shape of You"?', 'Ed Sheeran', 'Music', 'MCQ', 'Justin Bieber, Ed Sheeran, Shawn Mendes, Bruno Mars'),
('What genre is the song "Lose Yourself" by Eminem?', 'Hip Hop', 'Music', 'FBQ', NULL);

-- General Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES
('What is the largest planet in our solar system?', 'Jupiter', 'General', 'MCQ', 'Earth, Mars, Jupiter, Saturn'),
('Who painted the Mona Lisa?', 'Leonardo da Vinci', 'General', 'MCQ', 'Pablo Picasso, Vincent van Gogh, Leonardo da Vinci, Claude Monet'),
('What is the capital city of Australia?', 'Canberra', 'General', 'MCQ', 'Sydney, Melbourne, Canberra, Brisbane'),
('Which element has the chemical symbol "O"?', 'Oxygen', 'General', 'FBQ', NULL),
('What is the hardest natural substance on Earth?', 'Diamond', 'General', 'MCQ', 'Gold, Iron, Diamond, Quartz');

SELECT * FROM Questions


-- Math Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES 
('What is the result of 7 factorial (7!)?', '5040', 'Math', 'MCQ', '2520, 5040, 7560, 10080'),
('In a right-angled triangle, what is the square of the hypotenuse equal to?', 'Sum of squares of other two sides', 'Math', 'FBQ', NULL),
('What is the value of x in the equation 4x - 9 = 23?', '8', 'Math', 'MCQ', '6, 7, 8, 9'),
('What is the area of a circle with diameter 10 units?', '78.54 square units', 'Math', 'MCQ', '25π square units, 78.54 square units, 100 square units, 50π square units'),
('What is the next number in the Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, __?', '13', 'Math', 'MCQ', '11, 12, 13, 15'),
('What is the slope of a line parallel to the x-axis?', '0', 'Math', 'FBQ', NULL),
('If log(x) = 2, what is the value of x?', '100', 'Math', 'MCQ', '10, 50, 100, 1000'),
('What is the perimeter of a regular hexagon with side length 5 units?', '30 units', 'Math', 'MCQ', '25 units, 30 units, 35 units, 40 units');

-- General Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES 
('Which country is home to the Taj Mahal?', 'India', 'General', 'MCQ', 'Pakistan, India, Bangladesh, Nepal'),
('What is the largest desert in the world?', 'Antarctic Desert', 'General', 'MCQ', 'Sahara Desert, Gobi Desert, Antarctic Desert, Arabian Desert'),
('Who invented the telephone?', 'Alexander Graham Bell', 'General', 'MCQ', 'Thomas Edison, Alexander Graham Bell, Nikola Tesla, Guglielmo Marconi'),
('What is the smallest country in the world?', 'Vatican City', 'General', 'FBQ', NULL),
('Which river is the longest in the world?', 'Nile', 'General', 'MCQ', 'Amazon, Nile, Yangtze, Mississippi'),
('In what year did World War II end?', '1945', 'General', 'MCQ', '1943, 1944, 1945, 1946'),
('What is the main language spoken in Brazil?', 'Portuguese', 'General', 'MCQ', 'Spanish, Portuguese, English, French'),
('Who wrote the novel "1984"?', 'George Orwell', 'General', 'FBQ', NULL);

-- Movie Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES 
('Which actor played James Bond in the film "Skyfall"?', 'Daniel Craig', 'Movie', 'MCQ', 'Pierce Brosnan, Daniel Craig, Sean Connery, Roger Moore'),
('What is the name of the fictional city where Batman operates?', 'Gotham City', 'Movie', 'MCQ', 'Metropolis, Gotham City, Central City, Star City'),
('Who directed the movie "Jurassic Park"?', 'Steven Spielberg', 'Movie', 'MCQ', 'James Cameron, Steven Spielberg, George Lucas, Ridley Scott'),
('In "The Lion King", what is the name of Simbas uncle?', 'Scar', 'Movie', 'FBQ', NULL),
('Which actress won an Oscar for her role in "The Favorite"?', 'Olivia Colman', 'Movie', 'MCQ', 'Emma Stone, Olivia Colman, Rachel Weisz, Melissa McCarthy'),
('What is the name of the fictional metal in the Marvel universe?', 'Vibranium', 'Movie', 'MCQ', 'Adamantium, Vibranium, Unobtanium, Kryptonite'),
('Who played Hermione Granger in the Harry Potter film series?', 'Emma Watson', 'Movie', 'MCQ', 'Emma Watson, Emma Stone, Emma Roberts, Emily Blunt'),
('What is the name of the prison in "The Shawshank Redemption"?', 'Shawshank State Penitentiary', 'Movie', 'FBQ', NULL);

-- Music Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES 
('Which legendary guitarist was known as "Slowhand"?', 'Eric Clapton', 'Music', 'MCQ', 'Jimmy Page, Eric Clapton, Jimi Hendrix, Keith Richards'),
('What was the best-selling album of the 1980s?', 'Thriller', 'Music', 'MCQ', 'Back in Black, Thriller, The Joshua Tree, Born in the U.S.A.'),
('Which band released the album "Dark Side of the Moon"?', 'Pink Floyd', 'Music', 'MCQ', 'Led Zeppelin, Pink Floyd, The Who, The Rolling Stones'),
('What is the stage name of Robyn Fenty?', 'Rihanna', 'Music', 'FBQ', NULL),
('Which music streaming service was founded in Sweden?', 'Spotify', 'Music', 'MCQ', 'Apple Music, Spotify, Tidal, Amazon Music'),
('Who is the lead vocalist of the rock band Coldplay?', 'Chris Martin', 'Music', 'MCQ', 'Thom Yorke, Chris Martin, Matt Bellamy, Brandon Flowers'),
('Which rapper released the album "The Marshall Mathers LP"?', 'Eminem', 'Music', 'MCQ', 'Jay-Z, Eminem, Kanye West, Drake'),
('What instrument is Yo-Yo Ma famous for playing?', 'Cello', 'Music', 'FBQ', NULL);

-- Science Questions
INSERT INTO Questions (question_text, correct_answer, category, question_ask_type, question_choices)
VALUES 
('What is the atomic number of carbon?', '6', 'Science', 'MCQ', '4, 5, 6, 7'),
('Which planet has the Great Red Spot?', 'Jupiter', 'Science', 'MCQ', 'Mars, Jupiter, Saturn, Neptune'),
('What is the smallest unit of matter?', 'Atom', 'Science', 'MCQ', 'Molecule, Atom, Electron, Proton'),
('What is the process by which plants release water vapor into the atmosphere?', 'Transpiration', 'Science', 'FBQ', NULL),
('Which element has the highest melting point?', 'Carbon', 'Science', 'MCQ', 'Tungsten, Carbon, Iron, Platinum'),
('What is the study of fossils called?', 'Paleontology', 'Science', 'MCQ', 'Archaeology, Paleontology, Geology, Anthropology'),
('What is the unit of electric current?', 'Ampere', 'Science', 'MCQ', 'Volt, Ampere, Watt, Ohm'),
('What is the name of the closest star to our solar system?', 'Proxima Centauri', 'Science', 'FBQ', NULL);