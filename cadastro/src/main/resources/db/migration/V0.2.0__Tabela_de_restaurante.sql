-- Table: public.restaurante


CREATE SEQUENCE public.restaurante_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999999;

ALTER SEQUENCE public.localizacao_id_seq
    OWNER TO ifood_cadastro;
-- DROP TABLE public.restaurante;

CREATE TABLE IF NOT EXISTS public.restaurante
(
    id bigint NOT NULL DEFAULT nextval('restaurante_id_seq'::regclass),
    data_atualizacao timestamp without time zone NOT NULL,
    data_criacao timestamp without time zone NOT NULL,
    cnpj character varying(14) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(300) COLLATE pg_catalog."default" NOT NULL,
    proprietario character varying(300) COLLATE pg_catalog."default" NOT NULL,
    localizacao_id bigint,
    CONSTRAINT restaurante_pkey PRIMARY KEY (id),
    CONSTRAINT ukjd0r4hoyk81uwt7jb88va5noa UNIQUE (nome),
    CONSTRAINT fkdfbggt9ievc4ev74wl3tdnscl FOREIGN KEY (localizacao_id)
        REFERENCES public.localizacao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.restaurante
    OWNER to ifood_cadastro;
-- Index: indexrestaurantenome

-- DROP INDEX public.indexrestaurantenome;

CREATE INDEX indexrestaurantenome
    ON public.restaurante USING btree
    (nome COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;