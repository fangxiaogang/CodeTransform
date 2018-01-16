package com.xiaogang.codetransform;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class OutInputExportUtil {



    public static void main(String[] args) throws Exception{

        String fromurl = "D:"+File.separator+"code"+File.separator+"ShiSe";
        String tourl = "D:"+File.separator+"SHISE2";
        File tofile = new File(tourl);
        if(!tofile.exists()){
            tofile.mkdirs();
        }

        System.out.println("=========执行===========");

        getFiles(fromurl, tourl);

        System.out.println("=========结束============");
    }


    public static void getFiles(String from,String to)throws Exception{
        File list = new File(from);
        if(list.exists()){
            String[] listArr = list.list();
            if(listArr!=null && listArr.length>0){
                for(int i=0;i<listArr.length;i++){
                    File child = new File(listArr[i]);

                    if(listArr[i].indexOf("svn")>-1 || listArr[i].indexOf(".settings")>-1 ||
                            listArr[i].indexOf(".myeclipse")>-1){
                        continue;
                    }

                    if(child!=null && listArr[i].indexOf(".")==-1){
                        //目录
                        String name = child.getName();
                        if(name.indexOf("svn")==-1){
                            String newFileStr = to + File.separator + name;

                            System.out.println("=======正在创建========"+name);

                            File newFile = new File(newFileStr);
                            if(!newFile.exists()){
                                newFile.mkdirs();
                            }
                            getFiles(from + File.separator + name, newFileStr);
                        }
                    }else{
                        //文件
                        String name = child.getName();
                        String newFileStr = "";

                        System.out.println("=======正在复制========"+name);

                        //判断文件种类
                        if(name.indexOf(".txt")>-1){
                            newFileStr = to + File.separator + name;
                            newFileStr = newFileStr.replace(".txt", ".java");
                        }else{
                            newFileStr = to + File.separator + name;
                        }

                        File newFile = new File(newFileStr);
                        if(!newFile.exists()){
                            newFile.createNewFile();
                        }
                        copy(from + File.separator + name, newFileStr);
                    }

                }
            }
        }
    }





    public static void copy(String from,String to) {
        InputStream input = null;
        OutputStream output = null;
        try {
            try {
                input = new BufferedInputStream( new FileInputStream(from), 1024);
                output = new BufferedOutputStream( new FileOutputStream(to), 1024);
                byte [] buffer = new byte [1024];

                int i;
                while ((i=input.read(buffer) )> 0 )  {
                    output.write(buffer,0, i);
                }

            } finally  {
                if (null != input)  {
                    input.close();
                }
                if ( null != output)  {
                    output.flush();
                    output.close();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }


}
