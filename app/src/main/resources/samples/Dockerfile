FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# System resources directory
RUN mkdir -p /var/lib/hds-explorer

# Copy the configured executable WAR archive
COPY $WAR_FILENAME /app/hds-explorer-server.war

EXPOSE 8080

CMD ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "/app/hds-explorer-server.war"]
