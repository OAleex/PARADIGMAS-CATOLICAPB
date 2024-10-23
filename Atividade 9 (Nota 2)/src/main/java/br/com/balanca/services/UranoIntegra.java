package br.com.balanca.services;

import br.com.balanca.exceptions.*;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UranoIntegra implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        File directory = new File(pastaArquivoTxt);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new DiretorioInvalidoException("Diretório inválido ou inexistente: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/PRODUTOS.TXT"))) {
            for (Produto produto : produtos) {
                validarProduto(produto);
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ExportacaoFalhouException("Erro ao exportar arquivo para Urano Integra", e);
        }
    }

    private void validarProduto(Produto produto) {
        if (produto.getCodigo() <= 0) {
            throw new CodigoProdutoInvalidoException("Código de produto inválido: " + produto.getCodigo());
        }
        if (produto.getValor() <= 0) {
            throw new ValorProdutoInvalidoException("Valor de produto inválido: " + produto.getValor());
        }
    }

    private String formatarProduto(Produto produto) {
        try {
            String codigo = String.format("%06d", produto.getCodigo());
            String flag = "*";
            String tipo = "9".equals(produto.getTipo()) ? "0" : "6";
            String descricao = String.format("%-20s", produto.getDescricao());
            String preco = String.format("%09.2f", produto.getValor()).replace(".", ",");

            return codigo + flag + tipo + descricao + preco + "00000D";
        } catch (Exception e) {
            throw new FormatoProdutoInvalidoException("Erro ao formatar produto: " + produto.getDescricao());
        }
    }
}
