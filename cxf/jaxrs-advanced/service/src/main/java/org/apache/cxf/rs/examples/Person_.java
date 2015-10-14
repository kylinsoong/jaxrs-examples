package org.apache.cxf.rs.examples;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel(value=Person.class)
public class Person_ {
	
	public static volatile SingularAttribute<Person, Integer> age;
    public static volatile SetAttribute<Person, Person> children;
    public static volatile SingularAttribute<Person, Person> father;
    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, Person> mother;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Person> partner;

}
