CREATE TABLE IF NOT EXISTS vendors (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    vendor_code VARCHAR(50) NOT NULL UNIQUE,

    vendor_name VARCHAR(150) NOT NULL,

    contact_person VARCHAR(100),

    email VARCHAR(120) NOT NULL UNIQUE,

    phone VARCHAR(20),

    address TEXT,

    performance_score DECIMAL(5,2) DEFAULT 0.00,

    delivery_rating DECIMAL(3,2) DEFAULT 0.00,

    quality_rating DECIMAL(3,2) DEFAULT 0.00,

    response_time INT,

    total_orders INT DEFAULT 0,

    completed_orders INT DEFAULT 0,

    cancelled_orders INT DEFAULT 0,

    status VARCHAR(20) DEFAULT 'ACTIVE',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_vendor_name
ON vendors(vendor_name);

CREATE INDEX idx_vendor_status
ON vendors(status);

CREATE INDEX idx_vendor_score
ON vendors(performance_score);