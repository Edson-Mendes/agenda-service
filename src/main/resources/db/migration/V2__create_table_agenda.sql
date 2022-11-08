CREATE TABLE agenda (
	id bigserial PRIMARY KEY,
	descricao varchar(255),
	data_hora timestamp,
	data_criacao timestamp,
	paciente_id int8,
	CONSTRAINT fk_agenda_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);