package LexicalAnalyzer;

public class ValueImple implements Value {
	
	ValueType type;
	int ivalue;
	double dvalue;
	String svalue;
	boolean bvalue;
	
	public ValueImple(String src, ValueType targetType){
		
		this.type = targetType;
		switch(targetType) {
			case INTEGER:
				dvalue = Double.parseDouble(src);
				ivalue = (int) dvalue;
				break;
			case DOUBLE:
				dvalue = Double.parseDouble(src);
				break;
			case STRING:
				svalue = src;
				break;
			case BOOL:
				bvalue = Boolean.valueOf(src);
				break;
		}
	}

	@Override
	public String getSValue() {
		// TODO 自動生成されたメソッド・スタブ
		return svalue;
	}

	@Override
	public int getIValue() {
		// TODO 自動生成されたメソッド・スタブ
		return ivalue;
	}

	@Override
	public double getDValue() {
		// TODO 自動生成されたメソッド・スタブ
		return dvalue;
	}

	@Override
	public boolean getBValue() {
		// TODO 自動生成されたメソッド・スタブ
		return bvalue;
	}

	@Override
	public ValueType getType() {
		// TODO 自動生成されたメソッド・スタブ
		return type;
	}

}
