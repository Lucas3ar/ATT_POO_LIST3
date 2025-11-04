public class Sala {

    private final int LINHAS;
    private final int COLUNAS;
    private String[][] grid;

    public Sala(int linhas, int colunas) {
        this.LINHAS = linhas;
        this.COLUNAS = colunas;
        this.grid = new String[LINHAS][COLUNAS];
    }

    private void limparGrid() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                grid[i][j] = " ";
            }
        }
    }

    public void desenhar(Robo robo) {
        limparGrid();
        grid[robo.getLinha()][robo.getColuna()] = "1";

        System.out.print("+");
        for (int j = 0; j < COLUNAS; j++) {
            System.out.print("-");
        }
        System.out.println("+");

        for (int i = 0; i < LINHAS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUNAS; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("|");
        }

        System.out.print("+");
        for (int j = 0; j < COLUNAS; j++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}