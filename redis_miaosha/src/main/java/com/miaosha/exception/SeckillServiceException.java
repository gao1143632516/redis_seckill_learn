package com.miaosha.exception;

/**
 * @author gmq
 * @create 2020-09-11 14:07
 */
public class SeckillServiceException extends RuntimeException {
    SeckillServiceException(){
        super();
    }
    public SeckillServiceException(String message){
        super(message);
    }
}
