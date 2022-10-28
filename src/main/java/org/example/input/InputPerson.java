package org.example.input;

import java.util.Scanner;

public class InputPerson implements Input {
    private String nameOrID;
    @Override
    public void setString()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Write your ID or full name");
        nameOrID= sc.nextLine();
    }
    @Override
    public String getString()
    {
        setString();
        return  nameOrID;
    }
}
