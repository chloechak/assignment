package assignment;

public class Lakyshaspart {
        //scanner will allow the user to input 
        Scanner reader = new Scanner(System.in); 
        //the program asks the user to input the file they would like to read
        System.out.println("Which file would you like to read?");
        //take the input as string
        String fileChoice = reader.nextLine();
        //takes the file chosen by the user to read 
        File file = new File(fileChoice);


        public static void countFrequency(File file) throws FileNotFoundException{
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
         
         }
         * @param countArray - used to find the percentage of the first digit frequency of numbers (1-9)
         * @param appearingNum - stored the total number of sales which is used to find the digit frequency percentage of each number 
        */
        
        public static void finalPercent() {
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
        * @param countArray - perentage of the first digit frequency of the number 1 is needed to check for fraud 
        */
       public static void fraud(){
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
}
