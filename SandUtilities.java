
import java.awt.Color;

public class SandUtilities
{
  //pre : c!= null
  //post: returns the inverted color from the one sent as c
   public static Color invert(Color c)
   {
       return new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
   }
   
   //pre:   m!= null
   //post:  for each non-null element of m, changes it to its inverted color
   //       skips any color with the value skip1 and skip2, leaving them unchanged
   public static void invertColors(Color[][]m, Color skip1, Color skip2)
   {
       Color inverted;
       //Color piece; // = "";
       for (int i = 0; i < m.length; i++)
       {
           for (int j = 0; j < m[0].length; j++)
           {
               if (m[i][j] != null && (m[i][j] != skip1 && m[i][j] != skip2))
               {
                    inverted = invert(m[i][j]);
                    m[i][j] = inverted;
               
               }
           }
           
       }
   }
   
   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  flips the array upside down
   public static void flipUpsideDown(Object[][]m)
   {// = new Object[m.length];
       int type;
       for (int j = 0; j < m.length; j++)
       {
           if (j <= (m.length / 2))
               for (int k = 0; k< m.length; k++)
               {
                   Object save = m[j][k];
                   m[j][k] = m[m.length - 1 - j][k];
                   m[m.length - 1 - j][k] = save;
               }
       }
   }
   
   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  rotates the array 90 degrees to the left
   public static void rotateLeft(Object[][] m) 
   {
       //Linear agebra coming in handy because of matrices
       //We have to transpose the matrice by making each
       //row a collum
       for(int i=0;i<m.length;i++)  
        {  
            for(int j=i;j<m.length;j++)  
            {  
                Object temp = m[i][j];  
                m[i][j] = m[j][i];  
                m[j][i] = temp;  
            }  
        } 
       //Since we are going left  we swap the outer rows
       //and go inwards
       //so if 3x3 matrices we switch top row and bottom row
       
       for (int i = 0; i < m.length; i++)
       {
            int min = 0;
            int max = m.length -1;
            while (min < max)
            {
                Object temp = m[min][i];
                m[min][i] = m[max][i];
                m[max][i] = temp;
                min++;
                max--;
            }
       }
        //and Wallah we get a rotated matric
   }

   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  rotates the array 90 degrees to the right
   public static void rotateRight(Object[][] m)
   {
       //linear algebra for the win!!!!1
       //transpose 2-d array (MAtrices!!!)
       //same thing for left rotate but
       for(int i=0;i<m.length;i++)  
        {  
            for(int j=i;j<m.length;j++)  
            {  
                Object temp = m[i][j];  
                m[i][j] = m[j][i];  
                m[j][i] = temp;  
            }  
        }   
        //instead of swapping rows we swap collums
       for (int i = 0; i < m.length; i++)
       {
            int min = 0;
            int max = m.length -1;
            while (min < max)
            {
                Object temp = m[i][min];
                m[i][min] = m[i][max];
                m[i][max] = temp;
                min++;
                max--;
            }
       }
   }      
}