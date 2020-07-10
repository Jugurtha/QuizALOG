CREATE TABLE player (
		id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
		pseudo VARCHAR(10),
		name VARCHAR(50),
		email VARCHAR(320)
);

ALTER TABLE player
ADD CONSTRAINT uc_player UNIQUE (pseudo, email); 

CREATE TABLE game (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_player INTEGER,
	score INTEGER DEFAULT 0
);
ALTER TABLE game
ADD CONSTRAINT fk_game_player FOREIGN KEY (id_player) REFERENCES player(id);

CREATE TABLE question (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	text VARCHAR(256),
	id_correct_answer INTEGER DEFAULT NULL,
	score INTEGER DEFAULT 1
);

CREATE TABLE answer (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_question INTEGER,
	text VARCHAR(256)
);
ALTER TABLE answer
ADD CONSTRAINT fk_answer_question FOREIGN KEY (id_question) REFERENCES question(id);

ALTER TABLE question
ADD CONSTRAINT fk_question_answer FOREIGN KEY (id_correct_answer) REFERENCES answer(id); 

CREATE TABLE game_questions (
	id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_game INTEGER,
	id_question INTEGER,
	id_answer INTEGER
);
ALTER TABLE game_questions
ADD CONSTRAINT fk_game_questions_game FOREIGN KEY (id_game) REFERENCES game(id); 

ALTER TABLE game_questions
ADD CONSTRAINT fk_game_questions_question FOREIGN KEY (id_question) REFERENCES question(id); 

ALTER TABLE game_questions
ADD CONSTRAINT fk_game_questions_answer FOREIGN KEY (id_answer) REFERENCES answer(id); 


