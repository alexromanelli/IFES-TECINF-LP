-- Table: aluno

-- DROP TABLE aluno;

CREATE TABLE aluno
(
  matricula integer NOT NULL,
  nome character varying(50) NOT NULL,
  datanascimento character(10) NOT NULL,
  sexo character(1) NOT NULL,
  email character varying(80) NOT NULL,
  CONSTRAINT aluno_pkey PRIMARY KEY (matricula)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aluno
  OWNER TO postgres;

-- Table: curso

-- DROP TABLE curso;

CREATE TABLE curso
(
  codcurso serial NOT NULL,
  nome character varying(30) NOT NULL,
  cargahoraria integer NOT NULL,
  coordenador character varying(30) NOT NULL,
  CONSTRAINT curso_pkey PRIMARY KEY (codcurso)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE curso
  OWNER TO postgres;

-- Table: disciplina

-- DROP TABLE disciplina;

CREATE TABLE disciplina
(
  coddisciplina serial NOT NULL,
  nome character varying(50) NOT NULL,
  cargahoraria integer NOT NULL,
  ementa text,
  codcurso integer NOT NULL,
  CONSTRAINT disciplina_pkey PRIMARY KEY (coddisciplina),
  CONSTRAINT fk_disciplina_curso FOREIGN KEY (codcurso)
      REFERENCES curso (codcurso) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE disciplina
  OWNER TO postgres;
