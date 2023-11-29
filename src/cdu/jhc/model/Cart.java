package cdu.jhc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//购物车实体类
public class Cart {
    private Customer customer;
    private List<BookItem> bookItems;

    public Cart(){
        bookItems = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        for(BookItem bookItem : getBookItems()){
            totalPrice = totalPrice.add(bookItem.getBookPrice());
        }
        return totalPrice;
    }

    public int size(){
        return getBookItems().size();
    }
}
