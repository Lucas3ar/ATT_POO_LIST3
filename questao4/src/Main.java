public class Main {
    public static void main(String[] args) {

        System.out.println("--- Testando C3 ---");
        C3 objC3 = new C3("Objeto C3", false);
        System.out.println("Atributo C1: " + objC3.getAtributoC1());
        System.out.println("Atributo C3: " + objC3.isAtributoC3());
        objC3.metodoProprioC1();
        objC3.metodoProprioC3();
        objC3.metodoParaSobrescrever();
        objC3.metodoI1();
        objC3.metodoI2_A();
        objC3.metodoI2_B();

        System.out.println("\n--- Testando C4 ---");
        C4 objC4 = new C4("Objeto C4", 40, 4.4);
        System.out.println("Atributo C1: " + objC4.getAtributoC1());
        System.out.println("Atributo C2: " + objC4.getAtributoC2());
        System.out.println("Atributo C4: " + objC4.getAtributoC4());
        objC4.metodoProprioC1();
        objC4.metodoProprioC2();
        objC4.metodoProprioC4();
        objC4.metodoParaSobrescrever();
        objC4.metodoSobrecarga();
        objC4.metodoSobrecarga("Teste C4");

        System.out.println("\n--- Testando C5 ---");
        C5 objC5 = new C5("Objeto C5", 50, 500L);
        System.out.println("Atributo C1: " + objC5.getAtributoC1());
        System.out.println("Atributo C2: " + objC5.getAtributoC2());
        System.out.println("Atributo C5: " + objC5.getAtributoC5());
        objC5.metodoProprioC1();
        objC5.metodoProprioC2();
        objC5.metodoProprioC5();
        objC5.metodoParaSobrescrever();
        objC5.metodoI1();

        System.out.println("\n--- Testando Sobrecarga de Construtor ---");
        C4 objC4Padrao = new C4();
        System.out.println("C4 (construtor padrão) Atributo C1: " + objC4Padrao.getAtributoC1());
        System.out.println("C4 (construtor padrão) Atributo C2: " + objC4Padrao.getAtributoC2());
        System.out.println("C4 (construtor padrão) Atributo C4: " + objC4Padrao.getAtributoC4());
    }
}