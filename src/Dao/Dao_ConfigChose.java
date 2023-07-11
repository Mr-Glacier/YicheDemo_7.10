package Dao;

import Bean.Bean_T_YChose;
import Bean.Bean_YiCheConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class Dao_ConfigChose {
    public ArrayList<Bean_YiCheConfig> Method_Json() {
        try {
            for (int i = 0; i < 4371; i++) {
                String path = "F:/A_YicheAllConfig/" + i + "_Config.text";
                File file = new File(path);
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuffer SB = new StringBuffer();
                String text = "";
                while ((text = br.readLine()) != null) {
                    SB.append(text);
                }
                br.close();
                JSONObject ConfigItems1 = JSONObject.parseObject(SB.toString());

                JSONArray configData = ConfigItems1.getJSONArray("data");
                if (!(configData.size() == 0)) {
                    for (int j = 0; j < configData.size(); j++) {
                        JSONObject ConfigA = configData.getJSONObject(j);
                        String titeName = ConfigA.getString("name");
                        //System.out.println(titeName);
                        if (titeName.equals("选配包")) {
                            System.out.println("00000000");
                            JSONArray ConfigB = ConfigA.getJSONArray("items");
                            for (int k = 0; k < ConfigB.size(); k++) {
                                JSONObject ConfigC = ConfigB.getJSONObject(k);
                                String ChoseName = ConfigC.getString("name");
                                String ChoseContent = ConfigC.getString("desc");
                                JSONArray ConfigD = ConfigC.getJSONArray("paramValues");
                                for (int l = 0; l < ConfigD.size(); l++) {
                                    JSONObject ConfigE = ConfigD.getJSONObject(l);
                                    String PID = ConfigE.getString("id");

                                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                    //System.out.println(PID);
                                    JSONArray ConfigF = ConfigE.getJSONArray("subList");
                                    for (int m = 0; m < ConfigF.size(); m++) {
                                        JSONObject ConfigH = ConfigF.getJSONObject(m);
                                        String value = ConfigH.getString("value");
                                        String price = ConfigH.getString("price");
                                        String desc = ConfigH.getString("desc");
                                        if (desc== null){
                                            desc = "-";
                                        }
                                        if (price == null){
                                            price = "-";
                                        }
                                        Bean_T_YChose bean_t_yChose = new Bean_T_YChose();
                                            bean_t_yChose.set_C_PID(PID);
                                            bean_t_yChose.set_C_选配包名称(ChoseName);
                                            bean_t_yChose.set_C_选配包内容(ChoseContent);
                                            bean_t_yChose.set_C_选配包状态(value + desc);
                                            bean_t_yChose.set_C_选配包价格(price);
                                            System.out.println(PID);
                                            System.out.println(ChoseName);
                                            System.out.println(value+desc);
                                            System.out.println(ChoseContent);
                                            System.out.println(price);
                                        Dao_Farther dao_farther = new Dao_Farther("T_YChoseConfig",1);
                                        dao_farther.Method_Insert2(bean_t_yChose);
                                        System.out.println("完成一次插入");
                                    }
                                }
                            }
                        } else {
                            System.out.println("不 存 在 选 配 包");
                        }
                    }


                    //Dao_Farther daoConfig = new Dao_Farther("T_YiCheAllConfig", 0);

                } else {
                    System.out.println("error:" + path);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:/A_errorIns/" + i + "_error.text", true), 662148);//165537
                    bufferedOutputStream.write(path.getBytes());
                    bufferedOutputStream.close();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
