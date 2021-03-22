package com.jexing.cup;

import com.jexing.cup.exception.FileTypeNotSupportedException;
import com.jexing.cup.exception.NeedAbsolutePathException;
import com.jexing.cup.impl.LocalImageStore;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws NeedAbsolutePathException {
        ImageStore store =new LocalImageStore("D:\\imageStore");
    }
}
