CREATE TABLE ramo_atividades
(
    id        INT PRIMARY KEY,
    descricao VARCHAR(80)
);

CREATE TABLE empresas
(
    id                INT PRIMARY KEY,
    cnpj              VARCHAR(18),
    nome_fantasia     VARCHAR(80),
    razao_social      VARCHAR(120),
    tipo              VARCHAR(10),
    fundado_em        DATE,
    ramo_atividade_id INT,
    FOREIGN KEY (ramo_atividade_id) REFERENCES ramo_atividades (id)
);

insert into ramo_atividades (id, descricao)
values (1, 'Distribuição de alimentos');
insert into ramo_atividades (id, descricao)
values (2, 'Telecomunicações');
insert into ramo_atividades (id, descricao)
values (3, 'Vestuário');
insert into ramo_atividades (id, descricao)
values (4, 'Lavanderia');
insert into ramo_atividades (id, descricao)
values (5, 'Gráfica');
insert into ramo_atividades (id, descricao)
values (6, 'Mecânica');
insert into ramo_atividades (id, descricao)
values (7, 'Turismo');
insert into ramo_atividades (id, descricao)
values (8, 'Saúde');
insert into ramo_atividades (id, descricao)
values (9, 'Educação');
insert into ramo_atividades (id, descricao)
values (10, 'Lazer');

insert into empresas (id, cnpj, nome_fantasia, razao_social, tipo, fundado_em, ramo_atividade_id)
values (1, '70.311.193/0001-87', 'Mercado do João', 'João Mercado e Distribuidor de Alimentos Ltda', 'LTDA',
        '2009-03-02', 1);
insert into empresas (id, cnpj, nome_fantasia, razao_social, tipo, fundado_em, ramo_atividade_id)
values (2, '52.822.994/0001-25', 'Fale Mais', 'Fale Mais Telecom S.A.', 'SA', '1997-12-10', 2);
insert into empresas (id, cnpj, nome_fantasia, razao_social, tipo, fundado_em, ramo_atividade_id)
values (3, '41.952.519/0001-57', 'Maria de Souza da Silva', 'Maria de Souza da Silva', 'MEI', '2014-10-15', 3);
insert into empresas (id, cnpj, nome_fantasia, razao_social, tipo, fundado_em, ramo_atividade_id)
values (4, '16.134.777/0001-89', 'Gomes Inovação', 'José Fernando Gomes EIRELI ME', 'EIRELI', '2009-03-02', 4);