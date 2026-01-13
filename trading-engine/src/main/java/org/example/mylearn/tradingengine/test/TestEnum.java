package org.example.mylearn.tradingengine.test;

import org.example.mylearn.common.ErrorCode;

public class TestEnum {

    public static void main(String[] args) {


    ErrorCode err1 = ErrorCode.DEFAULT;
    ErrorCode err2 = ErrorCode.UNKNOWN_ERROR;
    ErrorCode err3 = ErrorCode.USER_NOT_FOUND;

    System.out.println(err1);
    System.out.println(err1.toString());
    System.out.println(err2.getCode() + ":" + err2.getMessage() );
    System.out.println(err2);
    System.out.println(err3);

    }

}
