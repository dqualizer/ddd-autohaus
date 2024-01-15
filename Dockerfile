FROM maven:3-eclipse-temurin-21-alpine AS build

# COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn install -DskipTests && mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:21-jre-alpine

ARG DEPENDENCY=target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","ddd.autohaus.tactical.design.WerkstattauftragApplication"]