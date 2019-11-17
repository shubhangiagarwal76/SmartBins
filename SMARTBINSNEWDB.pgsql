--
-- PostgreSQL database dump
--

-- Dumped from database version 10.9
-- Dumped by pg_dump version 10.9

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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: gen; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.gen AS ENUM (
    'Female',
    'Male'
);


ALTER TYPE public.gen OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: Driver; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Driver" (
    "Driver_ID" bigint NOT NULL,
    "Mobile_No" bigint NOT NULL,
    "F_Name" character varying NOT NULL,
    "Last_Name" character varying,
    "Aadhar" bigint NOT NULL,
    "Staff_ID" bigint NOT NULL,
    "Password" character varying NOT NULL,
    "Photo" character varying,
    "Gender" public.gen NOT NULL,
    "Email_ID" character varying,
    CONSTRAINT "Driver_id" CHECK (("Driver_ID" >= 10101)),
    CONSTRAINT f_name CHECK ((length(("F_Name")::text) <= 20))
);


ALTER TABLE public."Driver" OWNER TO postgres;

--
-- Name: Driver_Driver_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Driver_Driver_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Driver_Driver_ID_seq" OWNER TO postgres;

--
-- Name: Driver_Driver_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Driver_Driver_ID_seq" OWNED BY public."Driver"."Driver_ID";


--
-- Name: Driver_Driver_ID_seqaa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Driver_Driver_ID_seqaa"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Driver_Driver_ID_seqaa" OWNER TO postgres;

--
-- Name: Dustbin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Dustbin" (
    "Dustbin_ID" bigint NOT NULL,
    "Driver_ID" bigint,
    "Status" double precision,
    "Capacity" double precision,
    "Latitude_Coordinates" double precision,
    "Longitude_Coordinates" double precision
);


ALTER TABLE public."Dustbin" OWNER TO postgres;

--
-- Name: Dustbin_Driver_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Dustbin_Driver_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Dustbin_Driver_ID_seq" OWNER TO postgres;

--
-- Name: Dustbin_Driver_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Dustbin_Driver_ID_seq" OWNED BY public."Dustbin"."Driver_ID";


--
-- Name: Dustbin_Dustbin_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Dustbin_Dustbin_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Dustbin_Dustbin_ID_seq" OWNER TO postgres;

--
-- Name: Dustbin_Dustbin_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Dustbin_Dustbin_ID_seq" OWNED BY public."Dustbin"."Dustbin_ID";


--
-- Name: Location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Location" (
    "L_ID" bigint NOT NULL,
    "Staff_ID" bigint,
    "Driver_ID" bigint,
    "L_Name" text NOT NULL,
    "Latitide_Coordinates" double precision[],
    "Longitutde_Coordinates" double precision[]
);


ALTER TABLE public."Location" OWNER TO postgres;

--
-- Name: Location_Driver_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Location_Driver_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Location_Driver_ID_seq" OWNER TO postgres;

--
-- Name: Location_Driver_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Location_Driver_ID_seq" OWNED BY public."Location"."Driver_ID";


--
-- Name: Location_L_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Location_L_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Location_L_ID_seq" OWNER TO postgres;

--
-- Name: Location_L_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Location_L_ID_seq" OWNED BY public."Location"."L_ID";


--
-- Name: Location_Staff_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Location_Staff_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Location_Staff_ID_seq" OWNER TO postgres;

--
-- Name: Location_Staff_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Location_Staff_ID_seq" OWNED BY public."Location"."Staff_ID";


--
-- Name: Staff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Staff" (
    "Staff_ID" bigint NOT NULL,
    "F_Name" character varying NOT NULL,
    "L_Name" character varying,
    "Mobile_No" bigint NOT NULL,
    "Aadhar" bigint NOT NULL,
    "Password" character varying NOT NULL,
    "Email_id" character varying(50) NOT NULL,
    "DOB" date,
    "Gender" public.gen NOT NULL
);


ALTER TABLE public."Staff" OWNER TO postgres;

--
-- Name: Staff_Staff_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Staff_Staff_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Staff_Staff_ID_seq" OWNER TO postgres;

--
-- Name: Staff_Staff_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Staff_Staff_ID_seq" OWNED BY public."Staff"."Staff_ID";


--
-- Name: Driver Driver_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Driver" ALTER COLUMN "Driver_ID" SET DEFAULT nextval('public."Driver_Driver_ID_seq"'::regclass);


--
-- Name: Dustbin Dustbin_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dustbin" ALTER COLUMN "Dustbin_ID" SET DEFAULT nextval('public."Dustbin_Dustbin_ID_seq"'::regclass);


--
-- Name: Dustbin Driver_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dustbin" ALTER COLUMN "Driver_ID" SET DEFAULT nextval('public."Dustbin_Driver_ID_seq"'::regclass);


--
-- Name: Location L_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location" ALTER COLUMN "L_ID" SET DEFAULT nextval('public."Location_L_ID_seq"'::regclass);


--
-- Name: Location Staff_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location" ALTER COLUMN "Staff_ID" SET DEFAULT nextval('public."Location_Staff_ID_seq"'::regclass);


--
-- Name: Location Driver_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location" ALTER COLUMN "Driver_ID" SET DEFAULT nextval('public."Location_Driver_ID_seq"'::regclass);


--
-- Name: Staff Staff_ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Staff" ALTER COLUMN "Staff_ID" SET DEFAULT nextval('public."Staff_Staff_ID_seq"'::regclass);


