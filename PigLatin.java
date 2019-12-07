// Name: R1-01
// Date: 09-09-2019
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
     // part_1_using_pig();
     part_2_using_piglatenizeFile();
      
      /*  extension only    */
      // String str = pig("What!?");
      // System.out.print(str + "\t\t" + pigReverse(str));
      // str = pig("{(Hello!)}");
      // System.out.print("\n" + str + "\t\t" + pigReverse(str));
      // str = pig("\"McDonald???\"");
      // System.out.println("\n" + str + "  " + pigReverse(str));
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static int vowelIndex = -1;
   public static String start = "";
   public static String stop = "";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
   
      //remove and store the beginning punctuation 
      start = "";
      stop = "";
      vowelIndex = -1;
      for(int x = 0; x < punct.length(); x ++)
      {
         while(s.charAt(0)==punct.charAt(x))
         {
            start = start + String.valueOf(s.charAt(0));
            s.replace(String.valueOf(s.charAt(0)), "");
            s = s.substring(1);
            x = 0;
         }
      
      }
           
      for(int x = 0; x < punct.length(); x ++)
      {
         while(s.charAt(s.length()-1)==punct.charAt(x))
         {
            if(s.indexOf(String.valueOf(s.charAt(s.length()-1))) < s.indexOf(stop))
               stop = stop + String.valueOf(s.charAt(s.length()-1));
            if(s.indexOf(String.valueOf(s.charAt(s.length()-1))) > s.indexOf(stop))
               stop =  String.valueOf(s.charAt(s.length()-1)) + stop;
         
            s.replace(stop, "");
            s = s.substring(0, s.length()-1);
            x = 0;
         }
      
      }   
      //remove and store the ending punctuation 
         
         
      //START HERE with the basic case:
      //find the index of the first vowel
      for(int c = s.length() -1; c > -1; c--)
      {
         for(int x = 0; x < vowels.length(); x ++)
         {
            if(s.charAt(c) == vowels.charAt(x)) 
            {
               vowelIndex = c;
            }
            
         }
      }
      if(vowelIndex == 0)
         return(start + s + "way" + stop);
      if((s.indexOf("y") != 0 && s.indexOf("y") < vowelIndex && s.indexOf("y") != -1) || s.indexOf("y") < vowelIndex && (s.contains("y") && s.indexOf("y") != 0 && s.indexOf("y") != -1))     
         vowelIndex = s.indexOf("y");
     
      if(s.indexOf("q") +1 == vowelIndex || s.indexOf("Q") +1 == vowelIndex)
      {
         int r = vowelIndex;
         for(int c = s.length()-1; c > r; c--)
         {
            for(int x = 0; x < vowels.length(); x ++)
            {
               if(s.charAt(c) == vowels.charAt(x) ) 
               {
                  vowelIndex = c;
               }
            
            }
         }
      }
   
      if(vowelIndex == -1)
         return "**** NO VOWEL *****";
   
      if(Character.isUpperCase(s.charAt(0)))
      {
         char l = Character.toLowerCase(s.charAt(0));
         char p = Character.toUpperCase(s.charAt(vowelIndex));
         return( start + p + s.substring(vowelIndex + 1) + l + s.substring(1, vowelIndex) + "ay" + stop);
      }
      return( start + s.substring(vowelIndex) + s.substring(0, vowelIndex) + "ay" + stop);
   
   
   
   
      
      //     y is a vowel if it is not the first letter
      //     qu
      
      
      //if no vowel has been found
      
      //is the first letter capitalized?
      
      
      //return the piglatinized word 
      
      
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      int count = 1;

      String temp = "";
      int spaceindex = 0;
      int start = 0;
      String finish = new String("");
      while(infile.hasNextLine() == true)
      {
         finish = "";
         String piggy = infile.nextLine();
         String[] words = piggy.split(" ");
         for(int x = 0; x < words.length; x ++)
         {
            temp = pig(words[x]);
         
            finish = finish + " " + temp;
         }
      
         outfile.println(finish);
      }

   	//process each word in each line
      
   
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
  /* public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
         
         
   }*/
}
