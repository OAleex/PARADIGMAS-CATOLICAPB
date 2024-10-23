package br.com.balanca.services;

import br.com.balanca.exceptions.*;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilizolaSmart implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        File directory = new File(pastaArquivoTxt);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new DiretorioInvalidoException("Diretório inválido ou inexistente: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/CADTXT.TXT"))) {
            for (Produto produto : produtos) {
                validarProduto(produto);
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ExportacaoFalhouException("Erro ao exportar arquivo para Filizola Smart", e);
        }
    }

    private void validarProduto(Produto produto) {
        if (produto.getCodigo() <= 0) {
            throw new CodigoProdutoInvalidoException("Código de produto inválido: " + produto.getCodigo());
        }
        if (!"9".equals(produto.getTipo()) && !"U".equals(produto.getTipo())) {
            throw new TipoProdutoInvalidoException("Tipo de produto inválido: " + produto.getTipo());
        }
        if (produto.getValor() <= 0) {
            throw new ValorProdutoInvalidoException("Valor de produto inválido: " + produto.getValor());
        }
    }

    private String formatarProduto(Produto produto) {
        try {
            String codigo = String.format("%06d", produto.getCodigo());
            String tipo = "9".equals(produto.getTipo()) ? "P" : "U";
            String descricao = String.format("%-22s", produto.getDescricao());
            String preco = String.format("%07d", (int) (produto.getValor() * 100));
            return codigo + tipo + descricao + preco + "000";
        } catch (Exception e) {
            throw new FormatoProdutoInvalidoException("Erro ao formatar produto: " + produto.getDescricao());
        }
    }
}
