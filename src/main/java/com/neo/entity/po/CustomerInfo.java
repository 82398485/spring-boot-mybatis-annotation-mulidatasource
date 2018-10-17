package com.neo.entity.po;

import java.io.Serializable;
import java.util.List;

/**
 * Created by songcj on 2018/10/8.
 */
public class CustomerInfo implements Serializable,CommonEntity {

    private String userName;
    private double weight;
    private int age;

    public CustomerInfo(){
        super();
    }

    public CustomerInfo(String userName, double weight, int age){
        super();
        this.userName = userName;
        this.weight = weight;
        this.age = age;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "userName " + this.userName + ", weight " + this.weight + ", age " + this.age;
    }

    @Override
    public void cast(List list) throws Exception{
        userName = list.get(0).toString();
        weight = Double.parseDouble(list.get(1).toString());
        age = (int) Double.parseDouble(list.get(2).toString());
    }


}
