-- drop table if exists voluntario cascade;
-- drop table if exists ong cascade;
-- drop table if exists projeto cascade;

-- Criação de tabelas
create table voluntario(
codigo serial primary key,
email varchar(150) not null unique,
senha varchar(150) not null,
cpf varchar(11) not null unique);

create table ong(
codigo serial primary key,
nomeOng varchar(150) not null unique,
cnpj varchar(14) not null unique,
emailOng varchar(150) not null unique,
senhaOng varchar(150) not null,
telefone varchar(20) not null unique,
enderecoOng varchar(100) not null);

create table projeto(
codigo serial primary key,
nomeProjeto varchar(150) not null,
ong varchar(150) not null,
dataProjeto date not null,
horario varchar(20) not null,
endereco varchar(150) not null,
codong int not null unique,
constraint c01 foreign key(codigo) references ong(codigo) on update cascade);