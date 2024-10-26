create table users(
    id bigserial primary key,
    username varchar(255)
);

create table products
(
    id bigserial primary key,
    user_id bigint constraint idx_user_id references users,
    account      varchar(20),
    balance      numeric(15, 2),
    type_account varchar(10)
);

alter sequence users_id_seq owner to postgres;
alter sequence users_id_seq owned by users.id;
grant select, update, usage on sequence users_id_seq to tst_user;

alter sequence products_id_seq owner to postgres;
alter sequence products_id_seq owned by products.id;
grant select, update, usage on sequence products_id_seq to tst_user;

alter table users owner to postgres;
grant delete, insert, select, update on users to tst_user;

alter table products owner to postgres;
grant delete, insert, select, update on products to tst_user;



