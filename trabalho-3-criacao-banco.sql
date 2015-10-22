
-- Cadastros:
----------

-- Torneio

create table torneio (
    id_torneio serial primary key,
    nome varchar(100) not null,
    abrangencia char(1) not null, -- i: internacional / n: nacional / r: regional / e: estadual
    ano_inicio int not null,
    ano_termino int not null
);

-- Time

create table time_futebol (
    id_time serial primary key,
    nome varchar(100) not null,
    nome_abreviado varchar(10) not null,
    sede_pais varchar(20) not null,
    sede_estado char(2) not null,
    sede_cidade varchar(50) not null
);

-- Técnico

create table tecnico (
    id_tecnico serial primary key,
    nome varchar(60) not null,
    data_nascimento date not null,
    sexo char(1) not null,
    nacionalidade varchar(20) not null,
    id_time integer,
    
    foreign key (id_time) references time_futebol(id_time)
);

-- Jogador

create table posicao_jogador (
    id_posicao serial primary key,
    nome varchar(20) not null
);

create table jogador (
    id_jogador serial primary key,
    nome varchar(60) not null,
    data_nascimento date not null,
    sexo char(1) not null,
    nacionalidade varchar(20) not null,
    posicao_preferencial integer not null,
    id_time integer,
    
    foreign key (id_time) references time_futebol(id_time),
    foreign key (posicao_preferencial) references posicao_jogador(id_posicao)
);

-- Árbitro

create table organizacao_arbitragem (
    id_organizacao_arbitragem serial primary key,
    nome varchar(50) not null
);

create table arbitro (
    id_arbitro serial primary key,
    nome varchar(60) not null,
    data_nascimento date not null,
    sexo char(1) not null,
    nacionalidade varchar(20) not null,
    organizacao_arbitragem integer not null,
    
    foreign key (organizacao_arbitragem) references organizacao_arbitragem(id_organizacao_arbitragem)
);

-- Partida

create table estadio (
    id_estadio serial primary key,
    nome varchar(100) not null,
    apelido varchar(20) not null,
    capacidade integer not null,
    pais varchar(20) not null,
    estado char(2),
    cidade varchar(20) not null
);

create table partida (
    id_partida serial primary key,
    data_hora timestamp not null,
    time_casa integer not null,
    time_visitante integer not null,
    estadio integer not null,
    arbitro_principal integer not null,
    auxiliar_1 integer not null,
    auxiliar_2 integer not null,
    quarto_arbitro integer not null,
    
    foreign key (time_casa) references time_futebol(id_time),
    foreign key (time_visitante) references time_futebol(id_time),
    foreign key (estadio) references estadio(id_estadio),
    foreign key (arbitro_principal) references arbitro(id_arbitro),
    foreign key (auxiliar_1) references arbitro(id_arbitro),
    foreign key (auxiliar_2) references arbitro(id_arbitro),
    foreign key (quarto_arbitro) references arbitro(id_arbitro)
);

-- Gol

create table gol (
    id_gol serial primary key,
    partida integer not null,
    jogador integer not null,
    time_jogador integer not null,
    tipo char(1) not null default 'p', -- p: pró / c: contra
    
    foreign key (partida) references partida(id_partida),
    foreign key (jogador) references jogador(id_jogador),
    foreign key (time_jogador) references time_futebol(id_time)
);

-- Punição

create table tipo_punicao (
    id_tipo_punicao serial primary key,
    tipo varchar(20) not null
);

create table punicao (
    id_punicao serial primary key,
    partida integer not null,
    jogador integer not null,
    time_jogador integer not null,
    tipo_punicao integer not null,
    minuto_ocorrencia integer not null,
    
    foreign key (partida) references partida(id_partida),
    foreign key (jogador) references jogador(id_jogador),
    foreign key (time_jogador) references time_futebol(id_time),
    foreign key (tipo_punicao) references tipo_punicao(id_tipo_punicao)
);

-- Partida_Jogador

create table partida_jogador (
    id_partida_jogador serial primary key,
    partida integer not null,
    jogador integer not null,
    time_jogador integer not null,
    posicao integer not null,
    
    foreign key (partida) references partida(id_partida),
    foreign key (jogador) references jogador(id_jogador),
    foreign key (time_jogador) references time_futebol(id_time),
    foreign key (posicao) references posicao_jogador(id_posicao)
);

-- Partida_Árbitro

-- desnecessário (foi incorporado à tabela partida)