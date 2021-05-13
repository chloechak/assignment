    /**
     * Chloe
     * 
     * description: allows the javaFX factor of this program to run and create visual
     */
    public static void main(String[] args) {
        launch(args);
        
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
