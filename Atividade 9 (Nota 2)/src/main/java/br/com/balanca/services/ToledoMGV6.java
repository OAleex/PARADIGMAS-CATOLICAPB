package br.com.balanca.services;

import br.com.balanca.exceptions.*;
import br.com.balanca.interfaces.IBalanca;
import br.com.balanca.models.Produto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToledoMGV6 implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        File directory = new File(pastaArquivoTxt);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new DiretorioInvalidoException("Diretório inválido ou inexistente: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/ITENSMGV.TXT"))) {
            for (Produto produto : produtos) {
                validarProduto(produto);
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ExportacaoFalhouException("Erro ao exportar arquivo para Toledo MGV6", e);
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
            String dept = "01";
            String tipo = "0";
            String codigo = String.format("%06d", produto.getCodigo());
            String preco = String.format("%06d", (int) (produto.getValor() * 100));
            String descricao = String.format("%-50s", produto.getDescricao());

            return dept + tipo + codigo + preco + "000" + descricao +
                    "0000000000|01|                                                                      0000000000000000000000000|0000|0||";
        } catch (Exception e) {
            throw new FormatoProdutoInvalidoException("Erro ao formatar produto: " + produto.getDescricao());
        }
    }
}