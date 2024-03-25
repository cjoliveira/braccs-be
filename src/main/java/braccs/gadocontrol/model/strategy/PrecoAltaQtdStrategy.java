package braccs.gadocontrol.model.strategy;

public class PrecoAltaQtdStrategy implements PrecoStrategy {

    private Double descontoPercentual = 0.95;

    @Override
    public Double calcularPreco(Double preco) {
        return preco * this.descontoPercentual;
    }
}
