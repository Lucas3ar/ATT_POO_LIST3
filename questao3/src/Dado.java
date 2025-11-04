import java.util.Random;

public class Dado {

    private int valor;
    private Random random;

    public Dado() {
        this.random = new Random();
        this.valor = 0;
    }

    public int lancar() {
        this.valor = random.nextInt(6) + 1;
        return this.valor;
    }

    public int getValor() {
        return this.valor;
    }
}