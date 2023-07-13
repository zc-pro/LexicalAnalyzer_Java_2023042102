import java.util.HashMap;

public class Token {
    public static  HashMap<String,Integer> KEYWORD;     //关键字与种别值的映射集
    public static  HashMap<String,Integer> OPERATOR;    //运算符与种别值的映射集
    public static  HashMap<String,Integer> DELIMITER;   //界符与种别值的映射集
    public static HashMap<String,Integer> HEADFILE;     //头文件名与种别值的映射集
    public static HashMap<String,Integer> SPECIALCHAR;  //特殊字符与种别值的映射集


    public static final int IDENTIFIER = 90; // 标识符，种别值为90
    public static final int NUMBER = 91;     // 数字常量，种别值为91
    public static final int EXOLANATORYNOTE = 92;   //注释，种别值为92
    public static final int STRINGCONST = 93;      //字符串常量，种别值为93




    static {
        KEYWORD = new HashMap<String,Integer>();
        OPERATOR = new HashMap<String,Integer>();
        DELIMITER = new HashMap<String,Integer>();
        HEADFILE = new HashMap<String,Integer>();
        SPECIALCHAR = new HashMap<String,Integer>();  //特殊字符与种别值的映射

        KEYWORD.put("auto",1);
        KEYWORD.put("break",2);
        KEYWORD.put("case",3);
        KEYWORD.put("char",4);
        KEYWORD.put("const",5);
        KEYWORD.put("continue",6);
        KEYWORD.put("default",7);
        KEYWORD.put("do",8);
        KEYWORD.put("double",9);
        KEYWORD.put("else",10);
        KEYWORD.put("enum",11);
        KEYWORD.put("extern",12);
        KEYWORD.put("float",13);
        KEYWORD.put("for",14);
        KEYWORD.put("goto",15);
        KEYWORD.put("if",16);
        KEYWORD.put("int",17);
        KEYWORD.put("long",18);
        KEYWORD.put("register",19);
        KEYWORD.put("return",20);
        KEYWORD.put("short",21);
        KEYWORD.put("signed",22);
        KEYWORD.put("sizeof",23);
        KEYWORD.put("static",24);
        KEYWORD.put("struct",25);
        KEYWORD.put("switch",26);
        KEYWORD.put("typedef",27);
        KEYWORD.put("union",28);
        KEYWORD.put("unsigned",29);
        KEYWORD.put("void",30);
        KEYWORD.put("volatile",31);
        KEYWORD.put("while",32);

        OPERATOR.put("+",33);
        OPERATOR.put("-",34);
        OPERATOR.put("*",35);
        OPERATOR.put("/",36);
        OPERATOR.put("%",37);
        OPERATOR.put("=",38);
        OPERATOR.put("<",39);
        OPERATOR.put(">",40);
        OPERATOR.put("&",41);
        OPERATOR.put("|",42);
        OPERATOR.put("~",43);
        OPERATOR.put("^",44);
        OPERATOR.put("!",45);
        OPERATOR.put(".",46);
        OPERATOR.put(":",47);
        OPERATOR.put("?",48);

        //双运算符
        OPERATOR.put("<=",49);
        OPERATOR.put("==",50);
        OPERATOR.put(">=",51);
        OPERATOR.put("!=",52);
        OPERATOR.put("%=",53);
        OPERATOR.put("<<",54);
        OPERATOR.put(">>",55);
        OPERATOR.put("||",56);
        OPERATOR.put("&&",57);
        OPERATOR.put("++",58);
        OPERATOR.put("--",59);

        DELIMITER.put("{",60);
        DELIMITER.put("}",61);
        DELIMITER.put("(",62);
        DELIMITER.put(")",63);
        DELIMITER.put(",",64);
        DELIMITER.put(";",65);
        DELIMITER.put("[",66);
        DELIMITER.put("]",67);
        DELIMITER.put("'",68);
        DELIMITER.put("\"",69);
        DELIMITER.put("\\",70);

        HEADFILE.put("asset,h",71);
        HEADFILE.put("ctype.h",72);
        HEADFILE.put("errno.h",73);
        HEADFILE.put("float.h",74);
        HEADFILE.put("limits.h",75);
        HEADFILE.put("locate.h",76);
        HEADFILE.put("math.h",77);
        HEADFILE.put("setjmp.h",78);
        HEADFILE.put("signal.h",79);
        HEADFILE.put("stdarg.h",80);
        HEADFILE.put("stddef.h",81);
        HEADFILE.put("stdlib.h",82);
        HEADFILE.put("stdio.h",83);
        HEADFILE.put("string.h",84);
        HEADFILE.put("time.h",85);

        SPECIALCHAR.put("@",86);
        SPECIALCHAR.put("#",87);
        SPECIALCHAR.put("$",88);
        SPECIALCHAR.put("￥",89);
    }
}
