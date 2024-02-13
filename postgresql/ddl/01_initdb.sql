--
-- PostgreSQL database dump
--

-- Dumped from database version 12.16 (Ubuntu 12.16-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 16.0

-- Started on 2024-01-21 18:09:45

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
-- TOC entry 8 (class 2615 OID 26231)
-- Name: flatsapp; Type: SCHEMA; Schema: -; Owner: misso
--

CREATE SCHEMA flatsapp;


ALTER SCHEMA flatsapp OWNER TO misso;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 26232)
-- Name: user; Type: TABLE; Schema: flatsapp; Owner: misso
--

CREATE TABLE flatsapp."user" (
    uuid character varying(36) NOT NULL,
    dt_create bigint,
    dt_update bigint,
    mail text,
    fio text,
    role text,
    status text,
    password text
);


ALTER TABLE flatsapp."user" OWNER TO misso;

--
-- TOC entry 2831 (class 2606 OID 26239)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: flatsapp; Owner: misso
--

ALTER TABLE ONLY flatsapp."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (uuid);


-- Completed on 2024-01-21 18:09:45

--
-- PostgreSQL database dump complete
--

