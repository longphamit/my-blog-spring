create database myblog;
use myblog;
create table Authen(
	id varchar(255) primary key,
    email varchar(255),
	password varchar(255),
	avatar varchar(255),
    phone varchar(255),
    github varchar(255),
    linkedln varchar(255),
	facebook varchar(255),
    gitlab varchar(255)
);
create table category(
	id varchar(255) primary key,
    name varchar(255)
);
create table blog(
	id varchar(255) primary key,
    title varchar(255),
    content text,
	author varchar(255),
    created_at datetime,
    category_id varchar(255),
    image_show varchar(255),
    foreign key (category_id) references category(id)
);
create table image(
	id varchar(255) primary key,
    blog_id varchar(255),
    path varchar(255),
    foreign key (blog_id) references blog(id)
);

create table memo(
	id varchar(255) primary key,
    created_at datetime,
    content text,
    image varchar(255),
    year int
);
create table contact(
	id varchar(255) primary key,
    email varchar(255),
    content text,
    created_at datetime
);
create table refreshToken(
	id varchar(255) not null,
    email varchar(255),
    expiration datetime,
    value varchar(255),
    primary key (id),
    authen_id varchar(255),
    foreign key (authen_id) references Authen(id)
)