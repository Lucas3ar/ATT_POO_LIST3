public class Robo {

    private int linha;
    private int coluna;
    private int passo;
    private int maxLinha;
    private int maxColuna;

    public Robo(int linha, int coluna, int passo, int maxLinha, int maxColuna) {
        this.linha = linha;
        this.coluna = coluna;
        this.passo = passo;
        this.maxLinha = maxLinha;
        this.maxColuna = maxColuna;
    }

    public void mostrarPosicaoAtual() {
        System.out.println("Posição atual: (Linha: " + this.linha + ", Coluna: " + this.coluna + ")");
    }

    public void andarFrente() {
        int novaLinha = this.linha - this.passo;
        this.linha = Math.max(0, novaLinha);
    }

    public void andarTras() {
        int novaLinha = this.linha + this.passo;
        this.linha = Math.min(this.maxLinha - 1, novaLinha);
    }

    public void andarDireita() {
        int novaColuna = this.coluna + this.passo;
        this.coluna = Math.min(this.maxColuna - 1, novaColuna);
    }

    public void andarEsquerda() {
        int novaColuna = this.coluna - this.passo;
        this.coluna = Math.max(0, novaColuna);
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}