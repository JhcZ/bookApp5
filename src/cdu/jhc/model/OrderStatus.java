package cdu.jhc.model;

//订单状态枚举
public enum OrderStatus {
    UNKONWN("异常"),
    UNPAID("未付款"),
    PAID("已付款"),
    SHIPPED("已发货"),
    FINISHED("已完成"),
    CANCEL("已取消"),
    ALL("");

    private String name;
    OrderStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return UNPAID.name + "," + PAID.name + "," +
                SHIPPED.name + "," + FINISHED.name + "," +
                CANCEL.name + "," + ALL.name;
    }
}
