CREATE TABLE address (
    address_id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    city VARCHAR(50) NOT NULL,
    area VARCHAR(50) NOT NULL,
    building VARCHAR(100) NOT NULL,
    pincode VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);