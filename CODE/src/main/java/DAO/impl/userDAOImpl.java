package DAO.impl;
import DAO.userDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * Title: userDAOImpl.java
 * Description: This class is the implementation for the interface of user data access objects.
 * Copyright: Copyright (c) 2021
 * @since April 7 2021
 * @author Wangbo Li
 *
 */
public class userDAOImpl implements userDAO {

    public String[] getMembershipPrivileges(String MemberLevel){
        String filepath = " src/main/resources/files/membershipPrivileges.csv";
        try {
            String line = null;
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            while((line = reader.readLine()) != null){
                String[] value = line.split(",");
                if (value[0].equals(MemberLevel)){
                    return value;}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void Save(String[] info){
        String filepath = "src/main/resources/files/userInfo.csv";
        try {
            BufferedWriter Writer = new BufferedWriter(new FileWriter(filepath));
            Writer.write(info[0]);
            for(int i=1;i<11;i++){
                Writer.write(","+info[i]);
            }
            Writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getInfo(String userId) {
        String filepath =  "src/main/resources/files/userInfo.csv";
        String userName = "";
        String balance = "";
        String membershipLevel = "";
        String email = "";
        String address = "";
        String moblienum = "";
        String gender = "";
        String age = "";
        String category = "";
        String description = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] item = line.split(",");
                if (item[0].equals(userId)) {
                    userName = item[1];
                    address = item[2];
                    gender = item[3];
                    moblienum = item[4];
                    age = item[5];
                    category = item[6];
                    email = item[7];
                    description = item[8];
                    balance = item[9];
                    membershipLevel= item [10];
                    break;
                }
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{userId,userName,address,gender,moblienum,age,category,email,description,balance,membershipLevel};
    }

}