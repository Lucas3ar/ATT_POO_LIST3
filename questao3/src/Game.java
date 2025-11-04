import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Dado dado1;
    private Dado dado2;
    private List<Player> jogadores;
    private Ranking ranking;
    private Scanner scanner;

    public Game() {
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.jogadores = new ArrayList<>();
        this.ranking = new Ranking();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        mostrarMenuInicial();
        coletarJogadores();
        jogarRodada();
    }

    private void mostrarMenuInicial() {
        ranking.mostrarTopFive();
        System.out.println("\nPressione QUALQUER TECLA para iniciar o registro de jogadores...");
        scanner.nextLine();
    }

    private void coletarJogadores() {
        System.out.println("\n--- REGISTRO DE JOGADORES ---");

        for (int i = 0; i < 11; i++) {
            String username = validarUsername();
            int aposta = validarAposta();

            this.jogadores.add(new Player(username, aposta));
            System.out.printf("Jogador %s registrado com aposta %d.%n", username, aposta);

            if (i < 10) {
                System.out.print("Deseja adicionar outro jogador? (s/n): ");
                String continuar = scanner.nextLine();
                if (!continuar.equalsIgnoreCase("s")) {
                    break;
                }
            }
        }
    }

    private String validarUsername() {
        while (true) {
            System.out.print("Digite o nome do jogador: ");
            String username = scanner.nextLine();

            if (username.trim().isEmpty()) {
                System.out.println("Erro: O nome de usuário não pode estar vazio.");
                continue;
            }

            boolean jaExiste = false;
            for (Player p : jogadores) {
                if (p.getUsername().equalsIgnoreCase(username)) {
                    jaExiste = true;
                    break;
                }
            }

            if (jaExiste) {
                System.out.println("Erro: Esse nome de usuário já foi escolhido. Tente outro.");
            } else {
                return username;
            }
        }
    }

    private int validarAposta() {
        while (true) {
            try {
                System.out.print("Digite o valor da aposta (entre 2 e 12): ");
                int aposta = Integer.parseInt(scanner.nextLine());
                if (aposta >= 2 && aposta <= 12) {
                    return aposta;
                } else {
                    System.out.println("Erro: A aposta deve ser um número entre 2 e 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
            }
        }
    }

    private void jogarRodada() {
        System.out.println("\n--- LANÇANDO OS DADOS ---");
        int valorDado1 = dado1.lancar();
        int valorDado2 = dado2.lancar();
        int soma = valorDado1 + valorDado2;

        System.out.printf("Dado 1: %d%n", valorDado1);
        System.out.printf("Dado 2: %d%n", valorDado2);
        System.out.printf("SOMA: %d%n", soma);

        List<Player> vencedores = new ArrayList<>();
        for (Player p : jogadores) {
            if (p.getAposta() == soma) {
                vencedores.add(p);
            }
        }

        System.out.println("\n--- RESULTADO ---");
        if (vencedores.isEmpty()) {
            System.out.println("A máquina venceu! Nenhum jogador acertou.");
        } else {
            System.out.println("Temos vencedores:");
            for (Player vencedor : vencedores) {
                System.out.println("- " + vencedor.getUsername());
                ranking.registrarVitoria(vencedor.getUsername());
            }
        }

        System.out.println("\n--- RANKING ATUALIZADO ---");
        ranking.mostrarTopFive();
        scanner.close();
    }
}