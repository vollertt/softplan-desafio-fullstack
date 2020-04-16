--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12
-- Dumped by pg_dump version 10.12

-- Started on 2020-04-16 11:01:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2812 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 196 (class 1259 OID 16407)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16473)
-- Name: processo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.processo (
    cd_processo bigint NOT NULL,
    cd_usuario_cadastro bigint NOT NULL,
    cd_usuario_finaliza bigint NOT NULL,
    ds_processo character varying(255) NOT NULL,
    num_processo character varying(30) NOT NULL,
    parecer character varying(5000),
    status_finalizado boolean NOT NULL,
    dt_parecer timestamp without time zone,
    dt_processo timestamp without time zone
);


ALTER TABLE public.processo OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16481)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    cd_usuario bigint NOT NULL,
    dt_cadastro timestamp without time zone NOT NULL,
    email character varying(50) NOT NULL,
    nome character varying(100) NOT NULL,
    perfil_usuario character varying(255) NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 2803 (class 0 OID 16473)
-- Dependencies: 197
-- Data for Name: processo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.processo (cd_processo, cd_usuario_cadastro, cd_usuario_finaliza, ds_processo, num_processo, parecer, status_finalizado, dt_parecer, dt_processo) FROM stdin;
6	3	8	processo de compra	1.2.1	pode comprar esta tudo certo	t	2020-04-11 18:22:58.141	2020-04-10 10:17:06.262823
160	3	159	Processo de compra de bens para a secretaria .....	1.5.7.3.2	Processo aprovado pela secretaria ........\n	t	2020-04-12 17:12:28.175	2020-04-10 10:17:06.262823
165	2	8	processo de licenciamento 2	21.11.0	finalizado parecer	t	2020-04-15 17:58:13.192	2020-04-10 10:17:06.262
155	3	8	teste novo	1.5.6	processo em análise \nok	t	2020-04-15 17:58:39.09	2020-04-10 10:17:06.262
156	3	8	processo de manutenção da....	1111	analisando o parecer	f	2020-04-15 17:59:09.594	2020-04-10 10:17:06.262
4	4	8	processo de gastos	1.1.3	meu parecer inicial é....\n	f	2020-04-15 17:59:47.176	2020-04-10 10:17:06.262
13	3	8	processo de novo investimento	3.1.0	parecer inicial	f	2020-04-16 10:07:04.204	2020-04-10 10:17:06.262
2	3	7	processo de compra	1.1.1	\N	f	\N	2020-04-10 10:17:06.262823
5	2	7	processo de contrato	1.2.0	\N	f	\N	2020-04-10 10:17:06.262823
3	4	8	processo de investimento atualizado	1.1.2	 gastos não aprovados pela lei responsabilidade fiscal	t	2020-04-10 17:22:58.141	2020-04-10 10:17:06.262823
154	3	8	teste cadastro Distribuidor	1.3.4		f	\N	2020-04-10 10:17:06.262823
166	2	7	processo de licenciamento 3	26.11.0	\N	f	\N	2020-04-10 10:17:06.262823
1	2	7	processo de licenciamento	1.1.0	Não aprovado por ............................ 	t	2010-04-14 16:05:00	2020-04-10 10:17:06.262823
168	3	159	processo nova interface e integração	22.5.7	será fornecido a análise com detalhes.\n14/04/2020 16:50:00 ) Segue a análise por email	t	2020-04-14 16:52:34.591	2020-04-10 10:17:06.262823
\.


