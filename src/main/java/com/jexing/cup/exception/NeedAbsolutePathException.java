package com.jexing.cup.exception;

public class NeedAbsolutePathException extends Exception{
    public NeedAbsolutePathException(){
        super("需要一个window或者linux操作系统文件夹的绝对路径");
    }
}
