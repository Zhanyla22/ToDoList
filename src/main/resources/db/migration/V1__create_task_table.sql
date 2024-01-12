CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255),
    task_status VARCHAR(255),
    status VARCHAR(255),
    created_date TIMESTAMP,
    updated_date TIMESTAMP
    );

INSERT INTO task (description, task_status, status, created_date, updated_date)
VALUES
    ('Task 1', 'NOT_EXECUTED', 'ACTIVE', NOW(), null),
    ('Task 2', 'DONE', 'DELETED', NOW(), null);
