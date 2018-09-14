/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.SystemColor;

public class mainwindowIndex extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Object> index;
	
    public mainwindowIndex(ArrayList<Object> index) {
    	getContentPane().setBackground(SystemColor.controlHighlight);
    	setResizable(false);
        this.index = index;
		initComponents();
		createIndex();
    }

    private void initComponents() {
    	
        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JEditorPane();
        textPane.setBackground(SystemColor.menu);
        textPane.setEditable(false);
        jLabel1 = new javax.swing.JLabel();
        setIconImage(Toolkit.getDefaultToolkit().getImage("images\\index.png"));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Indice");
        setLocationByPlatform(true);
        
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setName("mainwindowIndex"); // NOI18N
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(null);

        textPane.setFont(new java.awt.Font("Arial", Font.PLAIN, 17)); // NOI18N
        jScrollPane1.setViewportView(textPane);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 90, 493, 440);

        jLabel1.setFont(new java.awt.Font("Tahoma", Font.BOLD , 24)); // NOI18N
        jLabel1.setText("Indice");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(262, 11, 76, 50);

        setLocationRelativeTo(null);
        pack();
    }
	
	private void createIndex(){
		String text = "";
		for (int i = 0; i < index.size(); i+=2) {
			text += (index.get(i) + "\t" + index.get(i+1) + "\n");
		}
		textPane.setText(text);
	}	
	
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainwindowIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainwindowIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainwindowIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainwindowIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainwindowIndex(index).setVisible(true);
            }
        });
    }

    private javax.swing.JEditorPane textPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;

}
