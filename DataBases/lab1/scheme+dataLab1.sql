CREATE TABLE Human (
    human_id SERIAL PRIMARY KEY,
    name TEXT,
    age INT CHECK (age > 0),
    gender TEXT CHECK (status IN('F', 'M'))
);

CREATE TABLE Dinosaur (
    dinosaur_id SERIAL PRIMARY KEY,
    species TEXT,
    size TEXT
);

CREATE TABLE Event (
    event_id SERIAL PRIMARY KEY,
    date DATE,
    start_time TIME NOT NULL,
    end_time TIME,
    description TEXT,
    status TEXT CHECK (status IN ('в процессе', 'завершено')) NOT NULL
);

CREATE TABLE Location (
    location_id SERIAL PRIMARY KEY,
    terrain_type TEXT,
    coordinates TEXT
);

CREATE TABLE Human_Reactions (
    human_id INT,
    event_id INT,
    reaction TEXT NOT NULL,
    PRIMARY KEY (human_id, event_id, reaction),
    FOREIGN KEY (human_id) REFERENCES Human(human_id),
    FOREIGN KEY (event_id) REFERENCES Event(event_id)
);

CREATE TABLE Dinosaur_Reactions (
    dinosaur_id INT,
    event_id INT,
    reaction TEXT NOT NULL,
    PRIMARY KEY (dinosaur_id, event_id, reaction),
    FOREIGN KEY (dinosaur_id) REFERENCES Dinosaur(dinosaur_id),
    FOREIGN KEY (event_id) REFERENCES Event(event_id)
);

CREATE TABLE Event_Consequences (
    event_id INT,
    consequence TEXT NOT NULL,
    PRIMARY KEY (event_id, consequence),
    FOREIGN KEY (event_id) REFERENCES Event(event_id)
);

CREATE TABLE Human_Events (
    human_id INT,
    event_id INT,
    role TEXT,
    PRIMARY KEY (human_id, event_id),
    FOREIGN KEY (human_id) REFERENCES Human(human_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE
);

CREATE TABLE Dinosaur_Events (
    dinosaur_id INT,
    event_id INT,
    role TEXT,
    PRIMARY KEY (dinosaur_id, event_id),
    FOREIGN KEY (dinosaur_id) REFERENCES Dinosaur(dinosaur_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE
);

CREATE TABLE Human_Locations (
    human_id INT,
    location_id INT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    PRIMARY KEY (human_id, location_id),
    FOREIGN KEY (human_id) REFERENCES Human(human_id) ON DELETE CASCADE,
    FOREIGN KEY (location_id) REFERENCES Location(location_id) ON DELETE CASCADE
);

CREATE TABLE Dinosaur_Locations (
    dinosaur_id INT,
    location_id INT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    PRIMARY KEY (dinosaur_id, location_id),
    FOREIGN KEY (dinosaur_id) REFERENCES Dinosaur(dinosaur_id) ON DELETE CASCADE,
    FOREIGN KEY (location_id) REFERENCES Location(location_id) ON DELETE CASCADE
);

INSERT INTO Human (name, age, gender) VALUES
('Лекси', 12, 'F'),
('Грант', 45, 'M');

INSERT INTO Dinosaur (species, size) VALUES
('Тиранозавр', 'огромный'),
('Микроцератопс', 'небольшой');

INSERT INTO Location (terrain_type, coordinates) VALUES
('лес', 'X:128,Y:45'),
('река', 'X:130,Y:48');

INSERT INTO Event (date, start_time, end_time, description, status) VALUES
('2025-03-27', '12:00', '12:10', 'Нападение в джунглях', 'завершено'),
('2025-03-27', '12:20', NULL, 'Сокрытие от динозавра', 'в процессе');

INSERT INTO Human_Events VALUES
(1, 1, 'наблюдатель'),
(2, 1, 'участник');

INSERT INTO Dinosaur_Events VALUES
(1, 1, 'нападающий'),
(2, 1, 'жертва');

INSERT INTO Human_Locations VALUES
(1, 1, '2025-03-27 11:50', '2025-03-27 12:05'),
(2, 2, '2025-03-27 11:55', '2025-03-27 12:10');

INSERT INTO Dinosaur_Locations VALUES
(1, 1, '2025-03-27 11:40', '2025-03-27 12:15'),
(2, 2, '2025-03-27 11:45', '2025-03-27 12:05');

INSERT INTO Human_Reactions VALUES
(1, 1, 'испуг'),
(2, 1, 'замешательство');

INSERT INTO Dinosaur_Reactions VALUES
(1, 1, 'агрессия'),
(1, 1, 'рев'),
(2, 1, 'бегство');

INSERT INTO Event_Consequences VALUES
(1, 'деревья повалены'),
(1, 'пугающий шум в лесу'),
(1, 'паника среди мелких динозавров');