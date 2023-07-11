import Bean.Bean_T_YVersion;
import Dao.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Dao_FindGroup daoGroup = new Dao_FindGroup();
        Method_web_Stop daoDown = new Method_web_Stop();
        for (int i = 0; i < 4371; i++) {
            String versionIDList ="";
            String versonID ="";
            ArrayList<Bean_T_YVersion> beanList = daoGroup.Methoy_FindGroup("T_YVersion", String.valueOf(i));
            for (int j = 0; j < beanList.size(); j++) {
                versonID = beanList.get(j).get_C_VersionID();
                versionIDList +=versonID+",";
            }
            versionIDList = versionIDList.substring(0, versionIDList.length() - 1);
            //System.out.println(versionIDList);
            String V_url = "https://car.yiche.com/chexingduibi/?carIds="+versionIDList;
            String JsonConent = daoDown.Method_FindYCWB(V_url);
            System.out.println(V_url);
            try{
//                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:/A_YicheAllConfig/"+ i +"_Config.text", true), 662148);//165537
//                bufferedOutputStream.write(JsonConent.getBytes());
//                bufferedOutputStream.close();
            }catch (Exception ex){
                System.out.println(ex.toString());
            }
        }
        System.out.println("Hello world!");
    }
}