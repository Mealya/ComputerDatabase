package com.excilys.utils;

public enum OrderType {
    NAME_ASC("name;asc"),
    NAME_DESC("name;desc"),
    
    INTRODUCED_ASC("introduced;asc"),
    INTRODUCED_DESC("introduced;desc"),
    
    DISCONTINUED_ASC("discontinued;asc"),
    DISCONTINUED_DESC("discontinued;desc"),
    
    COMPAID_ASC("company_id;asc"),
    COMPAID_DESC("company_id;desc");
    
    private String orderType = "";
    
    OrderType(String s) {
        orderType = s;
    }
    
    public String toString() {
        return orderType;
    }
    
    public static OrderType fromString(String text) {
        if (text != null) {
          for (OrderType b : OrderType.values()) {
            if (text.equalsIgnoreCase(b.orderType)) {
              return b;
            }
          }
        }
        return null;
      }
}
