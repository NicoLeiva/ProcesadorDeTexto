package Editor;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Filters.Filter;
import Filters.FilterAnd;
import Filters.FilterCompound;
import Filters.FilterOr;
import Filters.FilterWordType;
import Structure.Constant;
import Structure.Element;
import Visitor.Visitor;
import Visitor.VisitorSectionIndexWord;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class optionsIndex extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ArrayList<Element> elements;
	private JTable table;
	private Filter filtro;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					optionsIndex frame = new optionsIndex(elements);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public optionsIndex(ArrayList<Element> elements) {
		setTitle("Indice");
		this.elements = elements;
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\index.png"));
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 101, 238, 90);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Atributo", "Compuesto por"
			}
		));
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		JLabel lblSeleccioneElFormato = new JLabel("Seleccione el formato del \u00EDndice");
		lblSeleccioneElFormato.setBounds(100, 25, 157, 14);
		contentPane.add(lblSeleccioneElFormato);
		
		JComboBox<String> boxPalabra1 = new JComboBox<String>();
		boxPalabra1.setModel(new DefaultComboBoxModel<String>(new String[] {"Todas", "Verbo", "Adjetivo", "Adverbio", "Preposición" , "Pronombre"}));
		boxPalabra1.setBounds(86, 62, 79, 23);
		contentPane.add(boxPalabra1);
		setLocationRelativeTo(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visitor v;
				ArrayList<Object> index = new ArrayList<>();
				
				v = new VisitorSectionIndexWord(filtro);
				index = createIndex(v);
				
				mainwindowIndex mainIndex = new mainwindowIndex(index);
				mainIndex.show();
				hide();
				
					
			}
		});
		btnAceptar.setBounds(58, 202, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.setEnabled(false);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hide();
			}
		});
		btnCancelar.setBounds(207, 202, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton buttonAgregar = new JButton("");
		buttonAgregar.setIcon(new javax.swing.ImageIcon("images\\add.png"));
		buttonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAceptar.setEnabled(true);
				if (table.getRowCount() >= 1){
		        	Object opcion = JOptionPane.showInputDialog(null,"Seleccione un tipo",
		        			  "Indice compuesto", JOptionPane.QUESTION_MESSAGE, null,
		        			  new Object[] {"And", "Or"},"Seleccione");
		        	if (opcion != null){
			        	DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			        	Object[] datos = {boxPalabra1.getSelectedItem(), opcion};
			        	modelo.addRow(datos);
			        	addIndex(false, opcion.toString().toLowerCase(), boxPalabra1.getSelectedItem().toString());
			        }
		        }
		    	else{
		    		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		    		Object[] datos = {boxPalabra1.getSelectedItem(), ""};
		    		modelo.addRow(datos);
		        	addIndex(true, null, boxPalabra1.getSelectedItem().toString());
		    	}
			}
		});
		buttonAgregar.setBounds(220, 62, 40, 23);
		contentPane.add(buttonAgregar);
	}
	
	private void addIndex(boolean conf, String opcion, String atributo) {
		if (conf){
			filtro = new FilterWordType(atributo.toLowerCase());
		}
		else{
			if (Constant.AND.equals(opcion)){
				filtro = new FilterAnd(filtro, new FilterWordType(atributo.toLowerCase()));
			}
			else
				filtro = new FilterOr(filtro, new FilterWordType(atributo.toLowerCase()));
		}
		
	}
	
	private ArrayList<Object> createIndex (Visitor v){
		ArrayList<Object> aux = new ArrayList<>();
		
		for (Element elem : elements) {
			aux.addAll(((ArrayList<Object>)elem.accept(v)));
		}
		
		return aux;
	}

}
