FROM eclipse-temurin:17-jdk

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'
WORKDIR /app

COPY entrypoint.sh  ./
COPY --chown=185 service/target/quarkus-app/lib /app/lib
COPY --chown=185 service/target/quarkus-app/app /app/app
COPY --chown=185 service/target/quarkus-app/quarkus /app/quarkus
COPY --chown=185 service/target/quarkus-app/quarkus-run.jar /app/app.jar

EXPOSE 8080
USER 185

CMD ["/app/entrypoint.sh"]
