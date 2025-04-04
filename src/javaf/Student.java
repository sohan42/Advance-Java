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

//Bean Person class with the concept of serialization
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
    /*In main:
        Person p = new Person();
       p.setName("Amit");
       p.setAge(24);
       
       FileOutputStream fo = new FileOutputStream("person.ser");
       ObjectOutputStream out = new ObjectOutputStream(fo);
       out.writeObject(p);
       System.out.println("Serializaed data is saved in person.ser");
       
       FileInputStream fi = new FileInputStream("person.ser");
       ObjectInputStream in = new ObjectInputStream(fi);
       Person per = (Person) in.readObject();
       System.out.println("Deserialized person...");
       System.out.println("Name: "+per.getName());
       System.out.println("Age: "+per.getAge());
    */
}

//Implementing BeanInfo interfac on Person class
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
    
    /* In main:
        BeanInfo info = Introspector.getBeanInfo(Person.class);
       
       BeanDescriptor beanDescriptor = info.getBeanDescriptor();
       System.out.println("Class name: "+beanDescriptor.getName());
       
       PropertyDescriptor[] propertyDescriptor = info.getPropertyDescriptors();
       System.out.println("Properties: ");
       for(PropertyDescriptor pd: propertyDescriptor){
           System.out.println("Name: "+pd.getName()); //returns the name of the property
           System.out.println("Type: "+pd.getPropertyType()); //returns the type of the property
           System.out.println("Read method: "+pd.getReadMethod()); //returns the getter method
           System.out.println("Write method: "+pd.getWriteMethod()); //returns the setter method
           System.out.println(); 
    */
}

//Program using Bean Class Employee to demonstrate Bound property
class Employee implements Serializable{
    private String name;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public Employee(){} //no-args constructor
    
    public Employee(String name){ //parameterized constructor
        this.name = name;
    } 
    public String getName(){
        return name;
    }
    public void setName(String name){
        String oldName = this.name;
        this.name=name;
        pcs.firePropertyChange("name", oldName, name);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }
    
    /*In main:
        Employee e = new Employee("Abhi Basnet");
        e.addPropertyChangeListener(new PropertyChangeListener(){
           @Override
           public void propertyChange(PropertyChangeEvent evt) {
               System.out.println("Property: "+evt.getPropertyName()+" Changed from "+evt.getOldValue()+" to "+evt.getNewValue());           
           }
       });
       //e.setName("Raj Gurung");
    */
}

//Program using Bean Class User to demonstrate Constrained property
class User implements Serializable{
    private String name;
    private int age;
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);
    
    public User(){}
    
    public User(String name, int age){
        this.name=name;
        this.age=age;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    
    public void setAge(int age) throws PropertyVetoException{
        int oldAge = this.age;
        PropertyChangeEvent e = new PropertyChangeEvent(this, "age",oldAge,age); 
        vcs.fireVetoableChange(e);
        this.age = age;
    } 
    
    public void addVetoableChangeListeter(VetoableChangeListener listener){
        vcs.addVetoableChangeListener(listener);
    }
    /*In main:
        User u = new User("Raj",24);
       u.addVetoableChangeListeter(new VetoableChangeListener(){
           @Override
           public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException{
               if("age".equals(e.getPropertyName())){
                   int newAge = (int) e.getNewValue();
                   if(newAge<0){
                       throw new PropertyVetoException("Age cannot be negative",e);
                   }
               }
             System.out.println(e.getPropertyName()+"Changed from "+e.getOldValue()+" to "+e.getNewValue());
           }
       });
       try{
            u.setName("Amit");
            u.setAge(-30);
        }catch(PropertyVetoException ex){
            System.out.println(ex.getMessage());
        }
    */
}
