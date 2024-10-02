CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	content text,
	metadata json,
	embedding vector(1536)
);

CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);

CREATE TABLE product (
    product_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
    product_quantity INT,
    unit_price INT
);

CREATE TABLE sales (
    sales_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    sales_amount INT,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sales_product (
    sales_id UUID,
    product_id UUID,
    quantity INT NOT NULL DEFAULT 1,
    PRIMARY KEY (sales_id, product_id),
    FOREIGN KEY (sales_id) REFERENCES sales(sales_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);


INSERT INTO product (product_name, product_description, product_quantity, unit_price) VALUES
('Aqualung Wetsuit', 'A premium wetsuit designed for warmth and flexibility, perfect for cold-water diving.', 2, 100),
('Mares Dive Computer', 'A top-tier dive computer with easy-to-read display, advanced algorithms, and wireless connectivity.', 0, 50),
('Cressi Fins', 'High-performance diving fins offering great propulsion and comfort for long dives.', 3, 200),
('Suunto Dive Watch', 'A durable dive watch with water resistance up to 100 meters and built-in compass for underwater navigation.', 4, 430),
('Scubapro BCD', 'A buoyancy control device (BCD) providing exceptional stability and comfort during your dives.', 5, 1000),
('Atomic Aquatics Regulator', 'High-quality regulator ensuring smooth, easy breathing at all depths.', 6, 100),
('Oceanic Dive Mask', 'Wide-vision mask with anti-fog coating, ideal for clear underwater views.', 10, 20),
('Seac Sub Dry Bag', 'A waterproof dry bag to keep your personal belongings safe and dry on boat trips.', 2, 10),
('Tusa Snorkel', 'Ergonomically designed snorkel with a flexible tube and water-clearing valve.', 1, 15),
('IST Dive Knife', 'Stainless steel dive knife with a durable handle, ideal for underwater cutting and emergency situations.', 1, 55);