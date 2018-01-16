package com.xiaogang.codetransform;

/**
 * Created by xiaogang on 2018/1/16 0016.
 */

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;


public class GbkToUtf8 {

    /**
     * 使用common io批量将java编码从GBK转UTF-8
     *
     * @param args
     * @throws Exception
     */
    public static final String[] javastr = { "java" };


    public static void main(String[] args) throws Exception {
        // GBK编码格式源码路径
        String srcDirPath = "D:" + File.separator + "code2";
        // 转为UTF-8编码格式源码路径
        String utf8DirPath = "D:" + File.separator + "code3";

        // 获取所有java文件
        Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), javastr, true);

        for (Iterator<File> iterator = javaGbkFileCol.iterator(); iterator.hasNext();)
        {
            File javaGbkFile = iterator.next();
            // UTF8格式文件路径
            String utf8FilePath = utf8DirPath
                    + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());

            // 使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8",
                    FileUtils.readLines(javaGbkFile, "GBK"));
            System.out.println("转换完成！");
        }

    }
}