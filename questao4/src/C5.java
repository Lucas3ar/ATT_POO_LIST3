public class C5 extends C2 implements L1 {

    private long atributoC5;

    public C5() {
        super();
        this.atributoC5 = 100L;
    }

    public C5(String attrC1, int attrC2, long atributoC5) {
        super(attrC1, attrC2);
        this.atributoC5 = atributoC5;
    }

    public void metodoProprioC5() {
        System.out.println("Executando metodoProprioC5(). Atributo: " + this.atributoC5);
    }

    @Override
    public void metodoParaSobrescrever() {
        System.out.println("Método sobrescrito em C5");
    }

    @Override
    public void metodoI1() {
        System.out.println("C5 implementando metodoI1()");
    }

    public long getAtributoC5() {
        return atributoC5;
    }
}