package newlang3;

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
			ivalue = Integer.valueOf(src);
			break;
		case DOUBLE:
		case STRING:
		case BOOL:
		}
	}
	@Override
	public String getSValue() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int getIValue() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public double getDValue() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean getBValue() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public ValueType getType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
