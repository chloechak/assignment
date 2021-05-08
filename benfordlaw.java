// hello
// hello
//new change:)
//another change

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * benfordlaw
 */
class benfordlaw {
    public static void main(String[] args)  throws FileNotFoundException {
            // Scanner reader = new Scanner(System.in);
            //this scanner is names scan and it stores the csv file required for the benford calculation
             Scanner scan = new Scanner(new File("sales.csv"));
             //this array stores the numbers from 1-9 (10 numbers)
             int[] countArray = new int[10];     
              //initialize the counter
             int appearingNum = 0; 
    }
    
}