-- INSERT Produtos
INSERT INTO Product (category_id, name, description, price, complementary, url_image) VALUES
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), 'FOME TIPO-G', 'Pão Australiano, 2 hambúrgueres de 180g, queijo, salada e molho mb', 0, FALSE, 'https://i.imgur.com/SSanzqx.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), 'MAGIC BURGER', 'Pão de hambúrguer com gergelim, hambúrguer 180g, queijo, salada, picles e cebola caramelizada', 0, FALSE, 'https://i.imgur.com/m53Cky3.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), 'MB O BURGER', 'Pão de hambúrguer com gergelim, hambúrguer 180g, queijo, bacon, salada e molho mb', 0, FALSE, 'https://i.imgur.com/hMCyF8A.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), 'MB O JUNIOR', 'Pão brioche, hambúrguer 90g, queijo, salada e molho mb', 0, FALSE, 'https://i.imgur.com/3U7HzPL.png'),
((SELECT id_category FROM Category WHERE label = 'BURGER''S'), 'SMASHNOLO', 'Pão brioche, 2 hambúrgueres de 90g, queijo, bacon e molho mb', 0, FALSE, 'https://i.imgur.com/9mGQjdL.png'),
((SELECT id_category FROM Category WHERE label = 'COMBOS MB'), 'COMBO CASAL', '1 Mb o Burger + 1 Magic Burger + 1 Batata Mb + Refri 1,5l', 59.97, FALSE, 'https://i.imgur.com/2bMmv1I.png'),
((SELECT id_category FROM Category WHERE label = 'COMBOS MB'), 'COMBO JUNIOR', '1 Mb o Junior + 1 Batata Mb', 23.99, FALSE, 'https://i.imgur.com/Qs01bKl.png'),
((SELECT id_category FROM Category WHERE label = 'COMBOS MB'), 'COMBO MAGICO', '1 Magic Burger + 1 Batata Mb + Refri lata 310ml', 34.9, FALSE, 'https://i.imgur.com/j2ZxVf3.png'),
((SELECT id_category FROM Category WHERE label = 'FRITAS MB'), 'MB - CHICKEN', 'Contém 5 unidades de Nuggets de frango.', 9.99, FALSE, 'https://i.imgur.com/D0FQblq.png'),
((SELECT id_category FROM Category WHERE label = 'FRITAS MB'), 'MB - FRITAS', 'A melhor batata palito frita', 7.99, FALSE, 'https://i.imgur.com/yGtiwDP.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), 'COCA COLA 1,5', 'Não basta ser coca, tem que estar gelada!', 10, FALSE, 'https://i.imgur.com/5EAAyev.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), 'COCA COLA 310ML', 'Não basta ser coca, tem que estar gelada!', 4.99, FALSE, 'https://i.imgur.com/EvDhaMv.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), 'FANTA 1,5', 'Com sabor irresistível e único, de laranja.', 10, FALSE, 'https://i.imgur.com/AtSAork.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), 'FANTA 310ML', 'Com sabor irresistível e único, de laranja.', 4.99, FALSE, 'https://i.imgur.com/SKgONaU.png'),
((SELECT id_category FROM Category WHERE label = 'BEBIDAS'), 'GUARANÁ ANTARCTICA 350ML', 'Brasileiro com sabor único e natural', 4.99, FALSE, 'https://i.imgur.com/OofLKtA.png'),
((SELECT id_category FROM Category WHERE label = 'SOBREMESAS'), 'SOBREMESA MB', '', 12, FALSE, 'https://i.ytimg.com/vi/9_9YCWc8OEI/maxresdefault.jpg');

-- INSERT Sizes
INSERT INTO Size (product_id, name, max_combination) VALUES
((SELECT id_product FROM Product WHERE name = 'FOME TIPO-G'), 'BURGER', 1),
((SELECT id_product FROM Product WHERE name = 'MAGIC BURGER'), 'BURGER', 1),
((SELECT id_product FROM Product WHERE name = 'MB O BURGER'), 'BURGER', 1),
((SELECT id_product FROM Product WHERE name = 'MB O JUNIOR'), 'BURGER', 1),
((SELECT id_product FROM Product WHERE name = 'SMASHNOLO'), 'BURGER', 1);

-- INSERT Complementary
INSERT INTO Complementary (size_id, name, price, quantity) VALUES
((SELECT id_size FROM Size WHERE name = 'BURGER' AND product_id = (SELECT id_product FROM Product WHERE name = 'FOME TIPO-G')), 'BACON -', 5, NULL),
((SELECT id_size FROM Size WHERE name = 'BURGER' AND product_id = (SELECT id_product FROM Product WHERE name = 'FOME TIPO-G')), 'CHICKEN -', 3, NULL),
((SELECT id_size FROM Size WHERE name = 'BURGER' AND product_id = (SELECT id_product FROM Product WHERE name = 'FOME TIPO-G')), 'CEBOLA CARAMELIZADA -', 0, NULL),
((SELECT id_size FROM Size WHERE name = 'BURGER' AND product_id = (SELECT id_product FROM Product WHERE name = 'FOME TIPO-G')), 'CHEDDAR -', 3, NULL),
((SELECT id_size FROM Size WHERE name = 'BURGER' AND product_id = (SELECT id_product FROM Product WHERE name = 'FOME TIPO-G')), 'SALADA', 0, NULL);