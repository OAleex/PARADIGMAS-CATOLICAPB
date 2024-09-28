package Imposto;

import Model.Produto;

public class PIS extends Imposto {
    @Override
    public double calcular(Produto produto) {
        return produto.getPrecoCaixa() * 0.0065;
    }
}
