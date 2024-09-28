package Imposto;

import Model.Produto;

public class ICMS extends Imposto {
    @Override
    public double calcular(Produto produto) {
        return produto.getPrecoCaixa() * 0.18;
    }
}
