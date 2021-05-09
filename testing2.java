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

public class testing2 extends Application{ 
    double[] countArray = new double[10];     
    String one = "1";
    String two = "2";
    String three = "3";
    String four = "4";
    String five = "5";
    String six = "6";
    String seven = "7";
    String eight = "8";
    String nine = "9";
    public static void main(String[] args) {
        launch(args);
    }
    @Override 
    public void init() throws FileNotFoundException { 
       // Scanner reader = new Scanner(System.in);
       //this scanner is names scan and it stores the csv file required for the benford calculation
        Scanner scan = new Scanner(new File("sales (2).csv"));
        //this array stores the numbers from 1-9 (10 numbers)
        //double[] countArray = new double[10];     
        //initialize the counter of the numbers appearing
        double appearingNum = 0.0; 
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
    }
    public void finalPercent(double [] countArray, double appearingNum) {
        // this for loop will print out the frequency of every number from 1-9
        for (int i = 1; i<=9; i++){
            countArray[i] = countArray[i] * 100.0 / appearingNum;
            System.out.println(i+"- "+countArray[i]);
            //double x = Math.round((countArray[i]*1000)/1000.0);
            //System.out.println(i+"- "+ x);

        }
        return;
    }
    
    
    public void start(Stage stage) throws Exception {
        
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
        series1.getData().add(new XYChart.Data(one,countArray[1]));
        series1.getData().add(new XYChart.Data(two,countArray[2]));
        series1.getData().add(new XYChart.Data(three,countArray[3]));
        series1.getData().add(new XYChart.Data(four,countArray[4]));
        series1.getData().add(new XYChart.Data(five,countArray[5]));
        series1.getData().add(new XYChart.Data(six,countArray[6]));
        series1.getData().add(new XYChart.Data(seven,countArray[7]));
        series1.getData().add(new XYChart.Data(eight,countArray[8]));
        series1.getData().add(new XYChart.Data(nine,countArray[9]));

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();        
    }
}