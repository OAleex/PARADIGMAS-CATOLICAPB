package Main;

import Factory.ImpostoFactory;
import Factory.TipoImposto;
import Imposto.Imposto;
import Model.Produto;

public class Main {
    public static void main(String[] args) {
        Produto produto1 = new Produto("Produto Industrial", 10, 100, "1234567890123");
        Produto produto2 = new Produto("Produto Não Industrial", 8, 80, "3216549871230");

        calcularImposto(produto1, TipoImposto.TIPOICMS);
        calcularImposto(produto1, TipoImposto.TIPOIPI);
        calcularImposto(produto2, TipoImposto.TIPOPIS);
        calcularImposto(produto2, TipoImposto.TIPOISS);

        imprimirRelatorio(produto1);
        imprimirRelatorio(produto2);
    }

    private static void calcularImposto(Produto produto, TipoImposto tipoImposto) {
        Imposto imposto = ImpostoFactory.getImposto(tipoImposto);
        double impostoCalculado = imposto.calcular(produto);
        System.out.println("Imposto " + tipoImposto + " calculado para " + produto.getDescricao() + ": R$" + impostoCalculado);
    }

    private static void imprimirRelatorio(Produto produto) {
        System.out.println("\nRelatório do Produto");
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Preço por Lata: R$" + produto.getPrecoLata());
        System.out.println("Preço por Caixa: R$" + produto.getPrecoCaixa());
        System.out.println("Código de Barras: " + produto.getCodigoBarras());
    }
}
