package LexicalAnalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LexicalMain {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		String fname = "./src/FizzBuzz.bas";
		if(args.length > 0) {
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
		
		LexicalAnalyzerimple lai = new LexicalAnalyzerimple(is);

		while(true) {
			try {
				LexicalUnit lu = lai.get();
				System.out.println(lu.toString());
				if(lu.getType() == LexicalType.EOF) {
					break;
				}
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
