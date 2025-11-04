public class C4 extends C2 {

    private double atributoC4;

    public C4() {
        super();
        this.atributoC4 = 1.1;
    }

    public C4(String attrC1, int attrC2, double atributoC4) {
        super(attrC1, attrC2);
        this.atributoC4 = atributoC4;
    }

    public void metodoProprioC4() {
        System.out.println("Executando metodoProprioC4(). Atributo: " + this.atributoC4);
    }

    @Override
    public void metodoParaSobrescrever() {
        System.out.println("Método sobrescrito em C4");
    }

    public void metodoSobrecarga() {
        System.out.println("Método sobrecarga (sem parâmetros)");
    }

    public void metodoSobrecarga(String s) {
        System.out.println("Método sobrecarga (com String): " + s);
    }

    public double getAtributoC4() {
        return atributoC4;
    }
}