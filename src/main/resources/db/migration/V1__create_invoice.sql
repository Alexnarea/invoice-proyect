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
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id)
    );


CREATE TABLE IF NOT EXISTS lead (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(50),
    created_at DATE,
    updated_at DATE
    );


CREATE TABLE IF NOT EXISTS activity (
    id SERIAL PRIMARY KEY,
    description TEXT,
    scheduled_at DATE,
    created_at DATE,
    updated_at DATE,
    lead_id INT NOT NULL,
    FOREIGN KEY (lead_id) REFERENCES lead(id) ON DELETE CASCADE
    );
