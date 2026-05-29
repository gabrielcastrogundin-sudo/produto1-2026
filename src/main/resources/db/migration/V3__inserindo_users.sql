insert into tb_user (name, phone, email, password, created_at)
values (
    'Luis',
    '3455-2748',
    'luisfernandoanunes@gmail.com',
    '$2a$10$0q/uNhk55L0P4SQ.GiMvWugVzi/XzR/Ub4DjpTE4/HhStKo67xUfW',
    now()
);

insert into tb_user (name, phone, email, password, created_at)
values (
    'Claudio',
    '3425-1734',
    'claudio@gmail.com',
    '$2a$10$0q/uNhk55L0P4SQ.GiMvWugVzi/XzR/Ub4DjpTE4/HhStKo67xUfW',
    now()
);

insert into tb_user_perfil(id_user, id_perfil)
values (1, 1);

insert into tb_user_perfil(id_user, id_perfil)
values (2, 2);

insert into tb_user_perfil(id_user, id_perfil)
values (2, 3);