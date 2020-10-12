import java.util.*;
public class TimeIncrementer{
   public static void main(String[]args){
      Scanner sc = new Scanner(System.in);
      System.out.print("enter start time hh");
      int starthh = sc.nextInt();
      System.out.print("enter start time mm");
      int startmm = sc.nextInt();
      System.out.print("enter sis");
      int sis = sc.nextInt();
      System.out.print("enter end hh");
      int endhh = sc.nextInt();
      System.out.print("enter end mm");
      int endmm = sc.nextInt();
      int inchh = starthh;
      int incmm = startmm;
      
      if(inchh<10)System.out.print("0"+inchh+":");
      else System.out.print(inchh+":");
      if(incmm<10)System.out.println("0"+incmm);
      else System.out.println(incmm);      
      while(endhh>inchh || endmm>incmm){
         incmm=incmm+sis;
         if(incmm>=60){
            inchh++;
            incmm=incmm-60;
         }
         //if(inchh>12)inchh=inchh-12;
         if(inchh<10)System.out.print("0"+inchh+":");
         else System.out.print(inchh+":");
         if(incmm<10)System.out.println("0"+incmm);
         else System.out.println(incmm);
      }
      
   }
}