FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN apt-get update && apt-get install -y curl
RUN chmod +x ./mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser
USER appuser

FROM eclipse-temurin:17-jre-focal
VOLUME /tmp
RUN apt-get update && apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.kyosk.KyoskApplication"]