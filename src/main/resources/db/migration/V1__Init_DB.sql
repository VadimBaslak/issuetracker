create sequence hibernate_sequence start 1 increment 1;

create table comment (
    id int8 not null,
    date_comment timestamp,
    status varchar(255),
    text_comment varchar(2048) not null,
    user_id int8,
    issue_id int8,
    primary key (id)
);

create table issue (
    id int8 not null,
    description varchar(2048) not null,
    filename varchar(255),
    issue_name varchar(255) not null,
    start_date timestamp,
    status varchar(255),
    user_id int8,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    active boolean not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table if exists comment
    add constraint comment_user_fk
    foreign key (user_id) references usr;

alter table if exists comment
    add constraint comment_issue_fk
    foreign key (issue_id) references issue;

alter table if exists issue
    add constraint issue_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;