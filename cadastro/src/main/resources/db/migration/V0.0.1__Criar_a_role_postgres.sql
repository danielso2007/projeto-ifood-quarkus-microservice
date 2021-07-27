-- Role: cadastro
-- DROP ROLE cadastro;

DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles  -- SELECT list can be empty for this
      WHERE  rolname = 'ifood_cadastro') THEN

      CREATE ROLE ifood_cadastro WITH
          LOGIN
          SUPERUSER
          INHERIT
          CREATEDB
          CREATEROLE
          REPLICATION
          ENCRYPTED PASSWORD 'md5262741e0f57fd6e5e28281928947b644';
   END IF;
END
$do$;
