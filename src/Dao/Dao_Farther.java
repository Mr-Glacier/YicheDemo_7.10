package Dao;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.runtime.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_Farther {
    private Connection conn = null;
    private Statement stmt = null;

    private String driverName;
    private String UserName;
    private String UserPass;
    private String Url;

    private String pricaryKey;
    private String tableName;

    public Dao_Farther(String tableName, int chose) {
        try {
            this.tableName =tableName;
            //读取Json文件
            String path = "F:/Workplace/YicheDemo_7.10/Config.json";
            File file = new File(path);
            BufferedReader re = new BufferedReader(new FileReader(file));
            StringBuffer JsonConnect = new StringBuffer();
            String text = "";
            while ((text = re.readLine()) != null) {
                JsonConnect.append(text);
            }
            re.close();

            JSONObject jsonObject = JSONObject.parseObject(JsonConnect.toString());
            String packageName = jsonObject.getString("EntityPackage");
            JSONArray jsonArray = jsonObject.getJSONArray("DBList");
            JSONObject configDB = jsonArray.getJSONObject(chose);
            this.driverName = configDB.getString("DBDriver");
            this.Url = configDB.getString("DBConnectionString");
            this.UserName = configDB.getString("DBUserName");
            this.UserPass = configDB.getString("DBUserPass");

            JSONArray EntityArry = jsonObject.getJSONArray("EntityList");
            JSONObject ConfigEntity = EntityArry.getJSONObject(chose);
            this.pricaryKey = ConfigEntity.getString("EntityPrimaryKey");
            String EntityName = ConfigEntity.getString("EntityName");

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void Method_CreateObject() {
        try {
            Class.forName(driverName);
            if (null == conn || conn.isClosed()) {
                conn = DriverManager.getConnection(Url, UserName, UserPass);
            }
            if (null == stmt || stmt.isClosed()) {
                stmt = conn.createStatement();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void Method_I_U_D(String sql) {
        try {
            Method_CreateObject();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void Method_Delete(String id) {
        String sql = "DELETE FROM " + tableName + "WHERE " + pricaryKey + "= " + id;
        Method_I_U_D(sql);
    }

    public void Method_Insert2(Object object) {
        try {
            Class I = object.getClass();
            Method[] methods = I.getDeclaredMethods();
            String columenList = "";
            String valueList = "";
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().equals("get_" + pricaryKey)) {
                    continue;
                }
                if (methods[i].getName().startsWith("get")) {
                    String columenName = methods[i].getName().replace("get_", "");
                    columenList += columenName + ",";
                    String value = methods[i].invoke(object).toString();
                    if (methods[i].getReturnType() == String.class) {
                        valueList += "'" + value + "'" + ",";
                    } else {
                        valueList += value + ",";
                    }
                }
            }
            columenList = columenList.substring(0, columenList.length() - 1);
            valueList = valueList.substring(0, valueList.length() - 1);
            String sql = "Insert into " + this.tableName + "(" + columenList + ")values(" + valueList + ")";
            System.out.println(sql);
            Method_I_U_D(sql);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public void Method_Insert(Object object) {
        try {
            Class I = object.getClass();
            Method[] methods = I.getDeclaredMethods();
            String columenList = "";
            String valueList = "";
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().equals("get_" + pricaryKey)) {
                    continue;
                }
                if (methods[i].getName().startsWith("get")) {
                    String columenName = methods[i].getName().replace("get_", "");
                    columenList += columenName + ",";
                    String value = methods[i].invoke(object) == null ? "-" : methods[i].invoke(object).toString();
                    if (methods[i].getReturnType() == String.class) {
                        valueList += "'" + value + "'" + ",";
                    } else {
                        valueList += value + ",";
                    }
                }
            }
            columenList = columenList.substring(0, columenList.length() - 1);
            valueList = valueList.substring(0, valueList.length() - 1);
            String sql = "Insert into " + this.tableName + "(" + columenList + ")values(" + valueList + ")";
            System.out.println(sql);
            Method_I_U_D(sql);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
