/**`
 * Module 5 Project: Cemetery
 *
 * Name: Hershel Thomas
 * 
 * AP Computer Science, Virtual Virginia
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
public class Cemetery
{
   //////// MAIN ////////
   public static void main (String [] args)
   {
      File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      //TESTING ONLY: un-comment the 2 lines below to see if array was created properly
      //for (int i = 0; i < numEntries; i++) 
        //System.out.println(cemetery[i].getName() + " " + cemetery[i].getAge());
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());  
      //prints out ResidentialAddress of youngest person in order to meet level 2 requirments.
      System.out.println("Residential Address of youngest person: " + cemetery[min].getResidentialAddress());  
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());
      //prints out ResidentialAddress of oldest person in order to meet level 2 reuirments.
      System.out.println("Residential Address of oldest person: " + cemetery[max].getResidentialAddress());
      //prints out most common Residential Adress and how many times it appears to meet full level 2 requriments.
      System.out.println(mostPopularAddress(cemetery));
   }
   
   //////// METHODS (Cemetery) ////////

   /* Counts and returns the number of entries in File f.
    * Uses a try-catch block.   
    * @param f -- the file object
   */
   public static int countEntries(File f)
   {
       int i = 0;
       try
       {
           Scanner scan = new Scanner(f);
           while (scan.hasNextLine())
           {
               i++;
               scan.nextLine();
           }
       }
       catch (Exception e)
       {
           System.out.println("Check filename.");
       }
       
       return i;
   }

   /* Reads the data.
    * Fills the array with Person objects.
    * Uses a try-catch block.   
    * @param f -- the file object 
    *        num -- the number of lines in the File f  
    */
   public static Person[] readIntoArray (File f, int num)
   {
      Person[] persons; 
      persons = new Person[num];
      try
      {
          Scanner scan = new Scanner(f);
          String nexLi = scan.nextLine();
          for (int j = 0; j < persons.length; j++) {
              persons[j] = makePerson(nexLi);
              if (scan.hasNextLine()) {
                  nexLi = scan.nextLine();
              }
          }
      }
      catch (Exception e)
      {
           System.out.println("File not found");
      } 
      return persons;
   }
   /*A method to return the String telling the most popular Address 
    * and how many times it appeard to achieve the full 100 percent grade
   */
   public static String mostPopularAddress(Person[] arr) {
       String address = "";
       int countNames = 0;
       int mostPopular = 0;
       for (int i = 0; i < arr.length; i++) {
           countNames = 0;
           for (int j = 0; j < arr.length; j++) {
               if (arr[i].getResidentialAddress().equals(arr[j].getResidentialAddress())) {
                   countNames++;
               } 
               if (countNames > mostPopular) {
                    mostPopular = countNames;
                    address = arr[i].getResidentialAddress();
               }
           }
       }
       return "Most common Residential Adress is: " + address  + ", appearing " + mostPopular + " times!";
   }
   /* A helper method that instantiates one Person object.
    * @param entry -- one line of the input file.
    */
   private static Person makePerson(String entry)
   {
       String currentName = entry.substring(0,25);
       String currentBurialDate = entry.substring(25, 37);
       String currentAge = entry.substring(37,42);
       //Adding currentBurialPlace in order to meet level 2 requriments.
       String currentResidentialAddress = entry.substring(42);
       return new Person(currentName, currentBurialDate, currentResidentialAddress, currentAge);
   }  
   
   /* Finds and returns the location (the index) of the Person
    * who is the youngest.
    * @param arr -- an array of Person objects.
    */
   public static int locateMinAgePerson(Person[] arr)
   {
       double minAge = 10000000;
       int locateMin = 0;
       //String minPerson = "not working if get this";
       for (int i = 0; i < arr.length; i++) {
           if (Double.parseDouble(arr[i].age) < minAge) {
               minAge = Double.parseDouble(arr[i].age);
               //minPerson = arr[i].toString();
               locateMin = i;
           } else {
               locateMin = locateMin;
           }
       }
       return locateMin;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
    */
   public static int locateMaxAgePerson(Person[] arr)
   {
       double maxAge = 0;
       int locateMax = 0;
       //String maxPerson = "not working if get this";
       for (int i = 0; i < arr.length; i++) {
           if (Double.parseDouble(arr[i].age) > maxAge) {
               maxAge = Double.parseDouble(arr[i].age);
               //maxPerson = arr[i].toString();
               locateMax = i;
           } else {
               locateMax = locateMax;
           }
       }
       return locateMax;
   }        
}

class Person
{
   //// FIELDS ////
   
   /* Declare fields for the name, the burial date, and the age. */
   public String name;
   public String burialDate;
   public String residentialAddress;
   public String age;
   /* @param n -- a String
   ////// CONSTRUCTOR //////
   
   /* @param n -- a String representing a name from the input file.
    *        bd -- a String representing a burial date from the input file.
    *        a -- a String representing an age from the input file.
    *        Level 2 requriment: bp -- String reresenting residential Adress from input file.
    */
   public Person(String n, String bd, String ra, String a)
   {
       name = n;
       burialDate = bd;
       residentialAddress = ra;
       age = calculateAge(a) + "";
   }
   
   //////// METHODS (Person) ////////

   /* Calculates the numerical equivalent of an age in String format.
    * If the String contains a "w" (weeks) or "d" (days), calculates appropriate portion
    * of a year.
      @param a -- a String representing a person's age.
    *calulates age in term of years.
   */
   public double calculateAge(String a)
   {
       double numericalAge;
       if(a.contains("w"))
       {
           int pos = a.indexOf("w");
           double numWeeks = Double.parseDouble(a.substring(0,pos));
           numericalAge = numWeeks/52.0;
       }
       else if(a.contains("d"))
       {
           //write code here!
           int pos = a.indexOf("d");
           double numDays = Double.parseDouble(a.substring(0,pos));
           numericalAge = numDays/365.0;
       }
       else
       {
           //write code here!
           double num = Double.parseDouble(a.substring(0,a.length()));
           numericalAge = num*1.0;
       }
       return numericalAge;
   }
   
   ////////// ACCESSOR METHODS (Person) //////////
   
   /* Write 3 accessor methods for the fields of the Person class.
    * (See the main method in the Cemetery class.)
    */
   /*For Level 2 requriments, I can program an accessor method for
    * burial place and create a 
    * 
    */
   public String getName() {
       return name;
   }
   public String getBurialDate() {
       return burialDate;
   }
   /*adding getResidentialAddress accessor method here for 4th method 
    * that returns residential address in order to meet level 2 requirments
    * Used in conjuction with Method that returns the most popular adress
    * including how many times it appeared. 
   */
   public String getResidentialAddress() {
       return residentialAddress;
   }
   public String getAge() {
       return age;
   }
} 
