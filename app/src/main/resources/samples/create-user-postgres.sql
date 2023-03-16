create database "$DATABASE";
create user "$USERNAME" with password '$PASSWORD';

grant all privileges on database "$DATABASE" to "$USERNAME";
alter database "$DATABASE" owner to "$USERNAME";

-- \c "hds_explorer_db";
-- create schema "hds_explorer_db";
-- grant all privileges on schema "hds_explorer_db" to "hds_explorer";
-- alter schema "hds_explorer_db" owner to "hds_explorer";