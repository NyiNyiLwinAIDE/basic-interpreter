package SyntaxAnalyzer;

import LexicalAnalyzer.*;


public class PrintFunction extends Function{
    public Value invoke(ExprListNode args) throws Exception {
        if(args.size() != 1){
            throw new Exception("Printer.javaのinvokeのエラー");
        }
        Value value = args.getList().get(0).getValue();
        String str;
        switch (value.getType()){
            case INTEGER:
                str = Integer.toString(value.getIValue());
                break;
            case DOUBLE:
                str = Double.toString(value.getDValue());
                break;
            case STRING:
                str = value.getSValue();
                break;
            case BOOL:
                str = Boolean.toString(value.getBValue());
                break;
                default:
                    str = value.toString();
        }
        System.out.println(str);
        return null;
    }
}
