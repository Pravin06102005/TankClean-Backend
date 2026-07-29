CREATE TABLE feedback (
    feedback_id BIGSERIAL PRIMARY KEY,
    booking_id BIGSERIAL UNIQUE,
    rating BIGSERIAL,
    comment VARCHAR(200),
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);