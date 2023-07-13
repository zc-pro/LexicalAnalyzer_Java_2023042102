import java.util.Scanner;
public class LexerTest {
    public static void main(String[] args){
        System.out.println("请输入要进行词法分析的C语言源程序名：");
        String filename;
        Scanner scanner = new Scanner(System.in);
        filename = scanner.next();
        LexicalAnalyzer analyzer = new LexicalAnalyzer(filename);
        System.out.println("词法分析结果如下：");
        analyzer.analyze();
    }
}
