package org.example;

import java.util.Scanner;

public class InputPerson implements Input{
    private String nameOrID;
    @Override
    public void setString()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Write your ID or full name");
        String search=sc.nextLine();
        nameOrID=search;
    }
    @Override
    public String getString()
    {
        setString();
        return  nameOrID;
    }
}
