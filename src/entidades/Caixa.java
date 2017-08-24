package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import util.ChequeStatusEnum;

public class Caixa {

    private ArrayList<Cheque> chequeList;

    public Caixa() {
        this.chequeList = new ArrayList<>();
    }

    
/**
 * =========================== Método para cadastrar o cheque ==================
 * @param cheque 
 */    
    public int cadastrarCheque(Cheque cheque) {
        boolean temIgual = false;
        for (int i = 0; i < chequeList.size(); i++) {
            if (chequeList.get(i).getNumero() == cheque.getNumero()) {
                temIgual = true;
                System.out.println("Cheque já cadastrado.");
            }
        }
        for (int i = 0; i < chequeList.size(); i++) {
            if (chequeList.get(i).getData().equals(cheque.getData())) {
                chequeList.get(i).exibir();
            }
        }
        if (temIgual) {
            return -1;
        } else {
            chequeList.add(cheque);
            return chequeList.lastIndexOf(cheque);
        }
    }

/**
 * ========================= Método para gerar o Random ========================
 * @param qtd 
 */    
    public void gerarRandom(int qtd) {
        Random r = new Random();
        for (int i = 0; i < qtd; i++) {
            int numero = r.nextInt(99999) + (1 + r.nextInt(9)) * 100000;
            double valor = r.nextDouble() * (1000 + r.nextInt(5000));
            LocalDate data = LocalDate.now();
            data = data.plusDays(r.nextInt(1000) * (-1 + r.nextInt(1)));
            ChequeStatusEnum status = ChequeStatusEnum.values()[r.nextInt(2)];
            if (r.nextInt(100) <= 10) {
                status = ChequeStatusEnum.SEM_FUNDO;
            }

            Cheque cheque = new Cheque(numero, valor, data, status);
            chequeList.add(cheque);
        }
    }
    
//==============================================================================
    public ArrayList<Cheque> getChequeList() {
        return chequeList;
    }
}
