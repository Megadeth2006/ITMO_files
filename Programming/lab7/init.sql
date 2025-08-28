-- Create users table if not exists
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(255) PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS collection_id_seq;


CREATE TABLE IF NOT EXISTS coordinates (
    id SERIAL PRIMARY KEY,
    x INTEGER NOT NULL CHECK (x > -549),
    y INTEGER NOT NULL CHECK (y > -267)
    );

CREATE TABLE IF NOT EXISTS chapter (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_legion VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS collection (
    id INTEGER PRIMARY KEY DEFAULT nextval('collection_id_seq'),
    name VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    health DOUBLE PRECISION NOT NULL CHECK (health > 0),
    achievements VARCHAR(255),
    height FLOAT NOT NULL,
    melee_weapon VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL REFERENCES users(username),
    coordinates_id INTEGER REFERENCES coordinates(id) on delete cascade,
    chapter_id INTEGER REFERENCES chapter(id) on delete cascade
    );