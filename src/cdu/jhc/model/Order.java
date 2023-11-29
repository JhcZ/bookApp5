package cdu.jhc.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

//订单实体类
public class Order {
    private int id;
    private String orderId;
    private User customer;
    private List<BookItem> bookItems;
    private BigDecimal money;
    private OrderStatus status;
    private long createTime;
    private long updateTime;
    private String receiverName;
    private String receiverTel;
    private String receiverAddress;
    private String expressNumber;
    private String customerId;//用户模糊查询

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public String getBooks(){
        if(bookItems == null){
            return null;
        }
        String books = "";
        for(BookItem bookItem : bookItems){
            books += bookItem.toString() + ",";
        }
        return books;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setStatus(String statusName){
        if(statusName == null || statusName.equals("")){
            setStatus(OrderStatus.ALL);
            return;
        }
        switch (statusName){
            case "未付款":
                setStatus(OrderStatus.UNPAID);
                break;
            case "已付款":
                setStatus(OrderStatus.PAID);
                break;
            case "已发货":
                setStatus(OrderStatus.SHIPPED);
                break;
            case "已完成":
                setStatus(OrderStatus.FINISHED);
                break;
            case "已取消":
                setStatus(OrderStatus.CANCEL);
                break;
            case "异常":
                setStatus(OrderStatus.UNKONWN);
                break;
            default:
                setStatus(OrderStatus.ALL);
        }
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = String.valueOf(customerId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", books=" + Arrays.toString(bookItems.toArray()) +
                ", money=" + money +
                ", status=" + (status != null ? status.getName() : null) +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", receiverName=" + receiverName +
                ", receiverTel=" + receiverTel +
                ", receiverAddress=" + receiverAddress +
                '}';
    }
}
