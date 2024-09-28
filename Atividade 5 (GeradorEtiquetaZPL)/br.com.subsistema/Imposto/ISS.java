package Imposto;

import Model.Produto;

public class ISS extends Imposto {
    @Override
    public double calcular(Produto produto) {
        return produto.getPrecoCaixa() * 0.05;
    }
}
