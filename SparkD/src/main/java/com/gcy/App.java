package com.gcy;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        int[] val = {400,1,2,3,4,5,6};

        int su = 12 * val[0];

        int[] x = {100,100,100,100,100,100};
        for(int j =100;j<250;j++){
            for (int i =0;i <= 6;i++){


                int sum = val[1]*x[0]+val[2]*x[1]+val[3]*x[2]+val[4]*x[3]+val[5]*x[4]+val[6]*x[5];
                if(su<=sum){
                    System.out.println(x[0]+"-"+x[1]+"-"+x[2]+"-"+x[3]+"-"+x[4]+"-"+x[5]);
                }
                if(i<6){
                    x[i]+=1;
                }
            }
        }

    }
}
