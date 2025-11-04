import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {

    private static int proximoCodigo = 1;

    private int codigoPedido;
    private LocalDateTime dataCompra;
    private String enderecoEntrega;
    private int quantidade;
    private double precoUnitario;
    private double totalPedido;
    private LocalDateTime horaEntregaPrevista;
    private String formaPagamento;
    private StatusPedido status;

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Pedido(LocalDateTime dataCompra, String enderecoEntrega) {
        this.codigoPedido = proximoCodigo++;
        this.dataCompra = dataCompra;
        this.enderecoEntrega = enderecoEntrega;
        this.status = StatusPedido.PENDENTE;
        this.quantidade = 0;
        this.totalPedido = 0;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void calcularTotal(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        this.totalPedido = this.quantidade * this.precoUnitario;
    }

    public void calcularHoraEntrega() {
        this.horaEntregaPrevista = this.dataCompra.plusHours(2);
    }

    public void confirmarPagamento(String numeroCartao) {
        this.formaPagamento = numeroCartao;
        this.status = StatusPedido.CONFIRMADO;
    }

    public void entregarPedido() {
        this.status = StatusPedido.ENTREGUE;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getHoraEntregaPrevista() {
        return horaEntregaPrevista;
    }

    @Override
    public String toString() {
        String dataCompraFormatada = (dataCompra != null) ? dtf.format(dataCompra) : "N/D";
        String horaEntregaFormatada = (horaEntregaPrevista != null) ? dtf.format(horaEntregaPrevista) : "N/D";

        return "----------------------------------------\n" +
                "Pedido Cód: " + codigoPedido + " [" + status + "]\n" +
                "----------------------------------------\n" +
                "Data/Hora Compra: " + dataCompraFormatada + "\n" +
                "Endereço: " + enderecoEntrega + "\n" +
                "Quantidade: " + quantidade + " botijões\n" +
                String.format("Preço Unitário: R$ %.2f\n", precoUnitario) +
                String.format("Total: R$ %.2f\n", totalPedido) +
                "Forma Pagamento: " + ((formaPagamento != null) ? "Cartão (Final " + formatarCartao(formaPagamento) + ")" : "N/D") + "\n" +
                "Entrega Prevista: " + horaEntregaFormatada + "\n";
    }

    private String formatarCartao(String cartao) {
        if (cartao == null || cartao.length() < 4) {
            return "****";
        }
        return cartao.substring(cartao.length() - 4);
    }

    public String getResumoParaConfirmacao() {
        return "--- Revise seu Pedido ---\n" +
                "Data/Hora: " + dtf.format(dataCompra) + "\n" +
                "Endereço: " + enderecoEntrega + "\n";
    }
}