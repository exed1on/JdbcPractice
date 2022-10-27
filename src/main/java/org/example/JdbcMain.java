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
          String sql = "INSERT INTO car_fines VALUES (?, ?, ?, ?, ?)";
          PreparedStatement pstmt = connection.prepareStatement(sql);
//          rs.moveToInsertRow();
          pstmt.setString(1,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          pstmt.setString(2,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          pstmt.setString(3,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          pstmt.setString(4,newFine.substring(0,newFine.indexOf(",")));
          newFine=newFine.substring(newFine.indexOf(",")+1,newFine.length());
          pstmt.setString(5,newFine.substring(0,newFine.length()));
          pstmt.executeUpdate();
//          rs.insertRow();
//          rs.moveToCurrentRow();
      }
      else {
          InputPerson inPers=new InputPerson();
          String search = inPers.getString();
          int counterFines = 0;
          double sumFines = 0;
          String temp = "";
          while (rs.next()) {
              // System.out.println(rs.getString(1)+"-----"+search);
              if (stringToLowerCase(rs.getString(1)).equals(stringToLowerCase(search))
                      || stringToLowerCase(rs.getString(2)).equals(stringToLowerCase(search))) {
                  counterFines++;
                  sumFines += stringFineToDouble(rs.getString(5));
                  System.out.printf("%s(ID %s) has fine on %s(VIN %s) %s%n",
                          rs.getString(2),
                          rs.getString(1),
                          rs.getString(3),
                          rs.getString(4),
                          rs.getString(5));
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