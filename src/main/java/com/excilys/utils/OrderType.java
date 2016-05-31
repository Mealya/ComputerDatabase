package com.excilys.utils;

import org.springframework.data.domain.Sort.Direction;

public enum OrderType {
    NAME_ASC("name;asc"),
    NAME_DESC("name;desc"),
    
    INTRODUCED_ASC("intro;asc"),
    INTRODUCED_DESC("intro;desc"),
    
    DISCONTINUED_ASC("disco;asc"),
    DISCONTINUED_DESC("disco;desc"),
    
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
    public Direction getOrder() {
        String order[] = orderType.split(";");
        switch (order[1]) {
        case "asc" :
            return Direction.ASC;
        case "desc" :
            return Direction.DESC;
        default :
            return Direction.ASC;
        }
    }
}
