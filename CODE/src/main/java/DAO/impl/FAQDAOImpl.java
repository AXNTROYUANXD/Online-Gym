package DAO.impl;

import DAO.FAQDAO;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * Title: FAQDAOImpl.java
 * Description: This class is for answer the question  for the interface of question number access objects.
 * Copyright: Copyright (c) 2021
 * @since April 7 2021
 * @author Wangbo Li
 *
 */
public class FAQDAOImpl implements FAQDAO {
    public String FAQ(String questionNumber){
        String filepath = "files/FAQ.csv";
        String answer= "";
        try {
            String line = null;
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            reader.readLine(); //first line is the header information
            while((line = reader.readLine()) != null){
                String[] value = line.split(",");

                if (value[0].equals(questionNumber)){
                    answer = value[1];}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

}