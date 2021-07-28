-- Table: public.localizacao

-- DROP TABLE public.localizacao;

CREATE TABLE IF NOT EXISTS public.localizacao
(
    id bigint NOT NULL,
    data_atualizacao timestamp without time zone NOT NULL,
    data_criacao timestamp without time zone NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    CONSTRAINT localizacao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.localizacao
    OWNER to ifood_marketplace;
