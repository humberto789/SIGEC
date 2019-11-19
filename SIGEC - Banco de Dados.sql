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
create table paciente(
	id int auto_increment not null,
	id_pessoa int not null,
	primary key(id),
    foreign key(id_pessoa) references pessoa(id) on delete restrict on update cascade
);
create table atestado(
	id int auto_increment not null,
	cid varchar(10) not null,
	dataEmissao date not null,
	dataVencimento date not null,
	id_medico int unique not null,
	id_paciente int unique not null,
	primary key(id),
	foreign key(id_medico) references medico(id) on delete restrict on update cascade,
	foreign key(id_paciente) references paciente(id) on delete restrict on update cascade
);

create table prontuario(
	id int auto_increment not null,
	peso double not null,
	altura double not null,
	alergia varchar(100),
	queixa varchar(200),
	temperatura double not null,
	id_medico int unique not null,
	id_paciente int unique not null,
	primary key(id),
	foreign key(id_medico) references medico(id) on delete restrict on update cascade,
	foreign key(id_paciente) references paciente(id) on delete restrict on update cascade
);


insert into pessoa(cpf, nome, dataNascimento, sexo) value("705.960.664-31", "Malévola", "1985-11-11", "feminino");
insert into usuario(login, senha, email, ativo, id_pessoa) values("705.960.664-31", "odeiohomens", "malévolamalvadinha@hotmail.com", true, (select id from pessoa where cpf = "705.960.664-31"));
