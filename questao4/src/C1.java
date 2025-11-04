public abstract class C1 {

    private String atributoC1;

    public C1() {
        this.atributoC1 = "ValorPadraoC1";
    }

    public C1(String atributoC1) {
        this.atributoC1 = atributoC1;
    }

    public void metodoProprioC1() {
        System.out.println("Executando metodoProprioC1(). Atributo: " + this.atributoC1);
    }

    public void metodoParaSobrescrever() {
        System.out.println("Método original de C1 (para sobrescrever)");
    }

    public String getAtributoC1() {
        return atributoC1;
    }
}