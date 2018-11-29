package newlang1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		String fname = "./src/test1.bas";
		if(args.length > 0) {
			fname = args[0];
		}
		Reader fr = null;
		try {
			fr = new FileReader(fname);
		}catch (FileNotFoundException e) {
			System.out.println(fname + " : not found");
			System.exit(-1);
		}
		while(true) {
			int ci;
			try{
				ci = fr.read();
			}catch(IOException e){
				System.out.println("io error");
				break;
			}
				if(ci == -1) break;
				System.out.print((char)ci);
				}
			if(fr != null) {
				try {
					fr.close();
				}catch(IOException e) {}
			}
		}
	}