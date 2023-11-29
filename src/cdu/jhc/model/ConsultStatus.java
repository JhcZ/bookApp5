package cdu.jhc.model;

//图书咨询状态枚举类
public enum ConsultStatus {
    NOREPLY("未回复"),
    DONE("已回复"),
    ALL("");

    private String name;
    ConsultStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
