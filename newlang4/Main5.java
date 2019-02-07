package newlang4;

import newlang3.LexicalAnalyzer;
import newlang3.LexicalAnalyzerimple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main5 {
    public static void main(String[] args) throws Exception {
        // TODO 自動生成されたメソッド・スタブ
        // インタプリタの実行

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
        System.out.println(program.getValue());
    }
}
