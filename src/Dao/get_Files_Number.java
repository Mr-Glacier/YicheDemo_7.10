package Dao;

import java.io.File;

public class get_Files_Number {

//这是一个根据
    public static void main(String[] args) {
        System.out.println(Method_Files_number("F:/A_YicheAllConfig/"));
    }
    public static int Method_Files_number(String path) {
            File folder = new File(path);
            File[] list = folder.listFiles();
            int FilesNumber = 0;
            if (list != null) {
                FilesNumber = list.length;
                //System.out.println(list.length);
            }else {
                System.out.println("路径下没有文件: "+path);
            }
        return FilesNumber ;
    }
}
