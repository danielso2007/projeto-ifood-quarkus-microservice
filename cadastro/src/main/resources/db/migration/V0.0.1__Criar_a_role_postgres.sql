-- Role: postgres
-- DROP ROLE postgres;

DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles  -- SELECT list can be empty for this
      WHERE  rolname = 'postgres') THEN

      CREATE ROLE postgres WITH
          LOGIN
          SUPERUSER
          INHERIT
          CREATEDB
          CREATEROLE
          REPLICATION
          ENCRYPTED PASSWORD 'md53175bce1d3201d16594cebf9d7eb3f9d';
   END IF;
END
$do$;
