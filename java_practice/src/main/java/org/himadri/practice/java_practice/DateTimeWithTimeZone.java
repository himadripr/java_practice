package org.himadri.practice.java_practice;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateTimeWithTimeZone {
   public static void main(String[] args) throws Exception {
      // displaying current date and time
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
      System.out.println("Date and time = "+simpleformat.format(cal.getTime()));
      // displaying date
      simpleformat = new SimpleDateFormat("dd/MMMM/yyyy");
      String str = simpleformat.format(new Date());
      System.out.println("Current Date = "+str);
      // current time
      simpleformat = new SimpleDateFormat("HH.mm.ss");
      String strTime = simpleformat.format(new Date());
      System.out.println("Current Time = "+strTime);
      // displaying timezone in zzzz format
      simpleformat = new SimpleDateFormat("zzz");
      String strTimeZone = simpleformat.format(new Date());
      System.out.println("TimeZone in zzzz format = "+strTimeZone);
   }
}