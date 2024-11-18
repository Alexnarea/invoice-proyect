CREATE TABLE IF NOT EXISTS client (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS invoice (
    id SERIAL PRIMARY KEY,
    code VARCHAR(100),
    created_at DATE,
    total DECIMAL(9, 2),
    client_id Int NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
);