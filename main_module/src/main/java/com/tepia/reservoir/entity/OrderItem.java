package com.tepia.reservoir.entity;

/**
 * Created by khj on 2018-3-16.
 */

public class OrderItem {
    private String ticket;
    private String DateExit;
    private String money;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getDateExit() {
        return DateExit;
    }

    public void setDateExit(String dateExit) {
        DateExit = dateExit;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
