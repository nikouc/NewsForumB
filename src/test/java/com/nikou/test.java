package com.nikou;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\JAVAEE\\AK\\AccessKey.csv")));
        String line = null;
        int i = 1;
        while ((line = br.readLine())!=null){
            if (i==2){
                System.out.println(line.split(",")[0]);
                System.out.println(line.split(",")[1]);
            }
                i++;
        }
        br.close();
    }
}
