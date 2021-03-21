package com.jexing.cup.util;

import java.io.*;

public class IOUtil {
    /**
     * 保存一个文件到本地
     * @param path
     */
    public static void save(String path, InputStream stream) throws IOException {
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
        }finally {
            if (inputStream!=null){
                inputStream.close();
            }
            if (outputStream!=null){
                outputStream.close();
            }
        }
    }

    public static byte[] read(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            return new byte[0];
        }
        BufferedInputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream =new BufferedInputStream(new FileInputStream(file));
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (FileNotFoundException ignored) {}finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
        return data;
    }
}
