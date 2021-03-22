package com.jexing.cup;

import com.jexing.cup.exception.FileTypeNotSupportedException;

import java.io.InputStream;

public interface ImageStore {
    //存入图片对象
    //保存失败返回null
    String putObj(InputStream stream, String contentType) throws FileTypeNotSupportedException;
    //获取图片的base64编码
    //失败返回null
    String getObjForBase64(String key);
    //获取图片二进制
    //失败返回null
    byte[] getObjForByte(String key);
    //判断一个图片是否存在
    boolean contain(String key);
    //删除一个图片
    boolean delete(String key);
}
