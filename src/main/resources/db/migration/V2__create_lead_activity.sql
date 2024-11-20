CREATE TABLE lead (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255),
                      phone VARCHAR(50),
                      status ENUM('New', 'Contacted', 'Qualified', 'Closed') DEFAULT 'New',
                      created_at DATE  DEFAULT CURRENT_DATE,
                      updated_at DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_DATE
);


CREATE TABLE activity (
                          id SERIAL PRIMARY KEY,
                          type ENUM('Call', 'Email', 'Meeting', 'Follow-up') NOT NULL,
                          description TEXT,
                          scheduled_at DATE,
                          status ENUM('Pending', 'Completed', 'Cancelled') DEFAULT 'Pending',
                          created_at DATE DEFAULT CURRENT_DATE,
                          updated_at DATE DEFAULT CURRENT_DATE ON UPDATE CURRENT_DATE,
                          lead_id INT NOT NULL,
                          FOREIGN KEY (lead_id) REFERENCES lead(id) ON DELETE CASCADE
);
