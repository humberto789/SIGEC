drop database if exists SIGEC;
create database if not exists SIGEC;

use SIGEC;

create table pessoa (
	cpf char(14) unique not null,
    nome varchar(150) not null,
	dataNascimento date not null,
    sexo varchar(15) not null,
    id int auto_increment not null,
    primary key(id)
);
create table endereco(
	rua varchar(50) not null,
    numero varchar(20) not null,
    cep char(9) not null,
    cidade varchar(50) not null,
    estado varchar(50) not null,
    bairro varchar(50) not null,
    id int auto_increment not null,
    id_pessoa int not null,
    primary key(id),
    foreign key(id_pessoa) references pessoa(id) on delete restrict on update cascade
);
create table telefone(
	numero int not null,
    ddd int not null,
    id int auto_increment not null,
    id_pessoa int not null,
    primary key(id),
    foreign key(id_pessoa) references pessoa(id) on delete restrict on update cascade
);
create table usuario(
	login varchar(100) unique not null,
    senha varchar(50) unique not null,
    email varchar(100),
    ativo boolean not null,
    id int auto_increment not null,
    id_pessoa int not null,
    primary key(id),
    foreign key(id_pessoa) references pessoa(id) on delete restrict on update cascade
);
create table medico(
	crm varchar(50) unique not null,
    id int auto_increment not null,
    id_pessoa int not null,
    primary key(id),
    foreign key(id_pessoa) references pessoa(id) on delete restrict on update cascade
);
