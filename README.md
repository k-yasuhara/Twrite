## データベース
- データベースをセットアップする場合は、以下のSQLを実行する。
``` sql
create database twrite_db
CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

use twrite_db;

create table admins (
	id int primary key auto_increment,
    login_id varchar(30) unique not null,
    login_pass varchar(255) not null,
    name varchar(30) not null
);

create table records (
	id int primary key auto_increment,
    register_id varchar(30) not null,
    registered_at datetime not null,
    updated_at timestamp not null,
    start_at datetime not null,
    end_at datetime not null,
    patient_pattern int not null,
    consolution text,
    response text,
    editor int,
    staff_id int
);

create table staff (
	id int primary key auto_increment,
    name varchar(30) not null
);

create table patient (
	id int primary key auto_increment,
    attribute varchar(30) not null
);

create table symptoms_pattern (
	id int primary key auto_increment,
    symptoms_name varchar(30)
);

create table symptoms (
	id int primary key auto_increment,
    records_id int not null,
    symptoms_id int not null
);

-- pass 「zxcvbnm」
insert into admins values
(1,"op01","$2a$08$W.bQMV6aQkGdo7xqR5GVneodb4z9i5U6TPxqHf07FHQYmuOnC56t2","救急相談OP01"),
(2,"sv01","$2a$08$VPn8yd5/A52w9lN7HGJZZesdOLrTRlQZmIbiT5rXMQSx.pUYRLfuK","救急相談SV01");

insert into records values
(1,1,"2024-01-01 01:01:00","2024-01-01 01:10:00","2024-01-01 01:01:00","2024-01-01 01:10:00",1,"hoge","hoge",0,1);

insert into staff values
(1,"渡邊"),
(2,"高比良"),
(3,"松井");

insert into patient values
(1,"本人"),(2,"娘"),
(3,"息子"),(4,"父"),
(5,"母"),(6,"親戚"),(7,"その他");

INSERT INTO symptoms_pattern VALUES
(1, '熱発'),
(2, '咳嗽'),
(3, '鼻汁・鼻閉'),
(4, '咽頭痛'),
(5, '咽頭違和感'),
(6, '腹痛'),
(7, '下痢'),
(8, '嘔気・嘔吐'),
(9, '熱傷'),
(10, '打撲');

INSERT INTO symptoms VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 5),
(4, 3, 1),
(5, 4, 2);

```

