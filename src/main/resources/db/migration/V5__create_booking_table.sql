CREATE TABLE booking (
    booking_id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL,
    address_id BIGSERIAL,
    service_id BIGSERIAL,
    worker_id BIGINT,
    booking_date DATE,
    service_date DATE,
    status VARCHAR(50),
    total_price DOUBLE PRECISION,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id),
    FOREIGN KEY (worker_id) REFERENCES worker(worker_id)
);