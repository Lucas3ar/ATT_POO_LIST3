public class C3 extends C1 implements L1, L2 {

    private boolean atributoC3;

    public C3() {
        super();
        this.atributoC3 = true;
    }

    public C3(String attrC1, boolean atributoC3) {
        super(attrC1);
        this.atributoC3 = atributoC3;
    }

    public void metodoProprioC3() {
        System.out.println("Executando metodoProprioC3(). Atributo: " + this.atributoC3);
    }

    @Override
    public void metodoParaSobrescrever() {
        System.out.println("Método sobrescrito em C3");
    }

    @Override
    public void metodoI1() {
        System.out.println("C3 implementando metodoI1()");
    }

    @Override
    public void metodoI2_A() {
        System.out.println("C3 implementando metodoI2_A()");
    }

    @Override
    public void metodoI2_B() {
        System.out.println("C3 implementando metodoI2_B()");
    }

    public boolean isAtributoC3() {
        return atributoC3;
    }
}