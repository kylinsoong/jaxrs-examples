
What is it?
-----------

This example demonstrates an external JAX-RS RestEasy client which interacts with a JAX-RS Web service that uses *CDI 1.0* and *JAX-RS* 
in Red Hat JBoss Enterprise Application Platform.

This client "calls" the HelloWorld JAX-RS Web Service that was created in the `helloworld` project. 


System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 6.1 or later. 

All you need to build this project is Java 6.0 (Java SDK 1.6) or later, Maven 3.0 or later. 


Prerequisites
-----------

IMPORTANT: This project depends on the deployment of the 'helloworld' quickstart for its test. Before running this quickstart, see the [helloworld README](../helloworld/README.md) file for details on how to deploy it.

You can verify the deployment of the `helloworld-rs` quickstart by accessing the following content:

* The *XML* content can be viewed by accessing the following URL: <http://localhost:8080/helloworld/rest/xml> 
* The *JSON* content can be viewed by accessing this URL: <http://localhost:8080/helloworld/rest/json>


Investigate the Console Output
----------------------------

This command will compile the example and execute a test to make two separate requests to the Web Service.  Towards the end of the Maven build output, you 
should see the following if the execution is successful:

        ===============================================
        URL: http://localhost:8080/helloworld/rest/xml
        MediaType: application/xml

        *** Response from Server ***

        <xml><result>Hello World!</result></xml>
    
        ===============================================
        ===============================================
        URL: http://localhost:8080/helloworld/rest/json
        MediaType: application/json

        *** Response from Server ***

        {"result":"Hello World!"}
        ===============================================


