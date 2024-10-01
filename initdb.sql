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
    product_quantity INT
);

INSERT INTO product (product_name, product_description, product_quantity) VALUES
('Aqualung Wetsuit', 'A premium wetsuit designed for warmth and flexibility, perfect for cold-water diving.', 2),
('Mares Dive Computer', 'A top-tier dive computer with easy-to-read display, advanced algorithms, and wireless connectivity.', 0),
('Cressi Fins', 'High-performance diving fins offering great propulsion and comfort for long dives.', 3),
('Suunto Dive Watch', 'A durable dive watch with water resistance up to 100 meters and built-in compass for underwater navigation.', 4),
('Scubapro BCD', 'A buoyancy control device (BCD) providing exceptional stability and comfort during your dives.', 5),
('Atomic Aquatics Regulator', 'High-quality regulator ensuring smooth, easy breathing at all depths.', 6),
('Oceanic Dive Mask', 'Wide-vision mask with anti-fog coating, ideal for clear underwater views.', 0),
('Seac Sub Dry Bag', 'A waterproof dry bag to keep your personal belongings safe and dry on boat trips.', 2),
('Tusa Snorkel', 'Ergonomically designed snorkel with a flexible tube and water-clearing valve.', 1),
('IST Dive Knife', 'Stainless steel dive knife with a durable handle, ideal for underwater cutting and emergency situations.', 1);