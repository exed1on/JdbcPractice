package org.example;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class JdbcMain {

    public static String stringToLowerCase(String st)
    {
        String stRes="";
        char[] charArray=st.toCharArray();
        for (int i=0;i<st.length();i++)
        {
            stRes+=Character.toLowerCase(charArray[i]);
        }
        return stRes;
    }

    public static Double stringFineToDouble(String st)
    {
       return Double.valueOf(st
                .substring(1,st.indexOf(","))+"."+st.substring(st.indexOf(",")+1,st.length()));
    }

    public static void main(String[] args) {
   try(Connection connection = getConnection("jdbc:postgresql://localhost:5432/exed1", "postgres", "133154");
   Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
           ResultSet.CONCUR_UPDATABLE)){
       ResultSet rs = statement.executeQuery("select * from car_fines");
      InputType inType=new InputType();
      if(inType.getString().equals("e"))
      {
          InputNewFine inf=new InputNewFine();
          String newFine=inf.getString();
          ResultSet rset = statement.executeQuery("select * from car_fines");
          int a=0;
          while(rset.next())
          {
              a++;
          }
          rset.moveToInsertRow();
          rset.updateInt(1,a+1);
          rset.updateString(2,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          rset.updateString(3,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          rset.updateString(4,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          rset.updateString(5,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          rset.updateString(6,newFine.substring(0,newFine.length()));
          rset.insertRow();
          rset.moveToCurrentRow();
      }
      else {
          InputPerson inPers=new InputPerson();
          String search = inPers.getString();
          int counterFines = 0;
          double sumFines = 0;
          String temp = "";
          while (rs.next()) {
              if (stringToLowerCase(rs.getString(2)).equals(stringToLowerCase(search))
                      || stringToLowerCase(rs.getString(3)).equals(stringToLowerCase(search))) {
                  counterFines++;
                  sumFines += stringFineToDouble(rs.getString(6));
                  System.out.printf("%s(ID %s) has fine on %s(VIN %s) %s%n",
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