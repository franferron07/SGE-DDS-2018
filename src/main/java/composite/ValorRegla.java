package composite;

public class ValorRegla extends Regla {

    private int value;

    public ValorRegla(String nombre,int value) {
    	super(nombre);
        this.value = value;
    }

    @Override
    public int operate() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
