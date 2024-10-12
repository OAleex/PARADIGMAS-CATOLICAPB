package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ControleFinanceiro {
    public static Map<String, Double> despesaMap = new HashMap<>();
    public static Map<String, Double> receitaMap = new HashMap<>();
    public static Map<String, Double> provisaoMap = new HashMap<>();

    public static void somarTotais() {
        System.out.println("Detalhes das Despesas:");
        for (Map.Entry<String, Double> entry : despesaMap.entrySet()) {
            System.out.println("Data: " + entry.getKey() + ", Valor Total: " + entry.getValue() + ", Tipo: Despesa");
        }

        System.out.println("\nDetalhes das Receitas:");
        for (Map.Entry<String, Double> entry : receitaMap.entrySet()) {
            System.out.println("Data: " + entry.getKey() + ", Valor Total: " + entry.getValue() + ", Tipo: Receita");
        }

        System.out.println("\nDetalhes das Provisões:");
        for (Map.Entry<String, Double> entry : provisaoMap.entrySet()) {
            System.out.println("Data: " + entry.getKey() + ", Valor Total: " + entry.getValue() + ", Tipo: Provisão");
        }

        double totalDespesas = despesaMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalReceitas = receitaMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalProvisoes = provisaoMap.values().stream().mapToDouble(Double::doubleValue).sum();

        System.out.println("\n\nTotal de despesas: " + totalDespesas);
        System.out.println("Total de receitas: " + totalReceitas);
        System.out.println("Total de provisões: " + totalProvisoes);
    }

    public static class ProcessaFinanceiro implements Runnable {
        private String caminhoCSV;
        private Map<String, Double> financeiroMap;
        private CyclicBarrier barrier;

        public ProcessaFinanceiro(String caminhoCSV, int tipo, Map<String, Double> financeiroMap, CyclicBarrier barrier) {
            this.caminhoCSV = caminhoCSV;
            this.financeiroMap = financeiroMap;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new FileReader(caminhoCSV));
                String linha;
                boolean primeiraLinha = true;

                while ((linha = br.readLine()) != null) {
                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }

                    String[] valores = linha.split(",");
                    if (valores.length < 2) {
                        continue;
                    }

                    String data = valores[0].trim();
                    try {
                        String valorString = valores[1].replace("\"", "").trim();
                        double valor = Double.parseDouble(valorString);

                        financeiroMap.put(data, financeiroMap.getOrDefault(data, 0.0) + valor);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao processar valor: " + valores[1]);
                    }
                }
                br.close();

                barrier.await();
            } catch (IOException | InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
