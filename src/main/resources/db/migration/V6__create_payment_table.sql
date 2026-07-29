CREATE TABLE payment (
    payment_id BIGSERIAL PRIMARY KEY,
    booking_id BIGSERIAL UNIQUE,
    amount DOUBLE PRECISION,
    payment_date DATE,
    payment_status VARCHAR(20),
    payment_method VARCHAR(20),
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);