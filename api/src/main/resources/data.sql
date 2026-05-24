-- Categorias
INSERT INTO category (name, created_at) VALUES ('BURGERS', NOW());
INSERT INTO category (name, created_at) VALUES ('COMBOS MB', NOW());
INSERT INTO category (name, created_at) VALUES ('FRITAS MB', NOW());
INSERT INTO category (name, created_at) VALUES ('BEBIDAS', NOW());
INSERT INTO category (name, created_at) VALUES ('SOBREMESAS', NOW());

-- Categoria complementar
INSERT INTO category_complementary (name, type, min, max, max_free, created_at) VALUES ('TURBINE SEU BURGER', 'ADD', 0, 0, 0, NOW());

-- Complementares (adicionais)
INSERT INTO additional (category_complementary_id, name, price, created_at) VALUES (1, 'HAMBURGUER 180G', 8.00, NOW());
INSERT INTO additional (category_complementary_id, name, price, created_at) VALUES (1, 'CREAM CHEESE', 3.00, NOW());
INSERT INTO additional (category_complementary_id, name, price, created_at) VALUES (1, 'BACON', 5.00, NOW());
INSERT INTO additional (category_complementary_id, name, price, created_at) VALUES (1, 'CEBOLA CARAMELIZADA', 3.00, NOW());

-- Produto (burgers)
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (1, 'MAGIC BURGER', 'Pão de hambúrguer com gergelim, hambúrguer 180g, queijo, salada, picles e cebola caramelizada', 0, 'https://i.imgur.com/m53Cky3.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (1, 'MB O BURGER', 'Pão de hambúrguer com gergelim, hambúrguer 180g, queijo, bacon, salada e molho mb', 0, 'https://i.imgur.com/hMCyF8A.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (1, 'FOME TIPO-G', 'Pão Australiano, 2 hambúrgueres de 180g, queijo, salada e molho mb', 0, 'https://i.imgur.com/SSanzqx.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (1, 'SMASHNOLO', 'Pão brioche, 2 hambúrgueres de 90g, queijo, bacon e molho mb', 0, 'https://i.imgur.com/9mGQjdL.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (1, 'MB O JUNIOR', 'Pão brioche, hambúrguer 90g, queijo, salada e molho mb', 0, 'https://i.imgur.com/3U7HzPL.png', NOW());
    
-- Produto (combos)
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (2, 'COMBO MAGICO', '1 Magic Burger + 1 Batata Mb + Refri lata 310ml', 34.90, 'https://i.imgur.com/j2ZxVf3.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (2, 'COMBO JUNIOR', '1 Mb o Junior + 1 Batata Mb', 23.99, 'https://i.imgur.com/Qs01bKl.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (2, 'COMBO CASAL', '1 Mb o Burger + 1 Magic Burger + 1 Batata Mb + Refri 1,5l', 59.97, 'https://i.imgur.com/2bMmv1I.png', NOW());

-- Produto (fritas)
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (3, 'MB - FRITAS', 'A melhor batata palito frita', 7.99, 'https://i.imgur.com/yGtiwDP.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (3, 'MB - CHICKEN', 'Contém 5 unidades de Nuggets de frango.', 9.99, 'https://i.imgur.com/D0FQblq.png', NOW());

-- Produto (bebidas)
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (4, 'COCA COLA 310ML', 'Não basta ser coca, tem que estar gelada!', 4.99, 'https://i.imgur.com/EvDhaMv.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (4, 'FANTA 310ML', 'Com sabor irresistível e único, de laranja.', 4.99, 'https://i.imgur.com/SKgONaU.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (4, 'GUARANÁ ANTARCTICA 350ML', 'Brasileiro com sabor único e natural', 4.99, 'https://i.imgur.com/OofLKtA.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (4, 'COCA COLA 1,5', 'Não basta ser coca, tem que estar gelada!', 10.00, 'https://i.imgur.com/5EAAyev.png', NOW());
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (4, 'FANTA 1,5', 'Com sabor irresistível e único, de laranja.', 10.00, 'https://i.imgur.com/AtSAork.png', NOW());

-- Produto (sobremesas)
INSERT INTO product (category_id, name, description, price, url_image, created_at) VALUES (5, 'SOBREMESA MB', '', 12.00, 'https://i.ytimg.com/vi/9_9YCWc8OEI/maxresdefault.jpg', NOW());

-- Tamanhos (burgers)
INSERT INTO product_size (product_id, name, price, max_combination, created_at) VALUES (1, 'BURGER', 24.99, 1, NOW());
INSERT INTO product_size (product_id, name, price, max_combination, created_at) VALUES (2, 'BURGER', 24.99, 1, NOW());
INSERT INTO product_size (product_id, name, price, max_combination, created_at) VALUES (3, 'BURGER', 34.99, 1, NOW());
INSERT INTO product_size (product_id, name, price, max_combination, created_at) VALUES (4, 'BURGER', 28.99, 1, NOW());
INSERT INTO product_size (product_id, name, price, max_combination, created_at) VALUES (5, 'BURGER', 19.90, 1, NOW());

-- Vínculo Tamanhos <-> categoria complementar
INSERT INTO product_size_category_complementary (product_size_id, category_complementary_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 1);

-- Estabelecimento
INSERT INTO establishment (name, email, phone, cnpj, created_at) VALUES ('Burger House', 'contato@burgerhouse.com', '65999990001', '12345678000101', NOW());
INSERT INTO establishment (name, email, phone, cnpj, created_at) VALUES ('Pizza Prime', 'suporte@pizzaprime.com', '65999990002', '12345678000102', NOW());
INSERT INTO establishment (name, email, phone, cnpj, created_at) VALUES ('Sabor Grill', 'atendimento@saborgrill.com', '65999990003', '12345678000103', NOW());