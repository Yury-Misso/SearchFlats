--
-- PostgreSQL database dump
--

-- Dumped from database version 12.16 (Ubuntu 12.16-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 16.0

-- Started on 2024-02-14 01:43:52

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
-- TOC entry 8 (class 2615 OID 24730)
-- Name: flats; Type: SCHEMA; Schema: -; Owner: misso
--

CREATE SCHEMA flats;


ALTER SCHEMA flats OWNER TO misso;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 24731)
-- Name: flats; Type: TABLE; Schema: flats; Owner: misso
--

CREATE TABLE flats.flats (
    uuid character varying(36) NOT NULL,
    dt_create bigint,
    dt_update bigint,
    offer_type text,
    description text,
    area integer,
    price integer,
    floor integer,
    bedrooms integer,
    photo_urls text,
    original_url text,
    id_by_site text
);


ALTER TABLE flats.flats OWNER TO misso;

--
-- TOC entry 2833 (class 2606 OID 24738)
-- Name: flats flats_pkey; Type: CONSTRAINT; Schema: flats; Owner: misso
--

ALTER TABLE ONLY flats.flats
    ADD CONSTRAINT flats_pkey PRIMARY KEY (uuid);


-- Completed on 2024-02-14 01:43:53

--
-- PostgreSQL database dump complete
--

