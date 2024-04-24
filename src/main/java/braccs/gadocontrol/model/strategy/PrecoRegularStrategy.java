package braccs.gadocontrol.model.strategy;

import braccs.gadocontrol.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;

public class PrecoRegularStrategy implements PrecoStrategy {

    @Override
    public Double calcularPreco(Double preco) {
        return preco;
    }
}
