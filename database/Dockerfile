FROM mariadb:lts

COPY ./hyeonworld.sql /docker-entrypoint-initdb.d/
COPY 50-server.cnf /etc/mysql/mariadb.conf.d/50-server.cnf
COPY mariadb.cnf /etc/mysql/mariadb.cnf

ENV MARIADB_DATABASE hyeonworld
ENV	MARIADB_ROOT_PASSWORD {MY_DB_ROOT_PASSWORD}

