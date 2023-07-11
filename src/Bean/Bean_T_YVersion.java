package Bean;

public class Bean_T_YVersion {
    private int C_ID;
    private String C_ModelID;
    private String C_VersionName;
    private String C_VersionID;
    private String C_VersionURL;
    private String C_State;
    private String C_分组;
    private String C_分组是否被下载;

    public int get_C_ID() {
        return this.C_ID;
    }

    public void set_C_ID(int C_ID) {
        this.C_ID = C_ID;
    }

    public String get_C_ModelID() {
        return this.C_ModelID;
    }

    public void set_C_ModelID(String C_ModelID) {
        this.C_ModelID = C_ModelID;
    }

    public String get_C_VersionName() {
        return this.C_VersionName;
    }

    public void set_C_VersionName(String C_VersionName) {
        this.C_VersionName = C_VersionName;
    }

    public String get_C_VersionID() {
        return this.C_VersionID;
    }

    public void set_C_VersionID(String C_VersionID) {
        this.C_VersionID = C_VersionID;
    }

    public String get_C_VersionURL() {
        return this.C_VersionURL;
    }

    public void set_C_VersionURL(String C_VersionURL) {
        this.C_VersionURL = C_VersionURL;
    }

    public String get_C_State() {
        return this.C_State;
    }

    public void set_C_State(String C_State) {
        this.C_State = C_State;
    }

    public void set_C_分组(String C_分组) {
        this.C_分组 = C_分组;
    }
    public String get_C_分组(){
        return this.C_分组;
    }
    public void set_C_分组是否被下载(String C_分组是否被下载){
        this.C_分组是否被下载 = C_分组是否被下载;
    }
    public String get_C_分组是否被下载(){
        return this.C_分组是否被下载;
    }
}
