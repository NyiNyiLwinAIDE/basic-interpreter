package LexicalAnalyzer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;




public class LexicalAnalyzerimple implements LexicalAnalyzer {
	//PushbackReaderの宣言とMapの宣言と初期化
	PushbackReader reader;
	static Map<String,LexicalUnit> map = new HashMap<>();
	List<LexicalUnit> units = new ArrayList<>();
	
	public LexicalAnalyzerimple(InputStream in) {
		Reader r =  new InputStreamReader(in);
		reader = new PushbackReader(r);
	}
	
	static {
		map.put("IF", new LexicalUnit(LexicalType.IF));
		map.put("THEN", new LexicalUnit(LexicalType.THEN));
		map.put("ELSE", new LexicalUnit(LexicalType.ELSE));
		map.put("ELSEIF", new LexicalUnit(LexicalType.ELSEIF));
		map.put("ENDIF", new LexicalUnit(LexicalType.ENDIF));
		map.put("FOR", new LexicalUnit(LexicalType.FOR));
		map.put("", new LexicalUnit(LexicalType.IF));
		map.put("FORALL", new LexicalUnit(LexicalType.FORALL));
		map.put("NEXT", new LexicalUnit(LexicalType.NEXT));
		map.put("FUNC", new LexicalUnit(LexicalType.FUNC));
		map.put("DIM", new LexicalUnit(LexicalType.DIM));
		map.put("AS", new LexicalUnit(LexicalType.AS));
		map.put("END", new LexicalUnit(LexicalType.END));
		map.put("WHILE", new LexicalUnit(LexicalType.WHILE));
		map.put("DO", new LexicalUnit(LexicalType.DO));
		map.put("UNTIL", new LexicalUnit(LexicalType.UNTIL));
		map.put("LOOP", new LexicalUnit(LexicalType.LOOP));
		map.put("TO", new LexicalUnit(LexicalType.TO));
		map.put("WEND", new LexicalUnit(LexicalType.WEND));
		map.put("=", new LexicalUnit(LexicalType.EQ));
		map.put("<", new LexicalUnit(LexicalType.LT));
		map.put(">", new LexicalUnit(LexicalType.GT));
		map.put("<=", new LexicalUnit(LexicalType.LE));
		map.put("=<", new LexicalUnit(LexicalType.LE));
		map.put(">=", new LexicalUnit(LexicalType.GE));
		map.put("=>", new LexicalUnit(LexicalType.GE));
		map.put("<>", new LexicalUnit(LexicalType.NE));
		map.put("\n", new LexicalUnit(LexicalType.NL));
		map.put(".", new LexicalUnit(LexicalType.DOT));
		map.put("+", new LexicalUnit(LexicalType.ADD));
		map.put("-", new LexicalUnit(LexicalType.SUB));
		map.put("*", new LexicalUnit(LexicalType.MUL));
		map.put("/", new LexicalUnit(LexicalType.DIV));
		map.put("(", new LexicalUnit(LexicalType.LP));
		map.put(")", new LexicalUnit(LexicalType.RP));
		map.put(",", new LexicalUnit(LexicalType.COMMA));
	}

	@Override
	public LexicalUnit get() throws Exception {
		if(!units.isEmpty()) {
			int index = units.size() - 1;
			LexicalUnit tmp = units.get(index);
			units.remove(index);
			return tmp;
		}
		
		if(!reader.ready()) {
			return new LexicalUnit(LexicalType.EOF);
		}
		int ci = reader.read();
		if (ci < 0) {
			return new LexicalUnit(LexicalType.EOF);
		}
		char c = (char) ci;
		while(c == ' ' || c == '\t' || c == '\r') {
			ci = reader.read();
			c = (char) ci;
		}
		reader.unread(ci);
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
			return getString();
		} else if (Character.isDigit(c)) {
			return getNumber();

		} else if (c == '"') {
			return getLiteral();
		}
		else return getSymbol();
	}
	
	private LexicalUnit getString() throws Exception {
		String target ="";
		while(true) {
			int ci = reader.read();
			if(ci < 0) break;
			char c = (char) ci;
			if((c >= 'a' && c <= 'z') ||
				(c >= 'A' && c <= 'Z') ||
				(c >= '0' && c <= '9')){
				target += c;
				continue;
			}
			reader.unread(ci);
			break;
		}
		if(map.containsKey(target)) {
			return map.get(target);
		}
		return new LexicalUnit(LexicalType.NAME,
				new ValueImple(target, ValueType.STRING));
	}
	
	private LexicalUnit getNumber() throws Exception {
		String target ="";
		boolean flg = false;
		while(true) {
			int ci = reader.read();
			if(ci < 0) break;
			char c = (char) ci;
			if(c >= '0' && c <= '9'){
				target += c;
				continue;
			}
			if((ci > 0) && (c == '.') && !flg) {
				target += c;
				flg = true;
				continue;
			}
			reader.unread(ci);
			break;
		}
		if(flg) {
			return new LexicalUnit(LexicalType.DOUBLEVAL, 
					new ValueImple(target, ValueType.DOUBLE));
		}
		return new LexicalUnit(LexicalType.INTVAL, 
				new ValueImple(target, ValueType.INTEGER));
	}
	
	private LexicalUnit getLiteral() throws Exception {
		String target ="";
		int ci = reader.read();
		while(true) {
			ci = reader.read();
			if(ci < 0) break;
			char c = (char) ci;
			if(c != '"'){
				target += c;
				continue;
			}
			break;
		}
		return new LexicalUnit(LexicalType.LITERAL, 
				new ValueImple(target, ValueType.STRING));
	}
	private LexicalUnit getSymbol() throws Exception {
		int ci = reader.read();
		char c = (char) ci;
		String s = (String.valueOf(c));
		
		if(c == '<') {
			ci = reader.read();
			if((char)ci == '=') {
				s += (char)ci;
			}else if((char)ci == '>'){
				s += (char)ci;
			}
		}else if(c == '>') {
			ci = reader.read();
			if((char)ci == '=') {
				s += (char)ci;
			}
		}else if(c == '=') {
			ci = reader.read();
			if((char)ci == '>') {
				s += (char)ci;
			}else if((char)ci == '<') {
				s += (char)ci;
			}else{
				reader.unread(ci);
			}
		}
		
		if(map.containsKey(s)) {
			return map.get(s);
		}else {
			return null;
		}
	}

	@Override
	public boolean expect(LexicalType type) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void unget(LexicalUnit token) throws Exception {
		units.add(token);
		
	}
	
	// カーソルを進めないでn個先の字句を読めるpeekを作った
	// 変数は一字以降をみる
	public LexicalUnit peek(int n) throws Exception{
		List<LexicalUnit> tmp = new ArrayList<>();
		for(int i=0; i < n-1; i++) {
			tmp.add(get());
		}
		LexicalUnit lu = get();
		tmp.add(lu);
	
		for(int i=tmp.size()-1; i >= 0; i--) {
			unget(tmp.get(i));
		}
		
		return lu;
	}

}
