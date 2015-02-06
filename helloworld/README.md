## Start WildFly

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
## Build and Deploy

* Type this command to build and generate the archive:

        mvn clean install

* Deploy `target/helloworld.war` to the running instance of the server.


## Access the application 

The application is deployed to <http://localhost:8080/helloworld>.

The *XML* content can be viewed by accessing the following URL: <http://localhost:8080/helloworld/rest/xml> 

The *JSON* content can be viewed by accessing this URL: <http://localhost:8080/helloworld/rest/json>




