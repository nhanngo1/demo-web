FROM maven

WORKDIR /usr/local/app
COPY . .
RUN mvn clean install

CMD ["mvn", "test", "-DsuiteXmlFile=demo.xml"]
