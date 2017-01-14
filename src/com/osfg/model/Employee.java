package com.osfg.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author athakur
 * Model class for Employee
 */
@XmlRootElement(name = "employee")
public class Employee implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
    private int age;
     
    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
     

}
