## Deploy and Consume Customer Service

* Build

Maven build commands:

~~~
mvn clean install
~~~

will generate deployment war `customer.war`.

* Deploy

Deploy `CustomerRESTWebSvc.war` to a running JBoss server(Assume JBoss EAP 6 run on localhost).

* Consume

[http://localhost:8080/customer/customer/customerList](http://localhost:8080/customer/customer/customerList)



