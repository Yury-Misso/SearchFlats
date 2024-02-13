FROM postgres:16.1-alpine3.19

LABEL authors="Misso"

ADD ddl/01_initdb.sql /docker-entrypoint-initdb.d/
ADD ddl/02_initdb.sql /docker-entrypoint-initdb.d/
ADD ddl/03_initdb.sql /docker-entrypoint-initdb.d/