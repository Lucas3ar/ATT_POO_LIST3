import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    private static ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final double PRECO_BOTIJAO = 100.00;

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Entrega de Gás (24h)");

        while (true) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        fazerPedido();
                        break;
                    case 2:
                        confirmarEntrega();
                        break;
                    case 3:
                        verPedidosPorStatus(StatusPedido.CONFIRMADO);
                        break;
                    case 4:
                        verPedidosPorStatus(StatusPedido.ENTREGUE);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Até logo!");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Confirmar entrega (Atendente)");
        System.out.println("3. Ver pedidos confirmados");
        System.out.println("4. Ver pedidos entregues");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void fazerPedido() {
        try {
            System.out.println("\n--- Novo Pedido ---");
            System.out.print("Digite a data da compra (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();

            System.out.print("Digite a hora da compra (HH:mm): ");
            String horaStr = scanner.nextLine();

            LocalDateTime dataCompra = LocalDateTime.parse(dataStr + " " + horaStr, dtf);

            System.out.print("Digite o endereço de entrega: ");
            String endereco = scanner.nextLine();

            Pedido novoPedido = new Pedido(dataCompra, endereco);

            while (true) {
                System.out.println(novoPedido.getResumoParaConfirmacao());
                System.out.print("Os dados estão corretos? (1: Sim, Confirmar | 2: Alterar Endereço): ");
                int B = scanner.nextInt();
                scanner.nextLine();

                if (B == 2) {
                    System.out.print("Digite o novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    novoPedido.setEnderecoEntrega(novoEndereco);
                } else if (B == 1) {
                    System.out.print("Digite a quantidade de botijões: ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();
                    novoPedido.setQuantidade(qtd);
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            }

            System.out.println("\nCalculando total e entrega...");
            novoPedido.calcularTotal(PRECO_BOTIJAO);
            novoPedido.calcularHoraEntrega();

            System.out.printf("O preço unitário é R$ %.2f\n", PRECO_BOTIJAO);
            System.out.println("A hora prevista de entrega é: " + dtf.format(novoPedido.getHoraEntregaPrevista()));

            System.out.print("Digite o número do cartão de crédito para pagamento: ");
            String cartao = scanner.nextLine();

            novoPedido.confirmarPagamento(cartao);

            listaPedidos.add(novoPedido);

            System.out.println("\nPedido confirmado com sucesso!");
            System.out.println("O CÓDIGO do seu pedido é: " + novoPedido.getCodigoPedido());
            System.out.println(novoPedido);

        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data ou hora inválido. O pedido foi cancelado.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada numérica inválida. O pedido foi cancelado.");
            scanner.nextLine();
        }
    }

    private static void confirmarEntrega() {
        System.out.println("\n--- Confirmar Entrega (Atendente) ---");
        System.out.print("Digite o código do pedido a ser marcado como 'entregue': ");

        try {
            int codigo = scanner.nextInt();
            scanner.nextLine();

            Pedido pedidoEncontrado = buscarPedidoPorCodigo(codigo);

            if (pedidoEncontrado != null) {
                pedidoEncontrado.entregarPedido();
                System.out.println("Sucesso! O status do pedido " + codigo + " foi alterado para ENTREGUE.");
                System.out.println(pedidoEncontrado);
            } else {
                System.out.println("ERRO: Pedido não localizado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: O código deve ser um número.");
            scanner.nextLine();
        }
    }

    private static void verPedidosPorStatus(StatusPedido status) {
        String titulo = (status == StatusPedido.CONFIRMADO) ? "Confirmados" : "Entregues";
        System.out.println("\n--- Lista de Pedidos " + titulo + " ---");

        boolean encontrou = false;

        for (Pedido p : listaPedidos) {
            if (p.getStatus() == status) {
                System.out.println(p);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum pedido encontrado com o status: " + status);
        }
    }

    private static Pedido buscarPedidoPorCodigo(int codigo) {
        for (Pedido p : listaPedidos) {
            if (p.getCodigoPedido() == codigo) {
                return p;
            }
        }
        return null;
    }
}