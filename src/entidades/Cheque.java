package entidades;

import java.time.LocalDate;
import util.ChequeStatusEnum;

public class Cheque {

    private int numero;
    private double valor;
    private LocalDate data;
    ChequeStatusEnum status;

/**
 * ================================= Construtor ================================
 * @param numero
 * @param valor
 * @param data
 * @param status 
 */
    public Cheque(int numero, double valor, LocalDate data, ChequeStatusEnum status) {
        this.numero = numero;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }
    
    
/**
 * ================================ Proximo Status =============================
 */    
    public void nextStatus() {
        int x;
        if (status.ordinal() < 2) {
            x  = status.ordinal() + 1;
        }else{
            x = 0;
        }
        this.status = util.ChequeStatusEnum.values()[x];
    }

    public String exibir() {
        String data = this.data.getDayOfMonth() + "/"
                + this.data.getMonth() + "/" + this.data.getYear();
        return "Numero: " + this.numero + "Data: " + data
                + "Valor R$: " + this.valor + "Status: " + this.status;
    }
    
    public String isCompensado() {
        if (this.status == this.status.COMPENSADO) {
            return "SIM";
        }else{
            return "NAO";
        }
    }

    
/**
 * ============================ Gette& Setters ================================= 
 */    
    public int getNumero() {
        return numero;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public ChequeStatusEnum getStatus() {
        return status;
    }
}
