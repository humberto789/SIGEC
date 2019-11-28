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
	login varchar(100) not null,
    senha varchar(50) not null,
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
create table recepcionista(
	id int auto_increment not null,
	id_pessoa int not null,
	primary key(id),
    foreign key(id_pessoa) references pessoa(id) on delete restrict on update cascade
);
create table administrador(
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
	id_medico int not null,
	id_paciente int not null,
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

create table consulta(
	id int auto_increment not null,
	dataConsulta date not null,
	id_medico int not null,
	id_paciente int not null,
	primary key(id),
	foreign key(id_medico) references medico(id) on delete restrict on update cascade,
	foreign key(id_paciente) references paciente(id) on delete restrict on update cascade
);

create table fila (
	id int auto_increment not null primary key,
	senha int
);


INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("705.960.664-31", "Malévola", "1985-11-11", "feminino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("705.960.664-32", "José", "1990-11-11", "masculino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("705.960.664-33", "Maria", "1991-11-11", "feminino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("705.960.664-34", "George", "1999-11-11", "masculino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("111.111.111-11", "Humberto", "2000-11-11", "masculino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("222.222.222-22", "Hellen", "1998-11-11", "feminino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("333.333.333-33", "Isabel", "1997-11-11", "feminino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("444.444.444-44", "Julio", "1996-11-11", "masculino");
INSERT INTO pessoa(cpf, nome, dataNascimento, sexo) value("555.555.555-55", "Pierre", "1995-11-11", "masculino");

INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("705.960.664-31", "odeiohomens", "malévolamalvadinha@hotmail.com", true, (select id from pessoa where cpf = "705.960.664-31"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("705.960.664-33", "12345", "maria@gmail.com", true, (select id from pessoa where cpf = "705.960.664-33"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("123456", "12345", "jose@gmail.com", true, (select id from pessoa where cpf = "705.960.664-32"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("705.960.664-34", "odeiohomens2", "george.costa25.gc@gmail.com", true, (select id from pessoa where cpf = "705.960.664-34"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("111111", "12345", "humberto@gmail.com", true, (select id from pessoa where cpf = "111.111.111-11"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("222.222.222-22", "12345", "hellen@gmail.com", true, (select id from pessoa where cpf = "222.222.222-22"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("333.333.333-33", "12345", "isabel@gmail.com", true, (select id from pessoa where cpf = "333.333.333-33"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("444.444.444-44", "12345", "julio@gmail.com", true, (select id from pessoa where cpf = "444.444.444-44"));
INSERT INTO usuario(login, senha, email, ativo, id_pessoa) values("555.555.555-55", "12345", "pierre@gmail.com", true, (select id from pessoa where cpf = "555.555.555-55"));

INSERT INTO paciente(id_pessoa) value((select id from pessoa where cpf = "705.960.664-31"));
INSERT INTO paciente(id_pessoa) value((select id from pessoa where cpf = "705.960.664-33"));

INSERT INTO medico(crm, id_pessoa) value("123456", (select id from pessoa where cpf = "705.960.664-32"));
INSERT INTO medico(crm, id_pessoa) value("111111", (select id from pessoa where cpf = "111.111.111-11"));

INSERT INTO recepcionista(id_pessoa) value((select id from pessoa where cpf = "222.222.222-22"));
INSERT INTO recepcionista(id_pessoa) value((select id from pessoa where cpf = "333.333.333-33"));

INSERT INTO administrador(id_pessoa) value((select id from pessoa where cpf = "444.444.444-44"));
INSERT INTO administrador(id_pessoa) value((select id from pessoa where cpf = "555.555.555-55"));

INSERT INTO atestado(cid, dataEmissao, dataVencimento, id_medico, id_paciente) VALUES("f41", "2019-10-10", "2019-10-12", (select medico.id from medico inner join pessoa on medico.id_pessoa = pessoa.id where cpf = "705.960.664-32"), (select paciente.id from paciente inner join pessoa on paciente.id_pessoa = pessoa.id where cpf = "705.960.664-31"));
INSERT INTO atestado(cid, dataEmissao, dataVencimento, id_medico, id_paciente) VALUES("f45", "2019-10-13", "2019-10-16", (select medico.id from medico inner join pessoa on medico.id_pessoa = pessoa.id where cpf = "705.960.664-32"), (select paciente.id from paciente inner join pessoa on paciente.id_pessoa = pessoa.id where cpf = "705.960.664-33"));



SELECT * FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE usuario.login = "705.960.664-31";