CREATE TABLE paciente (
	id bigserial PRIMARY KEY,
	nome varchar(50),
	sobrenome varchar(100),
	cpf varchar(15),
	email varchar(100)
);