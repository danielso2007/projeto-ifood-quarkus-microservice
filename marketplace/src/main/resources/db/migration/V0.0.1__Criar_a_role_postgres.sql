-- Role: cadastro
-- DROP ROLE cadastro;

DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles  -- SELECT list can be empty for this
      WHERE  rolname = 'ifood_marketplace') THEN

      CREATE ROLE ifood_marketplace WITH
          LOGIN
          SUPERUSER
          INHERIT
          CREATEDB
          CREATEROLE
          REPLICATION
          ENCRYPTED PASSWORD 'md5a3097ef3b96741e897b5966dd5d9ca6c';
   END IF;
END
$do$;