--
-- Data for Name: Driver; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Driver" ("Driver_ID", "Mobile_No", "F_Name", "Last_Name", "Aadhar", "Staff_ID", "Password", "Photo", "Gender", "Email_ID") FROM stdin;
10103	9876543212	Shivam	Kumar	120000001232	111111	shivam123	\N	Female	\N
10102	9876543211	Roopvati	Kumari	120000001235	111111	roopvati123	\N	Male	\N
10101	9876543210	Radha	Kumari	120000001234	111111	radha123	\N	Female	\N
10130	9350257870	Bablu	Sharma	120000001229	111111	bablu123	\N	Male	\N
10131	9350257878	Amit	Tripathi	120000001998	111112	amit123	\N	Male	\N
\.


--
-- Data for Name: Dustbin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Dustbin" ("Dustbin_ID", "Driver_ID", "Status", "Capacity", "Latitude_Coordinates", "Longitude_Coordinates") FROM stdin;
104	10102	\N	10.880000000000001	\N	\N
105	10103	\N	20.600000000000001	\N	\N
102	10101	\N	7.2000000000000002	\N	\N
101	10101	\N	5	\N	\N
103	10102	\N	5.7999999999999998	\N	\N
\.


--
-- Data for Name: Location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Location" ("L_ID", "Staff_ID", "Driver_ID", "L_Name", "Latitide_Coordinates", "Longitutde_Coordinates") FROM stdin;
11	111111	10101	GHGF	\N	\N
13	111111	10103	BHGF	\N	\N
1	111111	10130	BHFF	\N	\N
2	111112	10131	Academic	\N	\N
12	111111	10102	GHFF	\N	\N
\.


--
-- Data for Name: Staff; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Staff" ("Staff_ID", "F_Name", "L_Name", "Mobile_No", "Aadhar", "Password", "Email_id", "DOB", "Gender") FROM stdin;
111111	Kaushal	Saxena	9717061462	123000000123	KS123	ks123@gmail.com	2019-09-02	Male
111112	Arti	Noor	97170614688	123000000223	AN123	an234@gmail.com	1970-05-27	Female
\.


--
-- Name: Driver_Driver_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Driver_Driver_ID_seq"', 10131, true);


--
-- Name: Driver_Driver_ID_seqaa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Driver_Driver_ID_seqaa"', 1, false);


--
-- Name: Dustbin_Driver_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Dustbin_Driver_ID_seq"', 73, true);


--
-- Name: Dustbin_Dustbin_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Dustbin_Dustbin_ID_seq"', 1, false);


--
-- Name: Location_Driver_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Location_Driver_ID_seq"', 2, true);


--
-- Name: Location_L_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Location_L_ID_seq"', 2, true);


--
-- Name: Location_Staff_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Location_Staff_ID_seq"', 1, true);


--
-- Name: Staff_Staff_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Staff_Staff_ID_seq"', 1, false);


--
-- Name: Driver Driver_Aadhar_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Driver"
    ADD CONSTRAINT "Driver_Aadhar_key" UNIQUE ("Aadhar");


--
-- Name: Driver Driver_Driver_ID_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Driver"
    ADD CONSTRAINT "Driver_Driver_ID_key" UNIQUE ("Driver_ID");


--
-- Name: Driver Driver_Mobile_No_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Driver"
    ADD CONSTRAINT "Driver_Mobile_No_key" UNIQUE ("Mobile_No");


--
-- Name: Driver Driver_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Driver"
    ADD CONSTRAINT "Driver_pkey" PRIMARY KEY ("Driver_ID");


--
-- Name: Dustbin Dustbin_Dustbin_ID_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dustbin"
    ADD CONSTRAINT "Dustbin_Dustbin_ID_key" UNIQUE ("Dustbin_ID");


--
-- Name: Dustbin Dustbin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dustbin"
    ADD CONSTRAINT "Dustbin_pkey" PRIMARY KEY ("Dustbin_ID");


--
-- Name: Location Location_L_ID_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location"
    ADD CONSTRAINT "Location_L_ID_key" UNIQUE ("L_ID");


--
-- Name: Location Location_L_Name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location"
    ADD CONSTRAINT "Location_L_Name_key" UNIQUE ("L_Name");


--
-- Name: Location Location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location"
    ADD CONSTRAINT "Location_pkey" PRIMARY KEY ("L_ID");


--
-- Name: Staff Staff_Email_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Staff"
    ADD CONSTRAINT "Staff_Email_id_key" UNIQUE ("Email_id");


--
-- Name: Staff Staff_Staff_ID_Mobile_No_Aadhar_Password_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Staff"
    ADD CONSTRAINT "Staff_Staff_ID_Mobile_No_Aadhar_Password_key" UNIQUE ("Staff_ID", "Mobile_No", "Aadhar", "Password");


--
-- Name: Staff Staff_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Staff"
    ADD CONSTRAINT "Staff_pkey" PRIMARY KEY ("Staff_ID");


--
-- Name: fki_Staff; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_Staff" ON public."Location" USING btree ("Staff_ID");


--
-- Name: fki_Staff_ID; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_Staff_ID" ON public."Driver" USING btree ("Staff_ID");


--
-- Name: Dustbin Driver_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Dustbin"
    ADD CONSTRAINT "Driver_ID" FOREIGN KEY ("Driver_ID") REFERENCES public."Driver"("Driver_ID") ON DELETE SET NULL;


--
-- Name: Location Driver_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location"
    ADD CONSTRAINT "Driver_id" FOREIGN KEY ("Driver_ID") REFERENCES public."Driver"("Driver_ID");


--
-- Name: Location Staff_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Location"
    ADD CONSTRAINT "Staff_ID" FOREIGN KEY ("Staff_ID") REFERENCES public."Staff"("Staff_ID");


--
-- Name: Driver Staff_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Driver"
    ADD CONSTRAINT "Staff_ID" FOREIGN KEY ("Staff_ID") REFERENCES public."Staff"("Staff_ID");


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO PUBLIC;


--
-- PostgreSQL database dump complete
--

