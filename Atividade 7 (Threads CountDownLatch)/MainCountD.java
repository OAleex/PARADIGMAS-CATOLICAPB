import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MainCountD {
    public static void main(String[] args) {
        long tempoInicial = System.currentTimeMillis();
        String caminhoArquivo = "C:\\\\Users\\\\Alex F\\\\IdeaProjects\\\\calibration java\\\\new_calibration_text.txt";

        String[] calibracoes = lerArquivo(caminhoArquivo);
        if (calibracoes == null) {
            return;
        }

        int numThreads = 8;
        long[] resultados = new long[numThreads];
        int linhasPorThread = calibracoes.length / numThreads;

        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * linhasPorThread;
            int endIndex = (i == numThreads - 1) ? calibracoes.length : (i + 1) * linhasPorThread;
            String[] linhasParaProcessar = new String[endIndex - startIndex];
            System.arraycopy(calibracoes, startIndex, linhasParaProcessar, 0, endIndex - startIndex);

            new Thread(new ProcessadorCalibracaoCountD(linhasParaProcessar, resultados, i, latch)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao aguardar as threads: " + e.getMessage());
        }

        long somaTotal = 0;
        for (long resultado : resultados) {
            somaTotal += resultado;
        }
        int count = calibracoes.length;

        System.out.printf("A soma dos valores é: %d%n", somaTotal);
        System.out.printf("Total de linhas: %d%n", count);

        long tempoFinal = System.currentTimeMillis();
        System.out.printf("Tempo de execução: %.3f ms%n", (tempoFinal - tempoInicial) / 1000.0);
    }

    private static String[] lerArquivo(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            return br.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }
}
