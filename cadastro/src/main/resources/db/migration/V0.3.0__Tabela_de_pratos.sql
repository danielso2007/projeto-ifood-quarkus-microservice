-- Table: public.prato

CREATE SEQUENCE public.prato_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999999;

-- DROP TABLE public.prato;

CREATE TABLE IF NOT EXISTS public.prato
(
    id bigint NOT NULL DEFAULT nextval('prato_id_seq'::regclass),
    data_atualizacao timestamp without time zone NOT NULL,
    data_criacao timestamp without time zone NOT NULL,
    descricao character varying(500) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(300) COLLATE pg_catalog."default" NOT NULL,
    preco numeric(19,2) NOT NULL,
    restaurante_id bigint NOT NULL,
    CONSTRAINT prato_pkey PRIMARY KEY (id),
    CONSTRAINT uk3c8w5y5kl8fxhnafxfb037k39 UNIQUE (nome),
    CONSTRAINT fk43yo4ddilkvn6ebgfcx2vnqs7 FOREIGN KEY (restaurante_id)
        REFERENCES public.restaurante (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.prato
    OWNER to ifood_cadastro;
-- Index: indexpratosnome

-- DROP INDEX public.indexpratosnome;

CREATE INDEX indexpratosnome
    ON public.prato USING btree
    (nome COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;