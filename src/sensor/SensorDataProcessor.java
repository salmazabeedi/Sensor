/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sensor;


//added the imports
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author salma
 *//////////
public class SensorDataProcessor {

// Senson data and limits.
    public double[][][] data;
    public double[][] limit;
// constructor

//no var type double               

    //salma zabeedi - fixed the constructor
    public SensorDataProcessor(double[][][] data, double[][] limit) {
        this.data = data;
        this.limit = limit;
    }

// calculates average of sensor data
    
    //edit by Renad Alharbi 
    private double average(double[] array) {
        if (array.length==0){
            return 0;
        }
        
        double average = 0; //renamed this from val to avg
        for (double value : array) {
            average += value;
        }
        return average / array.length;
    }
// calculate data
// edit in place 
    //jj
     
    public void calculate(double d){
        int i, j, k; //waad mnyawi - removed = 0 because it was declared later in the loop   
        double[][][] data2 = new double[data.length][data[0].length][data[0][0].length];
        BufferedWriter out = null; //shadan yousef - to ensure that the variable has a valid initial value
        // Write racing stats data into a file
        try {
            out = new BufferedWriter(new FileWriter("RacingStatsData.txt"));
            for (i = 0; i < data.length; i++) {
                for (j = 0; j < data[0].length; j++) {
                    for (k = 0; k < data[0][0].length; k++) {
                        data2[i][j][k] = data[i][j][k] / d
                                - Math.pow(limit[i][j], 2.0);
                        if (average(data2[i][j]) > 10 && average(data2[i][j])
                                < 50) {
                            break;
                        } else if (Math.max(data[i][j][k], data2[i][j][k])
                                > data[i][j][k]) {
                            break;
                        } else if (Math.pow(Math.abs(data[i][j][k]), 3)
                                < Math.pow(Math.abs(data2[i][j][k]), 3)
                                && average(data[i][j]) < data2[i][j][k] && (i + 1)
                                * (j + 1) > 0) {
                            data2[i][j][k] *= 2;
                        } else {
                            continue;
                        }
                    }
                }
            }
            for (i = 0; i < data2.length; i++) {
                for (j = 0; j < data2[0].length; j++) {
                    out.write(data2[i][j] + "\t");
                }
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e); /**waad mnyawi - changed = to : 
             to ensures consistency with the error message format used 
             throughout the code **/
        } // lama Khalil
    }
    

}