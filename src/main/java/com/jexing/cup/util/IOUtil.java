package com.jexing.cup.util;

import java.io.*;

public class IOUtil {
    /**
     * 保存一个文件到本地
     * @param path
     */
    public static boolean save(String path, InputStream stream) {
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try{
            File file =new File(path);
            file.createNewFile();
            inputStream = new BufferedInputStream(stream);
            outputStream =new BufferedOutputStream(new FileOutputStream(file));
            int n;
            while ((n = inputStream.read())!=-1){
                outputStream.write(n);
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }finally {
            closeIn(inputStream);
            closeOut(outputStream);
        }
        return true;
    }

    public static byte[] read(String path) {
        File file = new File(path);
        if (!file.exists()){
            return null;
        }
        BufferedInputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream =new BufferedInputStream(new FileInputStream(file));
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (FileNotFoundException ignored) {} catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeIn(inputStream);
        }
        return data;
    }
    public static void closeIn(InputStream inputStream){
        try {
            if (inputStream!=null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closeOut(OutputStream outputStream){
        try {
            if (outputStream!=null){
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
