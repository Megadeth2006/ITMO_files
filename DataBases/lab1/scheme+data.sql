CREATE TABLE Creatures (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) CHECK (type IN ('человек', 'динозавр')) NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Human_Attributes (
    creature_id INT PRIMARY KEY REFERENCES Creatures(id),
    status VARCHAR(50) CHECK (status IN ('жив', 'ранен', 'мертв')) NOT NULL
);

CREATE TABLE Dinosaur_Attributes (
    creature_id INT PRIMARY KEY REFERENCES Creatures(id),
    species VARCHAR(100) NOT NULL,
    size FLOAT NOT NULL
);

CREATE TABLE Locations (
    id SERIAL PRIMARY KEY,
    terrain_type VARCHAR(100) NOT NULL
);

CREATE TABLE Events (
    id SERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL, 
    end_time TIMESTAMP NULL, 
    description TEXT, 
    status VARCHAR(50) CHECK (status IN ('в процессе', 'завершено'))
);

CREATE TABLE Creature_Locations (
    id SERIAL PRIMARY KEY,
    creature_id INT REFERENCES Creatures(id),
    location_id INT REFERENCES Locations(id),
    start_time TIMESTAMP NOT NULL, 
    end_time TIMESTAMP NULL, 
    activity TEXT 
);

CREATE TABLE Creature_Events (
    id SERIAL PRIMARY KEY,
    creature_id INT REFERENCES Creatures(id),
    event_id INT REFERENCES Events(id),
    role VARCHAR(100) 
);

CREATE TABLE Creature_Reactions (
    id SERIAL PRIMARY KEY,
    creature_id INT REFERENCES Creatures(id),
    event_id INT REFERENCES Events(id),
    reaction TEXT 
);

CREATE TABLE Event_Consequences (
    id SERIAL PRIMARY KEY,
    event_id INT REFERENCES Events(id),
    consequence TEXT 
);

INSERT INTO Creatures (type, name) VALUES 
    ('человек', 'Алекс'),
    ('человек', 'Мария'),
    ('динозавр', 'Тираннозавр'),
    ('динозавр', 'Велоцираптор');

INSERT INTO Human_Attributes (creature_id, status) VALUES 
    (1, 'жив'),
    (2, 'ранен');

INSERT INTO Dinosaur_Attributes (creature_id, species, size) VALUES 
    (3, 'Tyrannosaurus Rex', 12.3),
    (4, 'Velociraptor', 2.0);

INSERT INTO Locations (terrain_type) VALUES 
    ('джунгли'),
    ('река'),
    ('пещера');

INSERT INTO Events (start_time, end_time, description, status) VALUES 
    ('2024-03-11 14:00:00', '2024-03-11 14:30:00', 'Нападение тираннозавра', 'завершено'),
    ('2024-03-11 15:00:00', NULL, 'Мария спряталась в пещере', 'в процессе');

INSERT INTO Creature_Locations (creature_id, location_id, start_time, end_time, activity) VALUES 
    (3, 1, '2024-03-11 14:00:00', '2024-03-11 14:30:00', 'Охота'),
    (2, 3, '2024-03-11 15:00:00', NULL, 'Скрывается в пещере');

INSERT INTO Creature_Events (creature_id, event_id, role) VALUES 
    (3, 1, 'преследователь'),
    (2, 2, 'жертва');

INSERT INTO Creature_Reactions (creature_id, event_id, reaction) VALUES 
    (3, 1, 'Рычание и атака'),
    (2, 2, 'Скрылась в пещере');

INSERT INTO Event_Consequences (event_id, consequence) VALUES 
    (1, 'Человек ранен'),
    (2, 'Мария продолжает скрываться');