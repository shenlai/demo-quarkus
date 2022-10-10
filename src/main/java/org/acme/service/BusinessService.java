package org.acme.service;

/**
 * @author rock
 * @Description TODO
 * @createTime 2022/09/06 11:06:00
 */
public class BusinessService {

    public String print() {
        return "a bean from " + this.getClass().getSimpleName();
    }

}
