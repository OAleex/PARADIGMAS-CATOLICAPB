package Main;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import Model.ControleFinanceiro;

public class Main {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                ControleFinanceiro.somarTotais();
            }
        });

        String despesaCSV = "C:/Users/Alex F/source/PARADIGMAS-CATOLICAPB/Atividade 8 (CyclicBarrier)/CSV/despesas.csv";
        String receitaCSV = "C:/Users/Alex F/source/PARADIGMAS-CATOLICAPB/Atividade 8 (CyclicBarrier)/CSV/receitas.csv";
        String provisaoCSV = "C:/Users/Alex F/source/PARADIGMAS-CATOLICAPB/Atividade 8 (CyclicBarrier)/CSV/provisao.csv";
        Thread despesaThread = new Thread(new ControleFinanceiro.ProcessaFinanceiro(despesaCSV, -1, ControleFinanceiro.despesaMap, barrier));
        Thread receitaThread = new Thread(new ControleFinanceiro.ProcessaFinanceiro(receitaCSV, 1, ControleFinanceiro.receitaMap, barrier));
        Thread provisaoThread = new Thread(new ControleFinanceiro.ProcessaFinanceiro(provisaoCSV, 0, ControleFinanceiro.provisaoMap, barrier));

        despesaThread.start();
        receitaThread.start();
        provisaoThread.start();

        despesaThread.join();
        receitaThread.join();
        provisaoThread.join();
    }
}
