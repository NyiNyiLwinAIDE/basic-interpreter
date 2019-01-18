

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class main {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		String fname = "./src/test1.bas";
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
