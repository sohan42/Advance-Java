/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaf;

import java.awt.Image;
import java.beans.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sohan
 */

//Creating java bean for student.
public class Student implements Serializable{ //Rule-1: Must implement Serializable
    private String name; //Rule-2: Properties must be private
    private int age; 
    
    //Rule-3: public no-arg constructor
    public Student(){
    }
    
    //Rule-4: getter and setter methods for properties
    void setName(String name){ //setter for name
        this.name = name;
    }
    
    void setAge(int age){ //setter for age
        this.age = age;
    }
    
    String getName(){ //getter for name
        return name;
    }
    
    int getAge(){ //getter for age
        return age;
    }
}

//Bean Person class
class Person implements Serializable{
    private String name;
    private int age;
    
    public Person(){
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
}

class PersonBeanInfo implements BeanInfo{

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(Person.class);
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public int getDefaultEventIndex() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor name, age;
        try {
            name = new PropertyDescriptor("uname",Person.class);
            age = new PropertyDescriptor("uage",Person.class);
            return new PropertyDescriptor[] {name, age};
        } catch (IntrospectionException ex) {
            Logger.getLogger(PersonBeanInfo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }

    @Override
    public int getDefaultPropertyIndex() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BeanInfo[] getAdditionalBeanInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Image getIcon(int iconKind) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
