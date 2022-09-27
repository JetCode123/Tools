package com.Tools;

import java.io.*;

/**
 * @.time: 2022.9.27 周2 05:13 'Youtube subtitle 格式转换器:' by 'JetCoding Lu'
 * */
public class YoutubeTransformer {
    public static void main(String[] args) {
        System.out.println("Youtube subtitle processor:");

        String conn = ""; // Final拼接的字符串:

        BufferedReader reader = getReader("/Users/mac/Desktop/MIT6.824-01.txt");
        conn = FileReadToString(reader, 10); // System.out.println("Final: " + conn);   //👌🏻

        BufferedWriter writer = getWriter("/Users/mac/Desktop/MIT6.824-01(t).txt");
        StringWriteToFile(writer, conn);
    }

    /* 获取写入器BufferedWriter实例:*/
    public static BufferedWriter getWriter(String outPath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outPath));
        } catch(IOException ex) {
        }
        return writer;
    }

    /* 获取读取器BufferedReader实例:*/
    public static BufferedReader getReader(String inPath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inPath));
        } catch (IOException ex) {
        }
        return br;
    }

    /** 将文件读取到string，并且指定排列格式:
     *  @param br '读取器实例:'
     *  @param spanned '指定每隔多少行空余1行的参数:'
     * */
    public static String FileReadToString(BufferedReader br, final int spanned) {
        String line = "";
        String conn = "";   // 拼接的字符串，as返回值!
        int c = 1; //迭代索引，从第一行开始:
        int total = 0; // 已经读取的行数:
        int valued = 3;   // 有效行的索引标号: 从1开始，第3行是有效的，之后的+4!
        try {
            while ((line = br.readLine()) != null) {
                if (c == valued) {
                    // 匹配到有效行: 🙄
                    if (total > 0 && total % spanned == 0) { conn += "\n\n"; }     /* 👌每隔10行，添加一个空行! Make it neater to see.*/

                    total += 1;
                    valued += 4;
                    conn += line + ". ";  /* '.' dot标识在原来文本中的位置:(1个dot就是一个单独行:) */
                }
                c++;
            }
        } catch (IOException ex) {
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /* BufferedWriter写入字符串final String s到writer所指向的文件:*/
    public static void StringWriteToFile(BufferedWriter writer, final String s) {
        try {
            writer.write(s);
        } catch (IOException e) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
}
