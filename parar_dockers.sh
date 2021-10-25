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
echo -e "${YELLOW}Iniciando docker de banco de dados postgres, pgadmin4, mongo e mongo-express: $1${NC}"
cd docker_database
./stop.sh
cd ..
echo -e "${YELLOW}Iniciando docker do keycloak, jaegertracing, activemq e kafka: $1${NC}"
cd docker_outros
./stop.sh
cd ..
echo -e "${YELLOW}Iniciando docker do prometheus e grafana: $1${NC}"
cd docker_prometheus_grafana
./stop.sh
cd ..
docker ps --format "table {{.ID}} :: {{.Image}}\t{{.Size}}"
