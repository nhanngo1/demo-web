FROM maven

WORKDIR /usr/local/app
COPY . .
RUN mvn clean install -DskipTests=true

CMD ["mvn", "test", "-DsuiteXmlFile=demo-parallel.xml"]
