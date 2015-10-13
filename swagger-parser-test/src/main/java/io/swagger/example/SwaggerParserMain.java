package io.swagger.example;


import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

/**
 * https://github.com/swagger-api/swagger-parser
 * 
 * @author kylin
 *
 */
public class SwaggerParserMain {

    public static void main(String[] args) {

        Swagger swagger = new SwaggerParser().read("http://localhost:8080/swagger.json");
        
        System.out.println(swagger.getInfo().getTitle());
        System.out.println(swagger.getInfo().getDescription());
        System.out.println(swagger.getInfo().getVersion());
        
        System.out.println(swagger.getHost());
        
        System.out.println(swagger.getConsumes());
        
        System.out.println(swagger.getBasePath());
        
        System.out.println(swagger.getPaths().get("/customer/customerList").getGet().getOperationId());
    }

}
