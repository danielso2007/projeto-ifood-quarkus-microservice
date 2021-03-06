#!/bin/bash
RED='\033[1;31m'
BLACK='\033[0;30m'
DARK_GRAY='\033[1;30m'
LIGHT_RED='\033[0;31m'
GREEN='\033[0;32m'
LIGHT_GREEN='\033[1;32m'
BROWN_ORANGE='\033[0;33m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
LIGHT_BLUE='\033[1;34m'
PURPLE='\033[0;35m'
LIGHT_PURPLE='\033[1;35m'
CYAN='\033[0;36m'
LIGHT_CYAN='\033[1;36m'
LIGHT_GRAY='\033[0;37m'
WHITE='\033[1;37m'
NC='\033[0m' # No Color
echo -e "${YELLOW}Parando container...${NC}"
docker-compose stop
docker-compose ps
echo -e "${YELLOW}Removendo container...${NC}"
docker-compose rm -f
echo -e "${YELLOW}Removendo volumes ...${NC}"
docker volume rm docker_database_pgadmin4conf
docker volume rm docker_database_pgadmin4serverdefinitions
docker volume rm docker_database_pgadmin4sessiondata
docker volume rm docker_database_pgsqlconfCadastro
docker volume rm docker_database_pgsqldataCadastro
docker volume rm docker_database_pgsqldblogsCadastro
docker volume rm docker_database_pgsqlconfMarketplace
docker volume rm docker_database_pgsqldataMarketplace
docker volume rm docker_database_pgsqldblogsMarketplace
docker volume rm docker_database_pgsqlconfKeycloak
docker volume rm docker_database_pgsqldataKeycloak
docker volume rm docker_database_pgsqldblogsKeycloak
docker volume rm docker_database_mongoDataPedido
docker volume rm docker_database_mongoConfigPedido
docker network rm net-ifood
docker volume ls
docker network ls