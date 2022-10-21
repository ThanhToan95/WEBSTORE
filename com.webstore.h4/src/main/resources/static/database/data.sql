create database h4store;
use h4store;
create table accounts(
username varchar(20) not null primary key,
password varchar(20),
fullname nvarchar(50),
email varchar(50),
phone int
);
create table roles(
id varchar(10) not null primary key,
name varchar(20)
);
create table authority(
id bigint not null primary key,
role_id varchar(10),FOREIGN KEY (role_id) REFERENCES roles(id),
username varchar(20),
 FOREIGN KEY (username) REFERENCES accounts(username)
);
create table categories(
id varchar(10) not null primary key,
name varchar(50),
status varchar(20)
);
create table products(
id varchar(10) not null primary key,
name varchar(50),
image varchar(50),
availiable bit,
create_date datetime,
discount int,
description nvarchar(255),
category_id varchar(10),FOREIGN KEY (category_id) REFERENCES categories(id)
);
create table orders(
id bigint not null primary key,
create_date datetime,
address nvarchar(150),
phone int,
email varchar(50),
notes nvarchar(255),
username varchar(20),FOREIGN KEY (username) REFERENCES accounts(username)
);
create table order_details(
id bigint not null primary key,
price float,
quantity int,
cod bit,
bank_transfer bit,
status bit,
username varchar(20),FOREIGN KEY (username) REFERENCES accounts(username),
product_id varchar(10),FOREIGN KEY (product_id) REFERENCES products(id),
order_id bigint,FOREIGN KEY (order_id) REFERENCES orders(id)
);
create table favorites(
id bigint not null primary key,
product_name nvarchar(50),
username varchar(20),FOREIGN KEY (username) REFERENCES accounts(username),
product_id varchar(10),FOREIGN KEY (product_id) REFERENCES products(id)
);
create table warranty(
id bigint not null primary key,
create_date datetime,
product_name nvarchar(50),
description nvarchar(255),
status bit,
username varchar(20),FOREIGN KEY (username) REFERENCES accounts(username),
product_id varchar(10),FOREIGN KEY (product_id) REFERENCES products(id)
);
