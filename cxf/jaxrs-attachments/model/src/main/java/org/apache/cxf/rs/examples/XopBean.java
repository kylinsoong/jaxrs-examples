package org.apache.cxf.rs.examples;

import java.awt.Image;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "xopBean", namespace = "http://ksoong.org/xop")
@XmlType(name = "XopBean", propOrder = {
        "name",
        "datahandler",
        "bytes",
        "image" })
public class XopBean {
	
	private String name;
    private DataHandler datahandler;
    private byte[] bytes;
    private Image image;

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    public byte[] getBytes() {
        return bytes;
    }

    
    public void setBytes(byte[] value) {
        this.bytes = value;
    }
    
    
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    public DataHandler getDatahandler() {
        return datahandler;
    }

    
    public void setDatahandler(DataHandler value) {
        this.datahandler = value;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

}
