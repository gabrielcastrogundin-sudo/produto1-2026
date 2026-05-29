create table tb_category (
    id bigint not null auto_increment,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    name varchar(255),
    primary key (id)
);

create table tb_perfil (
    id bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table tb_product (
    id bigint not null auto_increment,
    price double,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    description varchar(255),
    img_url varchar(255),
    name varchar(255),
    primary key (id)
);

create table tb_product_category (
    id_category bigint not null,
    id_product bigint not null,
    primary key (id_category, id_product)
);

create table tb_user (
    id bigint not null auto_increment,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    phone varchar(255),
    primary key (id)
);

create table tb_user_perfil (
    id_perfil bigint not null,
    id_user bigint not null,
    primary key (id_perfil, id_user)
);

alter table tb_product_category
add constraint FKmbt47wj35n9he7pofy5mgyh75
foreign key (id_category)
references tb_category(id);

alter table tb_product_category
add constraint FK6kdysltdkw0519op8r02iflt
foreign key (id_product)
references tb_product(id);

alter table tb_user_perfil
add constraint FKml01gsx6pqmda1s0ta9n146oa
foreign key (id_perfil)
references tb_perfil(id);

alter table tb_user_perfil
add constraint FKex75knqtcyb5rvboabi57dx2o
foreign key (id_user)
references tb_user(id);

insert into tb_category (name, created_at)
values ('Livros', now());

insert into tb_category (name, created_at)
values ('Canecas', now());

insert into tb_category (name, created_at)
values ('Lapis', now());

insert into tb_category (name, created_at)
values ('Borrachas', now());

insert into tb_category (name, created_at)
values ('Isqueiro', now());

insert into tb_product (name, description, price, img_url, created_at)
values ('ESTRELA01', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', now());

insert into tb_product (name, description, price, img_url, created_at)
values ('ESTRELA02', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', now());

insert into tb_product (name, description, price, img_url, created_at)
values ('ESTRELA03', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', now());

insert into tb_product (name, description, price, img_url, created_at)
values ('ESTRELA04', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', now());

insert into tb_product (name, description, price, img_url, created_at)
values ('ESTRELA05', 'BRINQUEDO ELETRICO', 30.40, 'LINKDAIMAGEM', now());

insert into tb_product_category(id_product, id_category)
values (1, 1);

insert into tb_product_category(id_product, id_category)
values (1, 2);

insert into tb_product_category(id_product, id_category)
values (2, 1);

insert into tb_product_category(id_product, id_category)
values (2, 3);

insert into tb_product_category(id_product, id_category)
values (3, 1);

insert into tb_product_category(id_product, id_category)
values (3, 3);

insert into tb_perfil (name)
values ('ROLE_ADMINISTRATOR');

insert into tb_perfil (name)
values ('ROLE_SALESMAN');

insert into tb_perfil (name)
values ('ROLE_CLIENT');