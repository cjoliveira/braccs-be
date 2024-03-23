package braccs.gadocontrol.model.strategy;

public class PrecoAltaQtdStrategy implements PrecoStrategy {

    @Override
    public Double calcularPreco(Double preco) {
        return preco * 0.95;
    }
}
