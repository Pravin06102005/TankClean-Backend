CREATE TABLE service (
    service_id BIGSERIAL PRIMARY KEY,
    service_name VARCHAR(100) NOT NULL,
    description VARCHAR(200),
    price DOUBLE PRECISION NOT NULL,
    image BYTEA
);