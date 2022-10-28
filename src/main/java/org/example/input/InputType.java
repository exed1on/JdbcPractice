package org.example.input;

import java.util.Scanner;

public class InputType implements Input {
    private String inputType;
    @Override
    public void setString() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type s to see your fines or type e to add new fine to database");
        inputType = sc.nextLine();
        if (!inputType.equals("s") && !inputType.equals("e"))
        {
            System.out.println(inputType);
            System.out.println("You need to choose between given variants(s or e)");
        setString();
        }
    }
    @Override
    public String getString()
    {
        setString();
       return inputType;
    }
}
