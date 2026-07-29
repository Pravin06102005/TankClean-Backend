CREATE TABLE worker (
    worker_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    status VARCHAR(20),
    image BYTEA
);