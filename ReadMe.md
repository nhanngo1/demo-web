# Selenium - Docker: End2End Test


## Installation
### 1. Non docker
1. Setup [Java and Maven environments](https://www.tutorialspoint.com/maven/maven_environment_setup.htm)
2. Download [chrome-driver](http://chromedriver.chromium.org/downloads), add chrome-driver to your PATH.
3. Download this repo using git clone
4. Open terminal, run command `mvn clean install -DskipTests=true` at project folder to install dependencies
5. To run test, run command `mvn test -DsuiteXmlFile={file.xml}`
> Example: `mvn test -DsuiteXmlFile=demo.xml`

### 2. Docker
1. Install docker
2. Rename `.env-example` to `.env` and fill the necessary values
3. Build images `docker-compose build`
4. Run tests `docker-compose up`
