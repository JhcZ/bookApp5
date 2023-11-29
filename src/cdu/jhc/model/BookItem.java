package cdu.jhc.model;

import java.math.BigDecimal;

//图书购买项实体类，该类用在购物车、订单中
public class BookItem {
    private Customer customer;//顾客
    private Book book;//图书
    private int num;//图书购买数量

    public BookItem(){}

    public BookItem(Book book,int num,Customer customer){
        this.book = book;
        this.num = num;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    //获取图书购买价格=折扣价*购买数量
    public BigDecimal getBookPrice(){
        return book.getSalePrice().multiply(BigDecimal.valueOf(num));
    }

    @Override
    public String toString(){
        return book.getId() + ":" + num;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof BookItem){
            BookItem item = (BookItem) obj;
            if(item.getBook().getId() == this.getBook().getId()){
                return true;
            }
        }
        return false;
    }
}
