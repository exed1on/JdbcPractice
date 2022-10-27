package org.example;

import java.util.Scanner;

public class InputPerson {
    private String nameOrID;
    private void setString()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Write your ID or full name");
        String search=sc.nextLine();
        nameOrID=search;
    }
    public String getString()
    {
        setString();
        return  nameOrID;
    }
}
