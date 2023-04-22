.EXPORT_ALL_VARIABLES:
PROJECT_VERSION=0.0.0-SNAPSHOT

dev-dependencies:
	docker compose up -d database

build-all: build-openapi
	mvn package -Pprod

build-fast:
	mvn package -Pprod -DskipTests -DskipChecks

build:
	mvn package -Pprod

build-openapi:
	mvn package -Popen-api -DskipTests -DskipChecks -am -pl "service"

build-all:build-openapi web-build build-fast dev-dependencies

run:
	docker compose up -d service --build

dev-service:
	mvn compile quarkus:dev -am -pl "service"

dev:dev-service

web-build:
	mvn clean package -pl web -DskipTests

dev-web:
	yarn --cwd ./web dev

generate-jwt:
	mvn exec:java -pl "service" -Dexec.mainClass=com.lopixlabs.app.fullstackdemo.GenerateToken -Dexec.classpathScope=test -Dsmallrye.jwt.sign.key.location=privateKey.pem
