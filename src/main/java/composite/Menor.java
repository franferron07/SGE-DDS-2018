package composite;

public class Menor extends Operation {

    public Menor(String nombre,Regla expression1, Regla expression2) {
        super(nombre,expression1, expression2);
    }

    @Override
    public int operate() {
    	 if(this.getExpression1().operate()<this.getExpression2().operate())
      	   return -1;
           return 0;
    }

    @Override
    protected String operationString() {
        return "<";
    }

}
