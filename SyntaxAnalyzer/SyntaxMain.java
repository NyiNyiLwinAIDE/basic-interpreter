package SyntaxAnalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import LexicalAnalyzer.*;

public class SyntaxMain {
    public static void main(String[] args) throws Exception {
        // TODO 自動生成されたメソッド・スタブ
        // 構文解析の実行

        String fname = "./src/FizzBuzz.bas";
        if (args.length > 0) {
            fname = args[0];
        }
        File fr = new File(fname);
        InputStream is = null;
        try {
            is = new FileInputStream(fr);
        } catch (FileNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        LexicalAnalyzer la = new LexicalAnalyzerimple(is);

        Environment env = new Environment(la);
        Node program = ProgramNode.getHandler(env);
        program.parse();
        System.out.println(program.toString());
    }
}
