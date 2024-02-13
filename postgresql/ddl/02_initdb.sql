--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.0

-- Started on 2024-02-12 04:55:50

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16396)
-- Name: audit; Type: TABLE; Schema: flatsapp; Owner: misso
--

CREATE TABLE flatsapp.audit (
    uuid text NOT NULL,
    dt_create bigint,
    user_id text,
    text text,
    type text,
    id text
);


ALTER TABLE flatsapp.audit OWNER TO misso;

--
-- TOC entry 3249 (class 2606 OID 16402)
-- Name: audit audit_pkey; Type: CONSTRAINT; Schema: flatsapp; Owner: misso
--

ALTER TABLE ONLY flatsapp.audit
    ADD CONSTRAINT audit_pkey PRIMARY KEY (uuid);


-- Completed on 2024-02-12 04:55:50

--
-- PostgreSQL database dump complete
--

