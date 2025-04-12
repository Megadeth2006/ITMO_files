CREATE TABLE Human (
    human_id SERIAL PRIMARY KEY,
    name TEXT,
    age INT,
    gender TEXT
);

CREATE TABLE Dinosaur (
    dinosaur_id SERIAL PRIMARY KEY,
    species TEXT,
    size TEXT
);

CREATE TABLE Event (
    event_id SERIAL PRIMARY KEY,
    date DATE,
    start_time TIME,
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