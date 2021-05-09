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

public class benfordlaw extends Application{ 
    public static void main(String[] args) throws FileNotFoundException { 
       // Scanner reader = new Scanner(System.in);
       //this scanner is names scan and it stores the csv file required for the benford calculation
        Scanner scan = new Scanner(new File("sales (2).csv"));
        //this array stores the numbers from 1-9 (10 numbers)
        int[] countArray = new int[10];     
        //initialize the counter of the numbers appearing
        int appearingNum = 0; 
        //while the file has next perform the tasks 
        while (scan.hasNext()) {
            //read the next digit but convert the 4th character into an int 
            int nextNum = Character.getNumericValue(scan.next().charAt(4));       
             
             //add a counter so it counts the number of times a number appears
            appearingNum++;
            // this counter will update the frequency of a number in its array  
            for(int a=1; a<10; a++){
                if(a==nextNum){
                countArray[a]++;  
              }
              }    
                 
        }
        //display the method that contains the final percentages
        finalPercent(countArray,appearingNum); 
        launch(args);
   
   
    }
    public static void finalPercent(int [] countArray, int appearingNum) {
             // this for loop will print out the frequency of every number from 1-9
       for (int i = 1; i<=9; i++){
            double percent = countArray[i] * 100.0 / appearingNum;
            
        }
    }
     
    @Override
    public void start(Stage stage) throws Exception {
        String one = "1";
        String two = "2";
        String three = "3";
        String four = "4";
        String five = "5";
        String six = "6";
        String seven = "7";
        String eight = "8";
        String nine = "9";
        stage.setTitle("bar graph");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
        new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Benford's Law Distribution First Digit");
        xAxis.setLabel("digit");       
        yAxis.setLabel("percent");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("digit frequency");       
        series1.getData().add(new XYChart.Data(one, 3));
        series1.getData().add(new XYChart.Data(two, 4));
        series1.getData().add(new XYChart.Data(three,6));
        series1.getData().add(new XYChart.Data(four,8));
        series1.getData().add(new XYChart.Data(five, 10));
        series1.getData().add(new XYChart.Data(six, 9));
        series1.getData().add(new XYChart.Data(seven, 5));
        series1.getData().add(new XYChart.Data(eight, 3));
        series1.getData().add(new XYChart.Data(nine, 6));

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();        
    }
}
