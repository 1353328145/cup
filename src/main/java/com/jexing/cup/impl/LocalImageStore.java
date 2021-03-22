package com.jexing.cup.impl;

import com.jexing.cup.ImageStore;
import com.jexing.cup.exception.FileTypeNotSupportedException;
import com.jexing.cup.exception.NeedAbsolutePathException;
import com.jexing.cup.util.IOUtil;
import com.jexing.cup.util.Tool;
import java.io.File;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

public class LocalImageStore implements ImageStore {
    private String localRepository;
    private int level;
    private HashMap<String,String> map;
    public LocalImageStore(String localRepository) throws NeedAbsolutePathException {
        this(localRepository,4);
    }
    //level = 4 , 8 , 16 否则都为 4
    public LocalImageStore(String localRepository,int level) throws NeedAbsolutePathException {
        if (level != 4 && level!= 8 && level!=16){
            level = 4;
        }
        map = Tool.getTypeMap();
        this.localRepository = localRepository;
        this.level = level;
        checkAndBuild();
    }

    @Override
    public String putObj(InputStream stream, String contentType) throws FileTypeNotSupportedException {
        if (!map.containsKey(contentType)){
            throw new FileTypeNotSupportedException();
        }
        String key = UUID.randomUUID().toString().replace("-","");
        String path = createDirectory(Tool.hash(key,level), key+map.get(contentType));
        return IOUtil.save(path,stream)?key+"@"+contentType:null;
    }

    /**
     * 根据hash、文件路径 创建文件夹，并返回最终路径
     * @param hashCode
     * @param fileName
     */
    private String createDirectory(String hashCode,String fileName){
        StringBuffer path = new StringBuffer(localRepository);
        for (int i = 0; i < hashCode.length(); i++) {
            path.append(File.separator).append(hashCode.charAt(i));
        }
        new File(path.toString()).mkdirs();
        path.append(File.separator).append(fileName);
        return path.toString();
    }

    /**
     * 遇上方法类似但不创建文件夹
     * @param hashCode
     * @param fileName
     * @return
     */
    private String handle(String hashCode,String fileName){
        StringBuffer path = new StringBuffer(localRepository);
        for (int i = 0; i < hashCode.length(); i++) {
            path.append(File.separator).append(hashCode.charAt(i));
        }
        path.append(File.separator).append(fileName);
        return path.toString();
    }

    private void checkAndBuild() throws NeedAbsolutePathException {
        File file = new File(localRepository);
        if (!file.isAbsolute()){
            throw new NeedAbsolutePathException();
        }
        file.mkdirs();
    }

    @Override
    public String getObjForBase64(String key) {
        byte[] bytes = getObj(key);
        if (bytes==null){
            return null;
        }
        String type = key.split("@")[1];
        StringBuffer answer = new StringBuffer("data:");
        answer.append(type).append(";base64,").append(Base64.getEncoder().encodeToString(bytes));
        return answer.toString();
    }

    @Override
    public byte[] getObjForByte(String key) {
        return getObj(key);
    }
    private byte[] getObj(String key){
        String path = getPath(key);
        if (path==null){
            return null;
        }
        return IOUtil.read(path);
    }
    @Override
    public boolean contain(String key) {
        String path = getPath(key);
        if (path!=null){
            return new File(path).exists();
        }
        return false;
    }

    @Override
    public boolean delete(String key) {
        String path = getPath(key);
        if (path == null){
            return false;
        }
        return new File(path).delete();
    }

    /**
     * 测试key的正确性
     */
    private boolean testKey(String[] split){
        return split.length==2 && map.containsKey(split[1]) && split[0].length()==32;
    }
    /**
     * 获取绝对路径
     * @param key
     */
    private String getPath(String key){
        String[] split = key.split("@");
        if (!testKey(split)){
            return null;
        }
        String hash = Tool.hash(split[0],level);
        return handle(hash, split[0] + map.get(split[1]));
    }
}
