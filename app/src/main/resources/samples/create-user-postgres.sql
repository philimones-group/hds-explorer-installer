create database "$DATABASE";
create user "$USERNAME" with password '$PASSWORD';

grant all privileges on database "$DATABASE" to "$USERNAME";
alter database "$DATABASE" owner to "$USERNAME";

-- Read-Only Export Database User
create user "$EXPORT_USERNAME" with password '$EXPORT_PASSWORD';
grant connect on database "$DATABASE" to "$EXPORT_USERNAME";
grant usage on schema public to "$EXPORT_USERNAME";
grant select on all tables in schema public to "$EXPORT_USERNAME";

-- \c "hds_explorer_db";
-- create schema "hds_explorer_db";
-- grant all privileges on schema "hds_explorer_db" to "hds_explorer";
-- alter schema "hds_explorer_db" owner to "hds_explorer";