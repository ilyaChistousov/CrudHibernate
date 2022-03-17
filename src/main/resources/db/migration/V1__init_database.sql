create table if not exists writers(
    id bigserial  primary key,
    first_name varchar(256),
    last_name varchar(256)
);

create table if not exists posts(
    id bigserial  primary key,
    writer_id bigint references writers(id),
    content varchar(256),
    created timestamp,
    updated timestamp,
    post_status varchar(20)
);

CREATE TABLE labels(
    id   bigserial primary key,
    name VARCHAR(255)
);

create table if not exists posts_labels(
    post_id bigint references posts(id),
    label_id bigint references labels(id)
);