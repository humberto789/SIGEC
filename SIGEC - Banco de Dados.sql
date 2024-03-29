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
    horario_cadastro datetime not null,
	id_medico int not null,
	id_paciente int not null,
	primary key(id),
	foreign key(id_medico) references medico(id) on delete restrict on update cascade,
	foreign key(id_paciente) references paciente(id) on delete restrict on update cascade
);

create table consulta(
	id int auto_increment not null,
	horario datetime not null,
    realizada boolean not null,
	id_medico int not null,
	id_paciente int not null,
    constraint indentificador_consulta unique(id_medico, id_paciente, horario),
	primary key(id),
	foreign key(id_medico) references medico(id) on delete restrict on update cascade,
	foreign key(id_paciente) references paciente(id) on delete restrict on update cascade
);

create table fila (
	id int auto_increment not null primary key,
	senha varchar(25) not null unique,
	chamado boolean,
    id_consulta int,
    foreign key(id_consulta) references consulta(id) on delete restrict on update cascade
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

INSERT INTO atestado(cid, dataEmissao, dataVencimento, id_medico, id_paciente) VALUES("F41", "2019-10-10", "2019-10-12", (select medico.id from medico inner join pessoa on medico.id_pessoa = pessoa.id where cpf = "705.960.664-32"), (select paciente.id from paciente inner join pessoa on paciente.id_pessoa = pessoa.id where cpf = "705.960.664-31"));
INSERT INTO atestado(cid, dataEmissao, dataVencimento, id_medico, id_paciente) VALUES("F45", "2019-10-13", "2019-10-16", (select medico.id from medico inner join pessoa on medico.id_pessoa = pessoa.id where cpf = "705.960.664-32"), (select paciente.id from paciente inner join pessoa on paciente.id_pessoa = pessoa.id where cpf = "705.960.664-33"));
INSERT INTO atestado(cid, dataEmissao, dataVencimento, id_medico, id_paciente) VALUES("F44", "2019-03-05", "2019-05-05", (select medico.id from medico inner join pessoa on medico.id_pessoa = pessoa.id where cpf = "111.111.111-11"), (select paciente.id from paciente inner join pessoa on paciente.id_pessoa = pessoa.id where cpf = "705.960.664-31"));

INSERT INTO prontuario(peso, altura, alergia, queixa, temperatura, id_medico, id_paciente, horario_cadastro) VALUES(70.5, 1.80, "poeira", "dor de cabeça", 37.0, (SELECT medico.id FROM medico WHERE medico.crm = "123456"), (SELECT paciente.id FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE pessoa.cpf="705.960.664-31"), now());

INSERT INTO consulta(id_medico, id_paciente, horario, realizada) VALUES ((SELECT id FROM medico WHERE crm="123456"), (SELECT paciente.id FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id WHERE pessoa.cpf="705.960.664-31"), "2019-10-12 01:00:00", 0);
INSERT INTO consulta(id_medico, id_paciente, horario, realizada) VALUES (2, 1, "2019-12-10 18:00:00", 0);

INSERT INTO fila(senha, id_consulta, chamado) VALUES ("123456", 1, true);
INSERT INTO fila(senha, id_consulta, chamado) VALUES ("789123", 1, false);
INSERT INTO fila(senha, id_consulta, chamado) VALUES ("444444", 1, false);

SELECT * FROM paciente INNER JOIN pessoa ON paciente.id_pessoa = pessoa.id INNER JOIN usuario ON usuario.id_pessoa = pessoa.id WHERE usuario.login = "705.960.664-31";
SELECT * FROM consulta INNER JOIN paciente ON consulta.id_paciente = paciente.id INNER JOIN medico ON consulta.id_medico = medico.id INNER JOIN pessoa ON pessoa.id = paciente.id_pessoa WHERE consulta.horario="2019-10-12 01:00:00" AND medico.crm="123456" AND pessoa.cpf="705.960.664-31";
SELECT prontuario.*, medico.crm, pessoaMedico.nome as medico, pessoaPaciente.nome as paciente FROM prontuario INNER JOIN medico ON prontuario.id_medico = medico.id INNER JOIN pessoa pessoaMedico ON medico.id_pessoa = pessoaMedico.id INNER JOIN paciente ON prontuario.id_paciente = paciente.id INNER JOIN pessoa pessoaPaciente ON paciente.id_pessoa = pessoaPaciente.id WHERE pessoaPaciente.cpf = "705.960.664-31";
SELECT consulta.*, medico.crm, pessoaMedico.nome , pessoaPaciente.nome FROM consulta INNER JOIN medico ON consulta.id_medico = medico.id INNER JOIN pessoa pessoaMedico ON medico.id_pessoa = pessoaMedico.id INNER JOIN paciente ON consulta.id_paciente = paciente.id INNER JOIN pessoa pessoaPaciente ON paciente.id_pessoa = pessoaPaciente.id WHERE pessoaPaciente.cpf = "705.960.664-31";

SELECT senha FROM fila as filas WHERE chamado = false ORDER BY id LIMIT 1;

SELECT * FROM endereco;
SELECT * FROM telefone;
SELECT * FROM pessoa;
SELECT * FROM paciente;
SELECT * FROM usuario;
SELECT * FROM consulta;
SELECT * FROM recepcionista;
SELECT * FROM administrador;
SELECT * FROM prontuario;
SELECT * FROM atestado;
SELECT * FROM fila;