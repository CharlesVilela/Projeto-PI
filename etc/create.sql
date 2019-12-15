create table USUARIO (
	ID serial,
	NOME varchar(50) not null,
	USERNAME varchar(20) not null unique,
	PASSWORD varchar(10) not null,
	VALIDADE_PASSWORD date,
	EMAIL varchar(30) not null unique,
	ATIVO char(1),
	AVATAR varchar(100) default 'default',
	WEBSITE varchar(100),
	constraint USUARIO_PK primary key (ID)
);

create table AUTH_TOKEN(
	UUID varchar(36),
	VALIDADE date,
	ID_USUARIO integer not null,
	constraint AUTH_TOKEN_PK primary key(UUID)
);