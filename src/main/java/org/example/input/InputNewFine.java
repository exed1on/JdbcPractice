package org.example.input;

import java.util.Scanner;

public class InputNewFine implements Input {
    private String inputedFine;
    @Override
    public void setString()
    {
        String temp;
        Scanner sc = new Scanner(System.in);
            System.out.println("Write your password");
            String passport = sc.nextLine();
            if (passport.equals("admin")) {
                inputedFine="";
                System.out.println("Write person's ID");
               temp = sc.nextLine();
               inputedFine+=temp+", ";
                System.out.println("Write person's full name");
                temp = sc.nextLine();
                inputedFine+=temp+", ";
                System.out.println("Write car maker");
                temp = sc.nextLine();
                inputedFine+=temp+", ";
                System.out.println("Write car VIN");
                temp = sc.nextLine();
                inputedFine+=temp+", ";
                System.out.println("Write fine amount");
                temp = sc.nextLine();
                inputedFine+=temp;
            }
            else
            {
                System.out.println("Password is wrong");
            }

    }

@Override
    public String getString() {
        setString();
        return inputedFine;
    }
}
