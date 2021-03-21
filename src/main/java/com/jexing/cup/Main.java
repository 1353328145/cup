package com.jexing.cup;

import com.jexing.cup.exception.FileTypeNotSupportedException;
import com.jexing.cup.exception.NeedAbsolutePathException;
import com.jexing.cup.impl.LocalImageStore;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws NeedAbsolutePathException, IOException, FileTypeNotSupportedException {
        ImageStore store =new LocalImageStore("D:\\test");
        BufferedInputStream inputStream =new BufferedInputStream(new FileInputStream(new File("D:\\test\\try.jpg")));
        Date date =new Date();
//        String s = store.putObj(inputStream, "image/jpeg");
        String objForBase64 = store.getObjForBase64("077773a50b8a48328b6bf2b41abb62b4@image/jpeg");
        Date date2 = new Date();
        System.out.println(date2.getTime()-date.getTime());
//        System.out.println(s);
        System.out.println(objForBase64);
        inputStream.close();
    }
}
