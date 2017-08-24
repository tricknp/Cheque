package View;

import entidades.Caixa;
import entidades.Cheque;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ChequeStatusEnum;

public class JFprincipal extends javax.swing.JFrame {

    private final Caixa caixa;
    
/**
 * ================================ Objetos ====================================
 */ 
    private final DefaultTableModel modeloTabela;
    private final Object[] colunas = {" Numero", " Data", " Valor", " Status"};

    public JFprincipal(Caixa caixa) {
        initComponents();
        this.caixa = caixa;
        // configurando o JTable com modelo personalizado
        this.modeloTabela = new DefaultTableModel();
        this.modeloTabela.setColumnIdentifiers(colunas);
        this.jTable_TabelaCheques.setModel(modeloTabela);
    }

    public void updateTabela() {
        this.modeloTabela.setRowCount(0);
        ArrayList<Cheque> cxList = this.caixa.getChequeList();
        Object[] row = new Object[4];

        int count = 0;
        double total = 0.0;
        
        boolean semFundoSelected = false; 
        boolean naoCompensadosSelected = false; 
        boolean compensadosSelected = false; 
        if (jCheckBox_SemFundo.isSelected()) {
            semFundoSelected = true;
        }
        if (jCheckBox_NaoCompensados.isSelected()) {
            naoCompensadosSelected = true;
        }
        if (jCheckBox_Compensados.isSelected()) {
            compensadosSelected = true;
        }
        
        
/**
 * ============================ Mostra todos cheques sem fundo =================
 */        
            for (int i = 0; i < cxList.size(); i++) {
                if (cxList.get(i).getStatus() == ChequeStatusEnum.SEM_FUNDO && semFundoSelected) {
                    // add valores na tabela(cada row é relativo a coluna que sera preenchida)
                    row[0] = "              " + cxList.get(i).getNumero();
                    row[1] = "           " + util.Util.convertLocalDateUStoBR(cxList.get(i).getData());
                    row[2] = NumberFormat.getCurrencyInstance().format(cxList.get(i).getValor());
                    row[3] = cxList.get(i).getStatus();
                    this.modeloTabela.addRow(row);
                    count++;
                    total += cxList.get(i).getValor();
                }
                
/**
 * ===================== Mostra todos os cheques compensados ===================
 */                
                 if (cxList.get(i).getStatus() == ChequeStatusEnum.COMPENSADO && compensadosSelected) {
                    // add valores na tabela(cada row é relativo a coluna que sera preenchida)
                    row[0] = "              " + cxList.get(i).getNumero();
                    row[1] = "           " + util.Util.convertLocalDateUStoBR(cxList.get(i).getData());
                    row[2] = NumberFormat.getCurrencyInstance().format(cxList.get(i).getValor());
                    row[3] = cxList.get(i).getStatus();
                    this.modeloTabela.addRow(row);
                    count++;
                    total += cxList.get(i).getValor();
                }
                 
/**
 * ============ Mostra todos os cheques que não foram compensados ==============
 */
                  if (cxList.get(i).getStatus() == ChequeStatusEnum.NAO_COMPENSADO && naoCompensadosSelected) {
                    // add valores na tabela(cada row é relativo a coluna que sera preenchida)
                    row[0] = "              " + cxList.get(i).getNumero();
                    row[1] = "           " + util.Util.convertLocalDateUStoBR(cxList.get(i).getData());
                    row[2] = NumberFormat.getCurrencyInstance().format(cxList.get(i).getValor());
                    row[3] = cxList.get(i).getStatus();
                    this.modeloTabela.addRow(row);
                    count++;
                    total += cxList.get(i).getValor();
                }
            }

        jTextField_ChequesEncontrados.setText(Integer.toString(count));
        jTextField_Total.setText(NumberFormat.getCurrencyInstance().format(total));
    }

/** 
 * ============================= Cadastra um novo cheque =======================
 */    
    private void cadastrarNovo() {
        if (this.caixa.getChequeList().size() > 50000) {
            JOptionPane.showConfirmDialog(this, "MAXIMO 50.000 Cheques!");
            return;
        }
        new JFcadastroCheque(this).setVisible(true);
    }
    
    
/**
 * ========================= Muda o Status do cheque ===========================
 */    
    private void mudarStatus() {
        int x = jTable_TabelaCheques.getSelectedRow();
        if (x == -1) {
            JOptionPane.showMessageDialog(null, "Cheque não selecionado.");
            return;
        }
        for (int i = x; i < x + jTable_TabelaCheques.getSelectedRowCount(); i++) {
            Object o = jTable_TabelaCheques.getValueAt(i, 0);
            int numeroCheque = Integer.parseInt(String.valueOf(o).substring(14));
            for (int h = 0; h < this.caixa.getChequeList().size(); h++) {
                if (this.caixa.getChequeList().get(h).getNumero() == numeroCheque) {
                    this.caixa.getChequeList().get(h).nextStatus();
                }
            }
        }
        updateTabela();
    }

/**
 * ======================== Método para excluir o cheque =======================
 */    
    private void excluirCheque() {
        int x = jTable_TabelaCheques.getSelectedRow();
        if (x == -1) {
            JOptionPane.showMessageDialog(null, "Cheque não selecionado.");
            return;
        }
        int numeroCheque;
        for (int i = x; i < (x + jTable_TabelaCheques.getSelectedRowCount()); i++) {
                    System.out.println("removu");
            Object o = jTable_TabelaCheques.getValueAt(i, 0);
            numeroCheque = Integer.parseInt(String.valueOf(o).substring(14));
            for (int h = 0; h < this.caixa.getChequeList().size(); h++) {
                if (this.caixa.getChequeList().get(h).getNumero() == numeroCheque) {
                    this.caixa.getChequeList().remove(h);
                    break;
                }
            }
        }
        updateTabela();
    }
    
//==============================================================================
    public Caixa getCaixa() {
        return caixa;
    }

//==============================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_TabelaCheques = new javax.swing.JTable();
        jButton_CadastrarNovo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField_ChequesEncontrados = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Total = new javax.swing.JTextField();
        jCheckBox_SemFundo = new javax.swing.JCheckBox();
        jCheckBox_Compensados = new javax.swing.JCheckBox();
        jCheckBox_NaoCompensados = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton_MudarStatus = new javax.swing.JButton();
        jButton_Excluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Caixa - Lista de cheques");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable_TabelaCheques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "        Numero", "        Data", "        Valor", "        Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_TabelaCheques);

        jButton_CadastrarNovo.setText("Cadastrar novo Cheque");
        jButton_CadastrarNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CadastrarNovoActionPerformed(evt);
            }
        });

        jLabel2.setText("Cheques encontrados:");

        jTextField_ChequesEncontrados.setEditable(false);
        jTextField_ChequesEncontrados.setText("0");

        jLabel3.setText("Total R$:");

        jTextField_Total.setEditable(false);
        jTextField_Total.setText("R$ 0,00");

        jCheckBox_SemFundo.setSelected(true);
        jCheckBox_SemFundo.setText("Sem fundo");
        jCheckBox_SemFundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_SemFundoActionPerformed(evt);
            }
        });

        jCheckBox_Compensados.setSelected(true);
        jCheckBox_Compensados.setText("Compensados");
        jCheckBox_Compensados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_CompensadosActionPerformed(evt);
            }
        });

        jCheckBox_NaoCompensados.setSelected(true);
        jCheckBox_NaoCompensados.setText("Não compensados");
        jCheckBox_NaoCompensados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_NaoCompensadosActionPerformed(evt);
            }
        });

        jButton_MudarStatus.setText("Mudar Status");
        jButton_MudarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MudarStatusActionPerformed(evt);
            }
        });

        jButton_Excluir.setText("X");
        jButton_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ChequesEncontrados))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox_SemFundo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox_Compensados)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox_NaoCompensados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_Total)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_MudarStatus))
                            .addComponent(jButton_CadastrarNovo))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox_SemFundo)
                            .addComponent(jCheckBox_Compensados)
                            .addComponent(jCheckBox_NaoCompensados))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_MudarStatus)
                        .addComponent(jButton_Excluir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_CadastrarNovo)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ChequesEncontrados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFprincipal(new Caixa()).setVisible(true);
            }
        });
    }

    private void jCheckBox_SemFundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_SemFundoActionPerformed
        updateTabela();
    }//GEN-LAST:event_jCheckBox_SemFundoActionPerformed
    private void jCheckBox_CompensadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_CompensadosActionPerformed
        updateTabela();
    }//GEN-LAST:event_jCheckBox_CompensadosActionPerformed
    private void jCheckBox_NaoCompensadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_NaoCompensadosActionPerformed
        updateTabela();
    }//GEN-LAST:event_jCheckBox_NaoCompensadosActionPerformed

    private void jButton_CadastrarNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CadastrarNovoActionPerformed
        cadastrarNovo();
    }//GEN-LAST:event_jButton_CadastrarNovoActionPerformed

    private void jButton_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcluirActionPerformed
        excluirCheque();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jButton_MudarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MudarStatusActionPerformed
        mudarStatus();
    }//GEN-LAST:event_jButton_MudarStatusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_CadastrarNovo;
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_MudarStatus;
    private javax.swing.JCheckBox jCheckBox_Compensados;
    private javax.swing.JCheckBox jCheckBox_NaoCompensados;
    private javax.swing.JCheckBox jCheckBox_SemFundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable_TabelaCheques;
    private javax.swing.JTextField jTextField_ChequesEncontrados;
    private javax.swing.JTextField jTextField_Total;
    // End of variables declaration//GEN-END:variables

}
