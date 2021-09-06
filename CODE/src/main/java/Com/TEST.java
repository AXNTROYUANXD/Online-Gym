package Com;

import DAO.impl.VideoDAOIml;
import javafx.application.Application;

import java.io.IOException;

public class TEST {
    public static void main(String[] args) {
        Application.launch(Main.class, args);//第一种方法,适用于启动另外一个继承Application的class（将两个class关联起来）
        //Application.launch(args);//第二种方法适用于start方法和main在一个java文件中
        //launch(args);
    }
}
