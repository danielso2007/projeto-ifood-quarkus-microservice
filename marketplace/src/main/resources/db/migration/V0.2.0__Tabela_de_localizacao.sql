-- Table: public.localizacao

CREATE SEQUENCE public.localizacao_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999999;

ALTER SEQUENCE public.localizacao_id_seq
    OWNER TO ifood_marketplace;

-- DROP TABLE public.localizacao;

CREATE TABLE IF NOT EXISTS public.localizacao
(
    id bigint NOT NULL DEFAULT nextval('localizacao_id_seq'::regclass),
    data_atualizacao timestamp without time zone NOT NULL,
    data_criacao timestamp without time zone NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    CONSTRAINT localizacao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.localizacao
    OWNER to ifood_marketplace;
