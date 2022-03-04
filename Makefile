.EXPORT_ALL_VARIABLES:
PROJECT_VERSION=0.0.0-SNAPSHOT

build-all: build-openapi
	mvn package -Pprod

build-fast:
	mvn package -Pprod -DskipTests -DskipChecks

build:
	mvn package -Pprod

build-openapi:
	mvn package -Popen-api -DskipTests -DskipChecks -am -pl "service"

run:
	java -jar service/target/quarkus-app/quarkus-run.jar

dev-service:
	mvn compile quarkus:dev -am -pl "service"

dev:dev-service

web-build:
	mvn clean package -pl web -DskipTests

web-dev:
	yarn start --prefix ./web


