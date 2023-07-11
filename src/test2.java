import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class test2 {
    public static void main(String[] args) {
        try {
            String path = "F:/A_YicheAllConfig/2086_Config.text";
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
            System.out.println(configData.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
