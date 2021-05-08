import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class benfordlaw { 
    public static void main(String[] args) throws FileNotFoundException { 
       // Scanner reader = new Scanner(System.in);
       //this scanner is names scan and it stores the csv file required for the benford calculation
        Scanner scan = new Scanner(new File("sales.csv"));
        //this array stores the numbers from 1-9 (10 numbers)
        int[] countArray = new int[10];     
        //initialize the counter of the numbers appearing
        int appearingNum = 0; 
        //while the file has next perform the tasks 
        while (scan.hasNext()) {
            //read the next digit but convert the 4th character into an int 
            int nextNum = Character.getNumericValue(scan.next().charAt(4));       
            int digit = Digit(nextNum);   
             //add a counter so it counts the number of times a number appears
            appearingNum++;
            // this counter will update the frequency of a number in its array  
            countArray[digit]++;      
                 
        }
        //display the method that contains the final percentages
        finalPercent(countArray,appearingNum); 
   
   
    }
    public static void finalPercent(int [] countArray, int appearingNum) {
             // this for loop will print out the frequency of every number from 1-9
       for (int i = 1; i<=9; i++){
            double percent = countArray[i] * 100.0 / appearingNum;
            System.out.println(i+"- "+percent);
          
     }
    }
       public static int Digit(int num) {
         while (num > 9) {
            num = num / 10;
          }
        return num;
    }
}
