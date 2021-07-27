-- Table: public.prato_cliente

-- DROP TABLE public.prato_cliente;

CREATE TABLE IF NOT EXISTS public.prato_cliente
(
    prato bigint NOT NULL,
    cliente character varying(500)[] COLLATE pg_catalog."default" NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public.prato_cliente
    OWNER to ifood_marketplace;

COMMENT ON TABLE public.prato_cliente
    IS 'Tabela de cadastro do carrinho do cliente.';