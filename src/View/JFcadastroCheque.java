package View;

import entidades.Cheque;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;
import util.ChequeStatusEnum;

public class JFcadastroCheque extends javax.swing.JFrame {

    private JFprincipal jfPrincipal;

    public JFcadastroCheque(JFprincipal jfPrincipal) {
        initComponents();
        this.jfPrincipal = jfPrincipal;
    }

/**
 * ======================== Método para cadastrar o cheque =====================
 */    
    private void cadastrarCheque() {
        int numero;
        double valor;
        LocalDate data;
        try {
            numero = Integer.parseInt(jTextField_Numero.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Numero inválido ou não preenchido.");
            return;
        }
        
        try {
            valor = Double.parseDouble(jTextField_Valor.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Valor inválido ou não preenchido.");
            return;
        }

        try {
            data = LocalDate.parse(util.Util.convertLocalDateBRtoUS(jFormattedTextField_Data.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data inválida ou não preenchido.");
            return;
        }

        Random r = new Random();
        Cheque cheque = new Cheque(numero, valor, data, ChequeStatusEnum.values()[r.nextInt(2)]);
        jfPrincipal.getCaixa().cadastrarCheque(cheque);
        jfPrincipal.updateTabela();
        this.dispose();
    }

/**
 * ======================= Método para gerar o random ==========================
 */    
    private void gerarRandom() {
        while (true) {
            String txt = JOptionPane.showInputDialog("Quantos? (max. 10000)");
            if (txt != null) {
                int qtd;
                try {
                    qtd = Integer.parseInt(txt);
                    if (qtd > 10000) {
                        qtd = 10000;
                    }
                    if (qtd >= 0 && qtd <= (50000 - jfPrincipal.getCaixa().getChequeList().size())) {
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
                jfPrincipal.getCaixa().gerarRandom(qtd);
                jfPrincipal.updateTabela();
                this.dispose();
                break;
            } else {
                break;
            }
        }
    }

//==============================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Cadastrar = new javax.swing.JButton();
        jTextField_Numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField_Data = new javax.swing.JFormattedTextField();
        jTextField_Valor = new javax.swing.JTextField();
        jButton_GerarRandom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Caixa - Cadastro de cheque(s)");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });

        jButton_Cadastrar.setText("Cadastrar");
        jButton_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CadastrarActionPerformed(evt);
            }
        });

        jLabel2.setText("Numero:");

        jLabel3.setText("Data(dd/mm/aaaa):");

        jLabel4.setText("Valor R$:");

        jFormattedTextField_Data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jFormattedTextField_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_DataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jButton_Cadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Cancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Valor)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_Valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField_Data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cadastrar)
                    .addComponent(jButton_Cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_GerarRandom.setText("Gerar Random");
        jButton_GerarRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GerarRandomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_GerarRandom)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton_GerarRandom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CadastrarActionPerformed
        cadastrarCheque();
    }//GEN-LAST:event_jButton_CadastrarActionPerformed

    private void jFormattedTextField_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_DataActionPerformed
    }//GEN-LAST:event_jFormattedTextField_DataActionPerformed

    private void jButton_GerarRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GerarRandomActionPerformed
        gerarRandom();
    }//GEN-LAST:event_jButton_GerarRandomActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_CancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cadastrar;
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_GerarRandom;
    private javax.swing.JFormattedTextField jFormattedTextField_Data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_Numero;
    private javax.swing.JTextField jTextField_Valor;
    // End of variables declaration//GEN-END:variables

}
