/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaf;

import java.io.Serializable;

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
