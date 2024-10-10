import java.util.concurrent.CountDownLatch;

public class ProcessadorCalibracaoCountD implements Runnable {
    private final String[] linhas;
    private final long[] resultados;
    private final int indice;
    private final CountDownLatch latch;

    public ProcessadorCalibracaoCountD(String[] linhas, long[] resultados, int indice, CountDownLatch latch) {
        this.linhas = linhas;
        this.resultados = resultados;
        this.indice = indice;
        this.latch = latch;
    }

    private int valorCalibracao(String linha) {
        Integer primeiroCaractere = null;
        Integer ultimoCaractere = null;

        for (char c : linha.toCharArray()) {
            if (Character.isDigit(c)) {
                if (primeiroCaractere == null) {
                    primeiroCaractere = Character.getNumericValue(c);
                }
                ultimoCaractere = Character.getNumericValue(c);
            }
        }

        if (primeiroCaractere != null && ultimoCaractere != null) {
            return primeiroCaractere * 10 + ultimoCaractere;
        } else {
            return 0;
        }
    }

    @Override
    public void run() {
        long somaLocal = 0;
        for (String linha : linhas) {
            somaLocal += valorCalibracao(linha);
        }
        resultados[indice] = somaLocal;
        latch.countDown();
    }
}
