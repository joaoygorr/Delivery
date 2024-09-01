CREATE TABLE Category (
    id_category SERIAL PRIMARY KEY,
    label VARCHAR(255) NOT NULL
);

CREATE TABLE Product (
    id_product SERIAL PRIMARY KEY,
    category_id INT,
    reference VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) DEFAULT 0,
    complementary BOOLEAN DEFAULT FALSE,
    url_image VARCHAR(255) NOT NULL
);

CREATE TABLE Size (
    id_size SERIAL PRIMARY KEY,
    product_id INT,
    reference VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2),
    max_combination INT
);

CREATE TABLE ComplementaryCategory (
    id_complementary_categories SERIAL PRIMARY KEY,
    size_id INT,
    family VARCHAR(50),
    name VARCHAR(255) NOT NULL,
    min INT DEFAULT 0,
    max INT DEFAULT 0,
    max_free INT DEFAULT 0,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE Complementary (
    id_complementary SERIAL PRIMARY KEY,
    complementary_category_id INT,
    reference VARCHAR(50),
    size VARCHAR(50),
    price DECIMAL(10, 2) DEFAULT 0,
    name VARCHAR(255),
    quantity INT
);

-- CREATE FOREIGN KEYS
ALTER TABLE Product
ADD CONSTRAINT fk_categories
FOREIGN KEY (category_id) REFERENCES Category(id_category);

ALTER TABLE Size
ADD CONSTRAINT fk_products
FOREIGN KEY (product_id) REFERENCES Product(id_product);

ALTER TABLE ComplementaryCategory
ADD CONSTRAINT fk_sizes
FOREIGN KEY (size_id) REFERENCES Size(id_size);

ALTER TABLE Complementary
ADD CONSTRAINT fk_complementary_categories
FOREIGN KEY (complementary_category_id) REFERENCES ComplementaryCategory(id_complementary_categories);

-- CREATE INSERTS
-- INSERT Categories
INSERT INTO Category (label) VALUES
('BURGER''S'),
('COMBOS MB'),
('FRITAS MB'),
('BEBIDAS'),
('SOBREMESAS');

-- INSERT Products
INSERT INTO Product (category_id, reference, name, description, price, complementary, url_image) VALUES
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), '4', 'FOME TIPO-G', 'Pão Australiano, 2 hambúrgueres de 180g, queijo, salada e molho mb', 0, FALSE, 'https://i.imgur.com/SSanzqx.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), '1', 'MAGIC BURGER', 'Pão de hambúrguer com gergelim, hambúrguer 180g, queijo, salada, picles e cebola caramelizada', 0, FALSE, 'https://i.imgur.com/m53Cky3.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), '3', 'MB O BURGER', 'Pão de hambúrguer com gergelim, hambúrguer 180g, queijo, bacon, salada e molho mb', 0, FALSE, 'https://i.imgur.com/hMCyF8A.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), '6', 'MB O JUNIOR', 'Pão brioche, hambúrguer 90g, queijo, salada e molho mb', 0, FALSE, 'https://i.imgur.com/3U7HzPL.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), '5', 'SMASHNOLO', 'Pão brioche, 2 hambúrgueres de 90g, queijo, bacon e molho mb', 0, FALSE, 'https://i.imgur.com/9mGQjdL.png'),
((SELECT id_category FROM Category WHERE label = 'COMBOS MB'), '52', 'COMBO CASAL', '1 Mb o Burger + 1 Magic Burger + 1 Batata Mb + Refri 1,5l', 59.97, FALSE, 'https://i.imgur.com/2bMmv1I.png'),
((SELECT id_category FROM Category WHERE label = 'COMBOS MB'), '51', 'COMBO JUNIOR', '1 Mb o Junior + 1 Batata Mb', 23.99, FALSE, 'https://i.imgur.com/Qs01bKl.png'),
((SELECT id_category FROM Category WHERE label = 'COMBOS MB'), '50', 'COMBO MAGICO', '1 Magic Burger + 1 Batata Mb + Refri lata 310ml', 34.9, FALSE, 'https://i.imgur.com/j2ZxVf3.png'),
((SELECT id_category FROM Category WHERE label = 'FRITAS MB'), '11', 'MB - CHICKEN', 'Contém 5 unidades de Nuggets de frango.', 9.99, FALSE, 'https://i.imgur.com/D0FQblq.png'),
((SELECT id_category FROM Category WHERE label = 'FRITAS MB'), '10', 'MB - FRITAS', 'Á melhor batata palito frita', 7.99, FALSE, 'https://i.imgur.com/yGtiwDP.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), '2003', 'COCA COLA 1,5', 'Não basta ser coca, tem que estar gelada!', 10, FALSE, 'https://i.imgur.com/5EAAyev.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), '2000', 'COCA COLA 310ML', 'Não basta ser coca, tem que estar gelada!', 4.99, FALSE, 'https://i.imgur.com/EvDhaMv.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), '2004', 'FANTA 1,5', 'Com sabor irresistível e único, de laranja.', 10, FALSE, 'https://i.imgur.com/AtSAork.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), '2001', 'FANTA 310ML', 'Com sabor irresistível e único, de laranja.', 4.99, FALSE, 'https://i.imgur.com/SKgONaU.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), '2002', 'GUARANÁ ANTARCTICA 350ML', 'Brasileiro com sabor único e natural', 4.99, FALSE, 'https://i.imgur.com/OofLKtA.png'),
((SELECT id_category FROM Category WHERE label = 'SOBREMESAS'), '20', 'SOBREMESA MB', '', 12, FALSE, 'https://i.ytimg.com/vi/9_9YCWc8OEI/maxresdefault.jpg');

-- INSERT Sizes
INSERT INTO Size (product_id, reference, name, price, max_combination) VALUES
((SELECT id_product FROM Product WHERE reference = '4'), '1', 'BURGER', 34.99, 1),
((SELECT id_product FROM Product WHERE reference = '1'), '1', 'BURGER', 24.99, 1),
((SELECT id_product FROM Product WHERE reference = '3'), '1', 'BURGER', 24.99, 1),
((SELECT id_product FROM Product WHERE reference = '6'), '1', 'BURGER', 19.9, 1),
((SELECT id_product FROM Product WHERE reference = '5'), '1', 'BURGER', 28.99, 1);

-- INSERT Complementary Categories
INSERT INTO ComplementaryCategory (size_id, family, name, min, max, max_free, type) VALUES
((SELECT id_size FROM Size WHERE reference = '1' AND product_id = (SELECT id_product FROM Product WHERE reference = '4')), '1', 'TURBINE SEU BURGER', 0, 0, 0, 'ADD'),
((SELECT id_size FROM Size WHERE reference = '1' AND product_id = (SELECT id_product FROM Product WHERE reference = '1')), '1', 'TURBINE SEU BURGER', 0, 0, 0, 'ADD'),
((SELECT id_size FROM Size WHERE reference = '1' AND product_id = (SELECT id_product FROM Product WHERE reference = '3')), '1', 'TURBINE SEU BURGER', 0, 0, 0, 'ADD'),
((SELECT id_size FROM Size WHERE reference = '1' AND product_id = (SELECT id_product FROM Product WHERE reference = '6')), '1', 'TURBINE SEU BURGER', 0, 0, 0, 'ADD'),
((SELECT id_size FROM Size WHERE reference = '1' AND product_id = (SELECT id_product FROM Product WHERE reference = '5')), '1', 'TURBINE SEU BURGER', 0, 0, 0, 'ADD');

-- INSERT Complementary
INSERT INTO Complementary (complementary_category_id, reference, size, price, name, quantity) VALUES
((SELECT id_complementary_categories FROM ComplementaryCategory WHERE name = 'TURBINE SEU BURGER' AND family = '1' LIMIT 1), '1002', NULL, 5, 'BACON -', NULL),
((SELECT id_complementary_categories FROM ComplementaryCategory WHERE name = 'TURBINE SEU BURGER' AND family = '1' LIMIT 1), '1003', NULL, 3, 'CEBOLA CARAMELIZADA -', NULL),
((SELECT id_complementary_categories FROM ComplementaryCategory WHERE name = 'TURBINE SEU BURGER' AND family = '1' LIMIT 1), '1001', NULL, 3, 'CREAM CHEESE -', NULL),
((SELECT id_complementary_categories FROM ComplementaryCategory WHERE name = 'TURBINE SEU BURGER' AND family = '1' LIMIT 1), '1000', NULL, 8, 'HAMBURGUER 180G -', NULL);
