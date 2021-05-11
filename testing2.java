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
import java.sql.ResultSet;

public class testing2 extends Application{ 
    //create the array with 10 indexes for 9 digits 
    static double[] countArray = new double[10];     
    static double appearingNum = 0.0; 

    // this main is only used to launch the javaFX visual made
    public static void main(String[] args) {
        launch(args);
        
    }
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
        Results(countArray);
        
   
    }
  /* @param- The paramters of this method includes the countArray(that stores the frequencies) and the file that the user inputted
     Description- This method will calculate the frequencies of each number (1-9). The method will also count every number of the file so the finl percentages can be displayed
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
                   countArray[a]++;  
               }
           } 
        }   
       //}   
    }
    /*
    @param- The parameters are the array that stores the frequency of the numbers and the counter that counts the number of numbers in the file
    description- This method will display the percentages of the first digit of each number. The method will also tell the user whether fraud has occured or not
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
            System.out.println(countArray[i]+"%");
        }
        if(29<countArray[1]&&countArray[1]<32){
            System.out.println("Fraud likely did not occur");
        }
        else{
            System.out.println("Fraud did occur");
        }
    }
    
    // start method to create visual
    public void start(Stage stage) throws Exception {
        // sections for x axis
        String one = "1";
        String two = "2";
        String three = "3";
        String four = "4";
        String five = "5";
        String six = "6";
        String seven = "7";
        String eight = "8";
        String nine = "9";
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

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("digit frequency");       
        // These are for each bar 
                                         // (x, data to determine the height of the  bar)
        series1.getData().add(new XYChart.Data(one,countArray[1]));
        series1.getData().add(new XYChart.Data(two,countArray[2]));
        series1.getData().add(new XYChart.Data(three,countArray[3]));
        series1.getData().add(new XYChart.Data(four,countArray[4]));
        series1.getData().add(new XYChart.Data(five,countArray[5]));
        series1.getData().add(new XYChart.Data(six,countArray[6]));
        series1.getData().add(new XYChart.Data(seven,countArray[7]));
        series1.getData().add(new XYChart.Data(eight,countArray[8]));
        series1.getData().add(new XYChart.Data(nine,countArray[9]));

        // scene scale
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();  
              
    }

    public static void Results (double [] countArray) {
        try {
            // data will be put in results.csv
            // true prevents it from being overwritten
            FileWriter outFile = new FileWriter ("results.csv", true);
            // Tells program to print in results.csv
            PrintWriter out = new PrintWriter(outFile);

            // Puts digit frequency into the file 
            for (int i = 1;i < 10;i++) {
                out.println(i + "=" + countArray[i] + "%");
            }
            out.flush();
            
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