/*
Name: Chloe and Lakysha
Date: May 5 2021
Teacher: Mr.Ho
Description: This program will take in a csv file from the user and output benfords law. The percentage of the each first digit frequency will be outputted in the terminal as well as in the graph.
A file called results.csv will store the benford law percentages, for the user to access after.
*/
//import all needed packages to run the program
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Chloe and Lakysha
 * 
 * Description: This class contains the needed program with all methods to run benfords law, the graph, and the results.csv file. 
 *              extends Application allows javaFX to work in the code 
 */
public class benfordLawAssignment extends Application{ 
    //create the array with 10 indexes for 9 digits 
    static double[] countArray = new double[10]; 
    // Initialiize counter    
    static double appearingNum = 0.0; 

    /**
     * Chloe
     * 
     * description: allows the javaFX factor of this program to run and create visual
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    /**
     * Chloe and Lakysha 
     * 
     * description: init is the main for javaFX, all methods go in here and leaves the public static void main empty so that it is able to launch the bar graph
     * @param - the parameters allows user to choose the file they want to be readed and used in this program
     * @throws - allows init to read file 
     */
    @Override
    // init is javaFX's main 
    public void init() throws FileNotFoundException { 
        //scanner will allow the user to input 
        Scanner reader = new Scanner(System.in); 
        //the program asks the user to input the file they would like to read
        System.out.println("Which file would you like to read?");
        //take the input as string
        String location =reader.nextLine();
        //convert the string into a file
        File file = new File(location);

        //the method that checks for fraud is placed here in the main so the user could check for fraud
        checkFraud(countArray,file);
        //benford law percentages will be displayed through this method
        finalPercent(countArray, appearingNum);
        fraud(countArray);
        // method that puts legend into resuls.csv
        Results(countArray);
        
   
    }
   /**
     * Lakysha
     * 
     * @param- The parameters of this method includes the countArray(that stores the frequencies) and the file that the user inputted
     * @throws- the file not found exception is used to sucessfully run the method and terminate it if the exception occurs
     * Description- This method will calculate the frequencies of each number (1-9). The method will also count every number of the file so the finl percentages can be displayed
     *
     */
    public static void checkFraud(double [] countArray, File file) throws FileNotFoundException{
       //this scanner is names scan and it stores the user inputted  file required for the benford calculation
       Scanner scan = new Scanner(file);
     
       //while the file has the next line read it and do the calculations 
       while (scan.hasNext()) {
        //this delimeter will allow the file to be read after the comma on each line
       scan.useDelimiter(",");

           //read the next number but convert the char into an int 
           int nextNum = Character.getNumericValue(scan.next().charAt(0));            
            
            //add a counter so it counts the number of times a number appears
           appearingNum++;
           // this counter will update the frequency of a number in its array  
           for(int a=1; a<10; a++){
               //if a equals the first digit of an number update the frequency
               if(a==nextNum){
                   //going through the array, if a is equal to the first number add 1 to its frequency
                   countArray[a]++;  
               }
           } 
        }   
       //}   
    }
    /** 
     * Lakysha
     * 
     *  @param- The parameters are the array that stores the frequency of the numbers and the counter that counts the number of numbers in the file
     *  @param scale = creates integer 10 to the power of 2 so that the percents can round to one decimal place
     * description- This method will display the percentages of the first digit of each number. The method will also tell the user whether fraud has occured or not
    */
    
    public static void finalPercent(double [] countArray, double appearingNum) {
        // this for loop will print out the frequency of every number from 1-9
        for (int i = 1; i<=9; i++){
            //the countArray will equal the percentage of the frequency of each number (1-9)
            countArray[i] = countArray[i] * 100.0 / appearingNum;
            // 10 to the power of 1 (for one decimal place)
            int scale = (int) Math.pow(10, 1);
            // elements in the array is multiplied by 10 to the power of 1, rounded and then divided by 10 to the power of 1 
            countArray[i]=(double) Math.round(countArray[i]* scale) / scale;
            //print out the first digit frequencies with a percentage sign at the end
            System.out.println(countArray[i]+"%");
        }
     
    }
    /**
     * @param- The paramaters include the countArray because it will be needed to get the 1st digit of the array
     * Description: This method will inform the user if fraud did/did not occur after the file goes through benfords law
     */
    public static void fraud(double [] countArray){
        //if the digit "1" is less than 32 and greater than 29 do the task
        if(29<=countArray[1]&&countArray[1]<=32){
        //if the statement is true output that fraud didnt occur
            System.out.println("Fraud likely did not occur");
        }
       //if the statement is false output fraud did occur
        else{
            System.out.println("Fraud did occur");
            }
    }
    /**
     * Chloe 
     * 
     * Descripion: javaFX start method that sets the scene and stages the visual (bar graph) using the percents given from method finalPecent
     * @param - creates the xaxis sections for the different first digit frequencies 
     * @throws - throws exceptions to allow start method to run and create visual
     */

    public void start(Stage stage) throws Exception {
        // title of stage 
        stage.setTitle("bar graph");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
        new BarChart<String,Number>(xAxis,yAxis);
        // title of bar graph
        bc.setTitle("Benford's Law Distribution First Digit");
        // x axis label
        xAxis.setLabel("digit");  
        // y axis label     
        yAxis.setLabel("percent");

        // series are the number of bars in one section, in this case there's only one 
        XYChart.Series series1 = new XYChart.Series();
        // Legend for the colour of the bar 
        series1.setName("digit frequency");       
        // These are for each bar 
                                         // (x, data to determine the height of the bar)
        series1.getData().add(new XYChart.Data("1",countArray[1]));
        series1.getData().add(new XYChart.Data("2",countArray[2]));
        series1.getData().add(new XYChart.Data("3",countArray[3]));
        series1.getData().add(new XYChart.Data("4",countArray[4]));
        series1.getData().add(new XYChart.Data("5",countArray[5]));
        series1.getData().add(new XYChart.Data("6",countArray[6]));
        series1.getData().add(new XYChart.Data("7",countArray[7]));
        series1.getData().add(new XYChart.Data("8",countArray[8]));
        series1.getData().add(new XYChart.Data("9",countArray[9]));

        // scene scale (how big the bar graph is)
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();  
              
    }

    /**
     * Chloe 
     * 
     * Description: this method prints the legend of the first digit frequency bar graph into the results.csv 
     * @param - chooses file and writes in it 
     */
    public static void Results (double [] countArray) {
        try {
            // data will be put in results.csv
            // true prevents it from being overwritten
            FileWriter outFile = new FileWriter ("results.csv");
            // Tells program to print in results.csv
            PrintWriter out = new PrintWriter(outFile);

            // Puts each digit frequency from the array into the file using for loop
            for (int i = 1;i < 10;i++) {
                out.println(i + "=" + countArray[i] + "%");
            }
            out.flush();
            
            // prints done when legend is printed into the results.csv
            System.out.println("Done");
            
            // closes the PrintWriter
            out.close(); 
        } 

        // Prints if an error has occured
        catch (Exception E) {
            System.out.println("error");
     
        }
    }
}