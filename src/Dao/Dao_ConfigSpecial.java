package Dao;

import Bean.Bean_T_YChose;
import Bean.Bean_T_YSpecialConfig;
import Bean.Bean_YiCheConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class Dao_ConfigSpecial {
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
                        if (titeName.equals("特色配置")) {
                            JSONArray ConfigB = ConfigA.getJSONArray("items");
                            for (int k = 0; k < ConfigB.size(); k++) {
                                JSONObject ConfigC = ConfigB.getJSONObject(k);
                                String SpecialName = ConfigC.getString("name");
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
                                        String desc = ConfigH.getString("desc");
                                        String price = ConfigH.getString("price");
                                        if ( null== price ){
                                            price = "-";
                                        }
                                        if (null == desc){
                                            desc = "-";
                                        }
                                        Bean_T_YSpecialConfig bean_t_ySpecialConfig = new Bean_T_YSpecialConfig();
                                        bean_t_ySpecialConfig.set_C_PID(PID);
                                        bean_t_ySpecialConfig.set_C_特色配置名称(SpecialName);
                                        bean_t_ySpecialConfig.set_C_特色配置状态(value + desc);
                                        bean_t_ySpecialConfig.set_C_特色配置价格(price);
                                        System.out.println(PID);
                                        System.out.println(SpecialName);
                                        System.out.println(value);
                                        System.out.println(price);
                                        Thread.sleep(500);
                                        Dao_Farther dao_farther = new Dao_Farther("T_YSpecialConfig",2);
                                        dao_farther.Method_Insert2(bean_t_ySpecialConfig);
                                        System.out.println("完成一次插入");
                                    }
                                }
                            }
                        } else {
                            //System.out.println("不 存 在 特 色 配 置");
                        }
                    }


                    //Dao_Farther daoConfig = new Dao_Farther("T_YiCheAllConfig", 0);

                } else {
                    System.out.println("error:" + path);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:/A_errorIns/" + i + "_errorSpecial.text", true), 662148);//165537
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
