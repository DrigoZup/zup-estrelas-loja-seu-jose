create database loja_de_pecas;
use loja_de_pecas;

create table pecas (
codigo_de_barras bigint primary key,
nome varchar(45) not null,
modelo_do_carro varchar(50) not null ,
fabricante varchar (50) not null,
preco_de_custo decimal (8,2) not null,
preco_de_venda decimal (8,2) not null,
quantidade_em_estoque int not null,
categoria  varchar (50) not null
);

insert into pecas
values
(111, 'Correia Dentada', 'Gol', 'Bosch', 48.34, 62.90, 30, 'Motor'),
(222, 'Multimidea', 'Onix', 'Multilaser', 1178.34, 1559.90, 12, 'Som'), 
(333, 'Para-Choque', 'Fusca', 'Cupim de Aço', 175.53, 229.90, 13, 'Funilaria'),
(444, 'Kit Turbo', 'Hillux SW4', 'Original', 2335.93, 3039.90, 18, 'Performace'),
(555, 'Bateria', 'Ka', 'Moura', 177.94, 229.90, 70, 'Motor');

select* from pecas;
