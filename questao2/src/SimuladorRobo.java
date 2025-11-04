import java.util.Scanner;

public class SimuladorRobo {

    private static final int LINHAS_SALA = 20;
    private static final int COLUNAS_SALA = 40;
    private static final int PASSO_ROBO = 1;

    public static void main(String[] args) {
        Robo R1 = new Robo(0, 0, PASSO_ROBO, LINHAS_SALA, COLUNAS_SALA);
        Sala sala = new Sala(LINHAS_SALA, COLUNAS_SALA);
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            sala.desenhar(R1);
            R1.mostrarPosicaoAtual();

            System.out.println("\nEscolha o movimento:");
            System.out.println("1 - Andar para Frente (Cima)");
            System.out.println("2 - Andar para Trás (Baixo)");
            System.out.println("3 - Andar para Direita");
            System.out.println("4 - Andar para Esquerda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    R1.andarFrente();
                    break;
                case 2:
                    R1.andarTras();
                    break;
                case 3:
                    R1.andarDireita();
                    break;
                case 4:
                    R1.andarEsquerda();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        System.out.println("Simulação encerrada.");
        scanner.close();
    }
}