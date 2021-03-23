package com.jexing.cup.exception;

public class FileTypeNotSupportedException extends Exception{
    public FileTypeNotSupportedException(){
        super("文件格式不被支持");
    }
}
