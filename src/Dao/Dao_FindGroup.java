package Dao;

import Bean.Bean_T_YVersion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class Dao_FindGroup {
    public ArrayList<Bean_T_YVersion> Methoy_FindGroup(String taleName,String groupid){
        ArrayList<Bean_T_YVersion> resultsList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=T_work","sa","7777");
            Statement statement = connection.createStatement();
            String sql = "SELECT*FROM "+taleName+"  where C_分组 = "+groupid;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Bean_T_YVersion beanTYVersion = new Bean_T_YVersion();
                beanTYVersion.set_C_VersionID(resultSet.getString(3));
                beanTYVersion.set_C_分组(resultSet.getString(7));
                beanTYVersion.set_C_分组是否被下载(resultSet.getString(8));
                resultsList.add(beanTYVersion);
            }
            resultSet.close();
            statement.close();
            connection.close();

        }catch (Exception ex){
            System.out.println(ex);
        }
        return resultsList;
    }
}
