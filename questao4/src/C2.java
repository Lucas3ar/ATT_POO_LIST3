public class C2 extends C1 {

    private int atributoC2;

    public C2() {
        super();
        this.atributoC2 = 10;
    }

    public C2(String attrC1, int atributoC2) {
        super(attrC1);
        this.atributoC2 = atributoC2;
    }

    public void metodoProprioC2() {
        System.out.println("Executando metodoProprioC2(). Atributo: " + this.atributoC2);
    }

    @Override
    public void metodoParaSobrescrever() {
        System.out.println("Método sobrescrito em C2");
    }

    public int getAtributoC2() {
        return atributoC2;
    }
}