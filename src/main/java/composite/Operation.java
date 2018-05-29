package composite;

public abstract class Operation extends Regla {

    private Regla expression1;

    private Regla expression2;

    public Operation(String nombre,Regla expression1, Regla expression2) {
    	super(nombre);
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public Regla getExpression1() {
        return expression1;
    }

    public Regla getExpression2() {
        return expression2;
    }

    protected abstract String operationString();

    @Override
    public String toString() {
        return "(" + this.getExpression1().toString() + this.operationString() + this.getExpression2().toString() + ")";
    }

}
