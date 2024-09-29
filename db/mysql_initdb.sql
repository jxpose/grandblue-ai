CREATE TABLE product (
    product_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT
);

INSERT INTO product (product_name, product_description) VALUES
('Aqualung Wetsuit', 'A premium wetsuit designed for warmth and flexibility, perfect for cold-water diving.'),
('Mares Dive Computer', 'A top-tier dive computer with easy-to-read display, advanced algorithms, and wireless connectivity.'),
('Cressi Fins', 'High-performance diving fins offering great propulsion and comfort for long dives.'),
('Suunto Dive Watch', 'A durable dive watch with water resistance up to 100 meters and built-in compass for underwater navigation.'),
('Scubapro BCD', 'A buoyancy control device (BCD) providing exceptional stability and comfort during your dives.'),
('Atomic Aquatics Regulator', 'High-quality regulator ensuring smooth, easy breathing at all depths.'),
('Oceanic Dive Mask', 'Wide-vision mask with anti-fog coating, ideal for clear underwater views.'),
('Seac Sub Dry Bag', 'A waterproof dry bag to keep your personal belongings safe and dry on boat trips.'),
('Tusa Snorkel', 'Ergonomically designed snorkel with a flexible tube and water-clearing valve.'),
('IST Dive Knife', 'Stainless steel dive knife with a durable handle, ideal for underwater cutting and emergency situations.');