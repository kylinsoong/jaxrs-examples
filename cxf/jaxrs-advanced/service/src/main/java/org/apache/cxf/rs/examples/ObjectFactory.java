package org.apache.cxf.rs.examples;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Person_QNAME = new QName("http://ksoong.org", "Person");
    private final static QName _PersonInfo_QNAME = new QName("http://ksoong.org", "PersonInfo");
    private final static QName _PersonCollection_QNAME = new QName("http://ksoong.org", "Persons");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.example.customerservice
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     */
    public Person createPerson() {
        return new Person();
    }
    
    /**
     * Create an instance of {@link Person }
     */
    public PersonInfo createPersonInfo() {
        return new PersonInfo();
    }

    /**
     * Create an instance of {@link PersonCollection }
     */
    public PersonCollection createPersonCollection() {
        return new PersonCollection();
    }

    /**
     * Create an instance of {@link Person}
     */
    @XmlElementDecl(namespace = "http://ksoong/org/", name = "Person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link PersonInfo}
     */
    @XmlElementDecl(namespace = "http://ksoong/org/", name = "PersonInfo")
    public JAXBElement<PersonInfo> createPersonInfo(PersonInfo value) {
        return new JAXBElement<PersonInfo>(_PersonInfo_QNAME, PersonInfo.class, null, value);
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link PersonCollection }{@code >}
     */
    @XmlElementDecl(namespace = "http://ksoong/org/", name = "Persons")
    public JAXBElement<PersonCollection> createPersonCollection(PersonCollection value) {
        return new JAXBElement<PersonCollection>(_PersonCollection_QNAME, PersonCollection.class, null, value);
    }

}
