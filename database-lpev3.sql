Create database lpev3;
Use lpev3;

Create table tb_marca
(
cod_marca int(6) not null primary key auto_increment,
nom_marca varchar(20) not null
);

Insert into tb_marca values(null, "Kia"), (null, "Lamborghini"), (null, "Ferrari"), (null, "Audi");

Create table tb_auto
(
cod_auto int(6) not null primary key auto_increment,
des_auto varchar(40) not null,
stock_auto int(3) not null,
pre_auto decimal(4,2) not null,
cod_marca int(6),

foreign key (cod_marca) references tb_marca(cod_marca)
);