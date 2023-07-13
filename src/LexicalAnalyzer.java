import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LexicalAnalyzer {
    private static String content;     //用于接收从文件中读取的字符串
    private static ArrayList<String> input;
    private static String fileName;    //文件名

    // C语言关键字 32个
    public static final String[] keywords = {
            "auto", "break", "case", "char", "const", "continue",
            "default", "do", "double", "else", "enum", "extern",
            "float", "for", "goto", "if", "int", "long",
            "register", "return", "short", "signed", "sizeof",
            "static", "struct", "switch", "typedef", "union",
            "unsigned", "void", "volatile", "while"
    };

    // C语言运算符
    public static final char[] operators = {
            '+', '-', '*', '/',
            '%', '=', '<', '>', '&', '|', '~', '^', '!',
            '.', ':', '?'
    };

    // C语言界符
    public static final char[] delimiters = {
            '{', '}', '(', ')', ',', ';', '[', ']', '\'', '\"', '\\'
    };

    // C语言头文件名
    public static final String[] headfile = {
            "asset,h", "ctype.h", "errno.h", "float.h", "limits.h",
            "locate.h", "math.h", "setjmp.h", "signal.h", "stdarg.h",
            "stddef.h", "stdlib.h", "stdio.h", "string.h", "time.h"
    };

    // C语言特殊符号
    public static final char[] specialchar = {
            '@', '#', '$', '￥'
    };

    // 判断字符是否为C语言运算符
    private static boolean isOperator(char c) {
        for (char ch : operators) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

    // 判断字符是否为C语言界符
    private static boolean isDelimiter(char c) {
        for (char ch : delimiters) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }

    // 判断字符是否为C语言的注释
    private static boolean isExolanatoryNote(String str, char c, int ptr) {
        if (ptr + 1 < str.length() && str.charAt(ptr) == '/' && (str.charAt(ptr + 1) == '/'||str.charAt(ptr+1)=='*')) {
            return true;
        }
        return false;
    }

    // 判断是否为C语言头文件名
    private static boolean isHeadFile(String c){
        for(String str : headfile){
            if(c.equals(str))
                return true;
        }
        return false;
    }
    // 判断是否为C语言特殊字符
    private static boolean isSpecialChar(char c){
        for (char ch : specialchar){
            if(c == ch){
                return true;
            }
        }
        return false;
    }

    //初始化
    public LexicalAnalyzer(String filename) {
        this.fileName = filename;
        content = "";
        input = new ArrayList<String>();
        try {
            // 读取C语言源程序文件
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content = line;
                input.add(content);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 逐行读取input中的字符串，进行词法分析，最终输出二元式
    public void analyze() {
        int i = 0;  // 读取的行数
        for (i = 0; i < input.size(); i++) {
            //System.out.println(input.get(i));
            content = input.get(i);
            int j = 0;  //当前读取的字符
            while (j < input.get(i).length()) {
                char c = content.charAt(j);
                // 判断字符是否为空格
                if (Character.isWhitespace(c)) {
                    j++;
                    continue;
                }
                // 判断字符是否为特殊字符
                if (isSpecialChar(c)){
                    System.out.println("( "+Token.SPECIALCHAR.get(String.valueOf(c))+", "+c+" )");
                }
                // 判断字符是否为C语言标识符或关键字或头文件名
                if (Character.isLetter(c) || c == '_') {
                    String identifier = "";
                    while (j < content.length() && (Character.isLetterOrDigit(c) || c == '_'|| c == '.')) {
                        identifier += c;
                        j++;
                        if (j < content.length()) {
                            c = content.charAt(j);
                        }
                    }
                    // 判断标识符是否为关键字
                    boolean isKeyword = false;
                    for (String keyword : keywords) {
                        if (identifier.equals(keyword)) {
                            System.out.println("( " + Token.KEYWORD.get(identifier) + ", " + identifier + " )");
                            isKeyword = true;
                            break;
                        }
                    }
                    boolean isHeadfile = false;
                    if(isHeadFile(identifier)){
                        isHeadfile = true;
                        System.out.println("( "+Token.HEADFILE.get(identifier)+", "+identifier+" )");
                    }
                    if (!isKeyword&&!isHeadfile) {
                        System.out.println("( " + Token.IDENTIFIER + ", " + identifier + " )");
                    }
                }
                // 判断字符是否为数字
                else if (Character.isDigit(c)) {
                    String number = "";
                    while (j < content.length() && (Character.isDigit(c)||c=='.')) {
                        number += c;
                        j++;
                        if (j < content.length()) {
                            c = content.charAt(j);
                        }
                    }
                    System.out.println("( " + Token.NUMBER + ", " + number + " )");
                }
                //判断字符是否为注释
                else if (isExolanatoryNote(content, c, j)) {
                    char next = content.charAt(j + 1);
                    String note = "";     //用于记录注释
                    boolean sameLine = false;      //信号量，判断/* 与 */ 是否在同一行
                    boolean signalEnd = false;     // 判断注释是否结束
                    boolean kind = false;          //信号标记，用于表示注释的类型 , 分为单行和多行注释
                    switch (next) {
                        case '/': {     //判断为单行注释
                            while (j + 2 < content.length()) {
                                note += content.charAt(j + 2);
                                j++;
                            }
                            System.out.println("( " + Token.EXOLANATORYNOTE + ", " + note + " )");
                            kind = true;
                            break;
                        }
                        case '*': {      //判断为多行注释
                            int previous = i; // 注释开始的一行
                            for (; i < input.size() && !signalEnd; i++) {
                                if (i == previous) {           //特殊情况 ：此时说明 /* */ 位于同一行
                                    j = j + 2;
                                    while (j < content.length()) {
                                        if (!(content.charAt(j) == '*' && content.charAt(j + 1) == '/')) {
                                            note += content.charAt(j);
                                            j++;
                                        } else {
                                            System.out.println("( " + Token.EXOLANATORYNOTE + ", " + note + " )");
                                            sameLine = true;
                                            signalEnd = true;
                                            kind = true;
                                            break;
                                        }
                                    }
                                }
                                else {
                                    j = 0;
                                    content = input.get(i);
                                    while (j + 1 < content.length()) {
                                        if (!(content.charAt(j) == '*' && content.charAt(j + 1) == '/')) {
                                            note += content.charAt(j);
                                            j++;
                                        } else {
                                            System.out.println("( " + Token.EXOLANATORYNOTE + ", " + note + " )");
                                            signalEnd = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(!kind)      //如果注释不是单行
                        i = i - 1; //减 1 是为了回退到 注释结尾一行， 因为外循环下一次读取自动加1
                    break;
                }
                // 判断字符是否为运算符
                else if (isOperator(c)) {
                    String operator = "";
                    while (j < content.length() && isOperator(c)) {
                        operator += c;
                        j++;
                        if (j < content.length()) {
                            c = content.charAt(j);
                        }
                    }
                    System.out.println("( " + Token.OPERATOR.get(operator) + ", " + operator + " )");
                }
                // 判断字符是否为界符或字符串常量
                else if (isDelimiter(c)) {
                    //boolean isright = false;
                    String delimiter = "";
                    if(c == '\"'){     //判断是否为字符串常量
                        System.out.println("( "+Token.DELIMITER.get(String.valueOf(c)) + ", " + c + " )");
                        String strconst = "";   // 用于存储字符串常量
                        j++;
                        while(j<content.length()&& content.charAt(j)!='\"'){
                            strconst+=content.charAt(j);
                            j++;
                            if (j < content.length()) {
                                c = content.charAt(j);
                            }
                        }
                        if(strconst!="")
                            System.out.println("( " + Token.STRINGCONST+ ", " + strconst + " )");
                        System.out.println("( "+Token.DELIMITER.get(String.valueOf(c)) + ", " + c + " )");
                        j++;
                        continue;
                    }

                    while (j < content.length() && isDelimiter(c)&& c!='"') {
                        delimiter = String.valueOf(c);
                        System.out.println("( " + Token.DELIMITER.get(delimiter) + ", " + delimiter + " )");
                        j++;
                        if (j < content.length()) {
                            c = content.charAt(j);
                        }
                    }
                }
                else {
                    j++;
                }
            }
        }
    }
}