--
-- TOC entry 2804 (class 0 OID 16481)
-- Dependencies: 198
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (cd_usuario, dt_cadastro, email, nome, perfil_usuario, status) FROM stdin;
8	2020-04-11 21:51:14.034	teste7@teste.com	teste7	FINALIZADOR	t
14	2020-04-11 21:51:28.389	testeform@teste.com	teste form	DISTRIBUIDOR	t
3	2020-04-11 21:51:34.723	teste2@teste.com	teste2	DISTRIBUIDOR	t
6	2020-04-11 22:14:15.708	teste5@teste.com	teste5	DISTRIBUIDOR	t
153	2020-04-12 22:32:20.149	joao2@teste.com	joao2	DISTRIBUIDOR	t
157	2020-04-13 18:21:37.545	joao1111@teste.com	joao111111	DISTRIBUIDOR	t
2	2020-04-11 22:22:27.801	teste1@teste.com	teste1	DISTRIBUIDOR	t
142	2020-04-12 08:40:44.775	teste25@teste.com	teste25	DISTRIBUIDOR	t
151	2020-04-12 22:28:14.647	teste33@dddd.ccccc	teste33	DISTRIBUIDOR	t
159	2020-04-13 20:34:26.203	novoteste@teste.com	novoteste	FINALIZADOR	t
167	2020-04-14 16:39:41.613	novoteste2@teste.com	novoteste2	DISTRIBUIDOR	t
5	2020-04-11 21:46:45.423	teste444@teste.com	teste444	DISTRIBUIDOR	t
12	2020-04-11 21:46:51.617	teste8@teste.com	teste8 new	FINALIZADOR	f
143	2020-04-12 08:49:26.016	mariajose@org.com	Maria José	DISTRIBUIDOR	t
152	2020-04-12 22:31:20.734	email1@dddd.cc	email1	DISTRIBUIDOR	t
26	2020-04-11 22:16:15.612	aaaa@ddd.bbb	aaaa	FINALIZADOR	t
42	2020-04-11 19:56:25.058	testedesafio@teste.com	testdesafio	FINALIZADOR	t
20	2020-04-11 22:27:07.362	euteste@ddd.com	Eu Teste	DISTRIBUIDOR	t
4	2020-04-11 22:20:36.062	teste3@teste.com	teste3 	DISTRIBUIDOR	t
17	2020-04-11 21:51:02.049	nome8@ui.com	nome8	DISTRIBUIDOR	f
158	2020-04-13 20:32:01.539	teste49@teste.com	teste49	DISTRIBUIDOR	t
28	2020-04-11 19:27:06.156	testeemail@teste.com	sdsadsad	DISTRIBUIDOR	t
19	2020-04-11 22:19:14.063	teste@teste44.com	teste55	DISTRIBUIDOR	t
25	2020-04-11 18:42:17.135	teste88@teste.com	teste88	FINALIZADOR	f
103	2020-04-11 22:14:05.533	teste99@teste.br	teste 99	DISTRIBUIDOR	t
15	2020-04-11 22:14:35.796	t88@teste.com	novo teste 88	FINALIZADOR	t
38	2020-04-11 19:39:04.503	marcel1@teste.com	marcel1	DISTRIBUIDOR	t
18	2020-04-11 22:16:39.856	brasil@gov.br	brasil	DISTRIBUIDOR	t
16	2020-04-11 21:47:00.659	alfa2@teste.com	alfa2	DISTRIBUIDOR	t
102	2020-04-11 21:46:36.145	meuemail@teste.com	teste meu email	FINALIZADOR	f
67	2020-04-11 21:22:56.384	distribuidor@org.com	distribuidor	DISTRIBUIDOR	t
66	2020-04-11 21:21:18.249	jose@org.com	Jose	DISTRIBUIDOR	t
56	2020-04-11 20:23:48.853	joao@teste.com	Joao	DISTRIBUIDOR	t
7	2020-04-11 21:51:18.35	teste6@teste.com	teste6	FINALIZADOR	f
33	2020-04-11 19:32:27.878	aaaaa@bbb.com	qqqsss	DISTRIBUIDOR	f
34	2020-04-11 19:33:38.934	sem@sem.com	sem nome	DISTRIBUIDOR	f
169	2020-04-15 13:46:50.637	newuser2@user.org	New user 2	FINALIZADOR	t
1	2020-04-11 21:47:52.257	admin@teste.com	Admin update	ADMIN	t
\.


--
-- TOC entry 2813 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 169, true);


--
-- TOC entry 2676 (class 2606 OID 16480)
-- Name: processo processo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo
    ADD CONSTRAINT processo_pkey PRIMARY KEY (cd_processo);


--
-- TOC entry 2678 (class 2606 OID 16485)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (cd_usuario);


--
-- TOC entry 2680 (class 2606 OID 16491)
-- Name: processo fk9uab01ukan5nhl850ex083sx5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo
    ADD CONSTRAINT fk9uab01ukan5nhl850ex083sx5 FOREIGN KEY (cd_usuario_cadastro) REFERENCES public.usuario(cd_usuario);


--
-- TOC entry 2679 (class 2606 OID 16486)
-- Name: processo fkaxm6ps2tuau8b95a5jlbg5gc4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.processo
    ADD CONSTRAINT fkaxm6ps2tuau8b95a5jlbg5gc4 FOREIGN KEY (cd_usuario_finaliza) REFERENCES public.usuario(cd_usuario);


-- Completed on 2020-04-16 11:01:06

--
-- PostgreSQL database dump complete
--

