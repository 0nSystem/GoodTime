package com.goodtime.webservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerAttributesSession {
    @Getter
    public enum AttributesType{
        ID("id");
        private String attributeName;
        AttributesType(String attributeName) {
            this.attributeName=attributeName;
        }
    }

    private Long id;

    public static ManagerAttributesSession getAttributesInHttpSession(HttpSession httpSession){
        ManagerAttributesSession managerAttributesSession=new ManagerAttributesSession();
        httpSession.getAttributeNames().asIterator().forEachRemaining(a->{
            String valueAttribute=String.valueOf(httpSession.getAttribute(a));
            if(a==AttributesType.ID.getAttributeName()){
                managerAttributesSession.setId(Long.valueOf(valueAttribute));
            }
        });

        return managerAttributesSession;
    }
    public static void setAttributesInHttpSession(ManagerAttributesSession managerAttributesSession,HttpSession httpSession){
        httpSession.setAttribute(AttributesType.ID.getAttributeName(), managerAttributesSession.getId());
    }
}
