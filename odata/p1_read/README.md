
## Get Code, Build

~~~
$ git clone git@github.com:kylinsoong/jaxrs-examples.git
$ cd jaxrs-examples/odata/p1_read/
$ mvn clean install
~~~

demoService-swarm.jar will be generated under 'target' folder.

## Run

~~~
$ java -jar target/demoService-swarm.jar
~~~

* The Metadata Document can be invoked via the following URI:

http://localhost:8080/DemoService.svc/

* The service document can be invoked via the following URI:

http://localhost:8080/DemoService.svc/$metadata

* Products

http://localhost:8080/DemoService.svc/Products
