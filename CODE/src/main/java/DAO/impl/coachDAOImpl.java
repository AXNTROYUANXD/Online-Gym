package DAO.impl;
import DAO.coachDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class coachDAOImpl implements coachDAO {
    public String[] getInfo(String coachId) {
        String filepath = "src/main/resources/files/coachInfo.csv";
        String coachName = "";
        String address = "";
        String gender= "";
        String moblienum = "";
        String age = "";
        String mark = "";
        String email = "";
        String category = "";
        String description = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] item = line.split(",");
                if (item[0].equals(coachId)) {
                    coachName = item[1];
                    address = item[2];
                    gender = item[3];
                    moblienum = item[4];
                    age = item[5];
                    mark = item[6];
                    email = item [7];
                    category = item[8];
                    description = item[9];
                    break;
                }
                line = reader.readLine();
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{coachId, coachName, address,gender,moblienum,age,mark,email,category,description};
    }

    public void editCoachInfo(String coachName, String address,String gender,String moblienum,String age,String mark,String email,String category,String description){
        //写入文件。
        String filepath =  "src/main/resources/files/coachInfo.csv";
        try {
            BufferedWriter Writer = new BufferedWriter(new FileWriter(filepath));
            Writer.write("123,");
            Writer.write(coachName+",");
            Writer.write(address+",");
            Writer.write(gender+",");
            Writer.write(moblienum+",");
            Writer.write(age+",");
            Writer.write(mark+",");
            Writer.write(email+",");
            Writer.write(category+",");
            Writer.write(description);
            Writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
