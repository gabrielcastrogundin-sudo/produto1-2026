insert into tb_category (nome,created_at) values ('Livros', NOW());
insert into tb_category (nome,created_at) values ('Canecas', NOW());
insert into tb_category (nome,created_at) values ('Lapis', NOW());
insert into tb_category (nome,created_at) values ('Borrachas', NOW());
insert into tb_category (nome,created_at) values ('Isqueiro', NOW());



insert into tb_product (name, description, price, img_url,created_at) values ('ESTRELA01', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', NOW());
insert into tb_product (name, description, price, img_url,created_at) values ('ESTRELA02', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', NOW());
insert into tb_product (name, description, price, img_url,created_at) values ('ESTRELA03', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', NOW());
insert into tb_product (name, description, price, img_url,created_at) values ('ESTRELA04', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', NOW());
insert into tb_product (name, description, price, img_url,created_at) values ('ESTRELA05', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', NOW());

insert into tb_product_category(tb_product, tb_category) values (1, 1);
insert into tb_product_category(id_product, id_category) values (1, 2);
insert into tb_product_category(id_product, id_category) values (2, 1);
insert into tb_product_category(id_product, id_category) values (2, 3);
insert into tb_product_category(id_product, id_category) values (3, 1);
insert into tb_product_category(id_product, id_category) values (3, 3);
insert into tb_product_category(id_product, id_category) values (3, 3);

insert into tb_perfil (name) values ('ROLE_ADMINISTRATOR');
insert into tb_perfil (name) values ('ROLE_SALESMAN');
insert into tb_perfil (name) values ('ROLE_CLIENT');

insert into tb_user (name, phone, email, password, created_at) values ('Luis', '3455-2748', 'luisfernandoanunes@gmail.com', '123456', now());
insert into tb_user (name, phone, email, password, created_at) values ('Claudio', '3425-1734', 'claudio@gmail.com', '123456', now());

insert into tb_user_perfil(id_user, id_perfil) values (1, 1);
insert into tb_user_perfil(id_user, id_perfil) values (2, 2);
insert into tb_user_perfil(id_user, id_perfil) values (2, 3);