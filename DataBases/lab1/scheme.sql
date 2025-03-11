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
