package org.example;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class JdbcMain {

    public static String stringToLowerCase(String st)
    {
        char[] charArray=st.toCharArray();
        for (int i=0;i<st.length();i++)
        {
            charArray[i]=Character.toLowerCase(charArray[i]);
        }

        return  String.valueOf(charArray);
    }

    public static Double stringFineToDouble(String st)
    {
       return Double.valueOf(st
                .substring(1,st.indexOf(","))+"."+st.substring(st.indexOf(",")+1));
    }

    public static void main(String[] args) {
   try(Connection connection = getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "133154");
   Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
           ResultSet.CONCUR_UPDATABLE)){
       ResultSet rs = statement.executeQuery("select * from car_fines");
      InputType inType=new InputType();
      if(inType.getString().equals("e"))
      {
          InputNewFine inf=new InputNewFine();
          String newFine=inf.getString();
          ResultSet resSet = statement.executeQuery("select * from car_fines");
          int a=0;
          while(resSet.next())
          {
              a++;
          }
          resSet.moveToInsertRow();
          resSet.updateInt(1,a+1);
          resSet.updateString(2,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1);
          resSet.updateString(3,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1);
          resSet.updateString(4,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1);
          resSet.updateString(5,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1);
          resSet.updateString(6,newFine);
          resSet.insertRow();
          resSet.moveToCurrentRow();
      }
      else {
          InputPerson inputPerson=new InputPerson();
          String search = inputPerson.getString();
          int counterFines = 0;
          double sumFines = 0;
          while (rs.next()) {
              if (stringToLowerCase(rs.getString(2)).equals(stringToLowerCase(search))
                      || stringToLowerCase(rs.getString(3)).equals(stringToLowerCase(search))) {
                  counterFines++;
                  sumFines += stringFineToDouble(rs.getString(6));
                  System.out.printf("%s(ID %s) has fine on %s(VIN %s): %s%n",
                          rs.getString(3),
                          rs.getString(2),
                          rs.getString(4),
                          rs.getString(5),
                          rs.getString(6));
              }
          }
          if (counterFines == 0) {
              System.out.println(search + " has no fines");
          } else {
              System.out.println("You need to pay " + sumFines + " €");
          }
      }
        } catch (SQLException e) {
       e.printStackTrace();
   }
    }




}