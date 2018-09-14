package Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Filters.Filter;
import Filters.FilterAnd;
import Filters.FilterOr;
import Filters.FilterParagraph;
import Filters.FilterQuantityToList;
import Filters.FilterSentence;
import Filters.FilterWord;
import Filters.FilterWordType;
import Structure.Constant;
import Structure.Element;
import Structure.ElementCompound;
import Visitor.Visitor;
import Visitor.VisitorParagraphType;
import Visitor.VisitorSectionType;
import Visitor.VisitorSentenceType;
import Visitor.VisitorWordType;

@SuppressWarnings("serial")
public class mainwindowFilter extends javax.swing.JFrame {

	private static ArrayList<Element> elements;
	private static boolean selected;
	private Integer cantBox;
	private Filter filtro;
	
    public mainwindowFilter(ArrayList<Element> elements, boolean selected) {
        this.elements = elements;
        this.selected = selected;
        initComponents();
        jButton1.setEnabled(false);
        boxQueMostrar.setEnabled(true);
        boxFiltrarPor.setEnabled(true);
        boxCantidad.setEnabled(true);
        boxTipoPalabra.setEnabled(false);
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Filtrar");
        setLocationByPlatform(true);
        TablaMuestraFiltro.setEnabled(false);
    }

    private void initComponents() {

        boxCantidad = new javax.swing.JComboBox<>();
        boxFiltrarPor = new javax.swing.JComboBox<>();
        boxTipoPalabra = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaMuestraTexto = new javax.swing.JTextArea();
        boxQueMostrar = new javax.swing.JComboBox<>();
        BotonAgregar = new javax.swing.JButton();
        BotonLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMuestraFiltro = new javax.swing.JTable();
        areaMuestraTexto.setLineWrap(true);
        areaMuestraTexto.setWrapStyleWord(true);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(780, 600));
        getContentPane().setLayout(null);

        boxCantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cantidad", "Menor que", "Igual que", "Mayor que" }));
        getContentPane().add(boxCantidad);
        boxCantidad.setBounds(386, 40, 110, 20);
        boxCantidad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Integer elegido = boxCantidad.getSelectedIndex();
						if(elegido!=null){
							try {
							switch (elegido){
							case 0:
								break;
							case 1:
								cantBox = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor."));
								break;
							case 2:
								cantBox = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor."));
								break;
							case 3:
								cantBox = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor."));
								break;
								
							}} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					}
				}
      );

        
        BotonLimpiar.setIcon(new javax.swing.ImageIcon("images/clear.png"));
        BotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiarActionPerformed(evt);
            }

			private void BotonLimpiarActionPerformed(ActionEvent evt) {
				DefaultTableModel tb = (DefaultTableModel) TablaMuestraFiltro.getModel();
		        int a = TablaMuestraFiltro.getRowCount()-1;
		        for (int i = a; i >= 0; i--) {           
		        tb.removeRow(tb.getRowCount()-1);
		        }
				boxCantidad.setSelectedIndex(0);
				boxFiltrarPor.setSelectedIndex(0);
				boxQueMostrar.setSelectedIndex(0);
				boxTipoPalabra.setSelectedIndex(0);
				areaMuestraTexto.setText("");
				boxQueMostrar.setEnabled(true);
				boxTipoPalabra.setEnabled(false);
				jButton1.setEnabled(false);
			}
        });
        getContentPane().add(BotonLimpiar);
        BotonLimpiar.setBounds(382, 71, 50, 30);        
        
        boxFiltrarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtrar Por" }));
        boxFiltrarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFiltrarPorActionPerformed(evt);
            }
        });
        getContentPane().add(boxFiltrarPor);
        boxFiltrarPor.setBounds(258, 40, 110, 20);

        boxTipoPalabra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Adverbio", "Adjetivo", "Pronombre", "Preposicion", "Verbo" }));
        getContentPane().add(boxTipoPalabra);
        boxTipoPalabra.setBounds(543, 40, 120, 20);

        jButton1.setText("Filtrar");
        jButton1.setIcon(new javax.swing.ImageIcon("images/filter.png"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(330, 215, 100, 40);

        areaMuestraTexto.setColumns(20);
        areaMuestraTexto.setRows(5);
        jScrollPane1.setViewportView(areaMuestraTexto);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(80, 280, 605, 230);

        if (selected)
        	boxQueMostrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar", "Palabra", "Sentencia", "Parrafo"}));
        else
        	boxQueMostrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar", "Palabra", "Sentencia", "Parrafo", "Seccion" }));
        boxQueMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxQueMostrarActionPerformed(evt);
            }
        });
        getContentPane().add(boxQueMostrar);
        boxQueMostrar.setBounds(98, 40, 110, 20);

        BotonAgregar.setIcon(new javax.swing.ImageIcon("images\\add.png"));
        BotonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregar);
        BotonAgregar.setBounds(328, 71, 50, 30);

        TablaMuestraFiltro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Muestra", "Filtro", "Cantidad", "Tipo de Palabra", "Compuesto por"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaMuestraFiltro);
        if (TablaMuestraFiltro.getColumnModel().getColumnCount() > 0) {
            TablaMuestraFiltro.getColumnModel().getColumn(0).setResizable(false);
            TablaMuestraFiltro.getColumnModel().getColumn(1).setResizable(false);
            TablaMuestraFiltro.getColumnModel().getColumn(2).setResizable(false);
            TablaMuestraFiltro.getColumnModel().getColumn(3).setResizable(false);
            TablaMuestraFiltro.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(90, 114, 582, 90);

        pack();
    }                       

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	Visitor v = null;
    	areaMuestraTexto.setText("");
    	switch (boxQueMostrar.getSelectedIndex()){
    	case 1:
    		v = new VisitorWordType(filtro);
    		break;
    	case 2:
    		v = new VisitorSentenceType(filtro);
    		break;
    	case 3:
    		v = new VisitorParagraphType(filtro);
    		break;
    	case 4:
    		v = new VisitorSectionType(filtro);
    		break;
    	}
    	
    	for (Element e : getOutVisitor((String)boxQueMostrar.getSelectedItem(), v)) {
			areaMuestraTexto.append("* " + e.toString() + "\n");
		}
    }                                                                          

    private ArrayList<Element> getOutVisitor(String index, Visitor v) {
    	ArrayList<Element> outVisitor = new ArrayList<>();
    	if (!Constant.SECCION.equals(index.toLowerCase()))
    		for (Element e : elements) {
    			outVisitor.addAll((ArrayList<Element>)((ElementCompound)e).visitorPart(v));
    		}
    	else
    		for (Element e : elements) {
    			outVisitor.addAll((ArrayList<Element>)((ElementCompound)e).accept(v));
		}
    	return outVisitor;
	}

	private void boxQueMostrarActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	 if(((String)boxQueMostrar.getSelectedItem()).toLowerCase().equals(Constant.PARRAFO)){
        	 boxTipoPalabra.setEnabled(false);
             boxFiltrarPor.setEnabled(true);
             boxCantidad.setEnabled(true);
             boxFiltrarPor.removeAllItems();
             boxFiltrarPor.addItem("Filtrar Por");
             boxFiltrarPor.addItem("Sentencia");
             boxFiltrarPor.addItem("Palabra");
           if(((String)boxFiltrarPor.getSelectedItem()).toLowerCase().equals(Constant.PALABRA)){
        	   boxTipoPalabra.setEnabled(true);
           }
           
            }
         if(((String)boxQueMostrar.getSelectedItem()).toLowerCase().equals(Constant.SENTENCIA)){
        	 boxTipoPalabra.setEnabled(false);
             boxFiltrarPor.setEnabled(true);
             boxCantidad.setEnabled(true);
             boxFiltrarPor.removeAllItems();
             boxFiltrarPor.addItem("Filtrar Por");
             boxFiltrarPor.addItem("Palabra");
             
            }
         if(((String)boxQueMostrar.getSelectedItem()).toLowerCase().equals(Constant.SECCION)){
        	 boxTipoPalabra.setEnabled(false);
             boxFiltrarPor.setEnabled(true);
             boxCantidad.setEnabled(true);
             boxFiltrarPor.removeAllItems();
             boxFiltrarPor.addItem("Filtrar Por");
             boxFiltrarPor.addItem("Parrafo");
             boxFiltrarPor.addItem("Sentencia");
             boxFiltrarPor.addItem("Palabra");
             
            }
         if(((String)boxQueMostrar.getSelectedItem()).toLowerCase().equals(Constant.PALABRA)){
             boxFiltrarPor.setEnabled(false);
             boxCantidad.setEnabled(false);
             boxTipoPalabra.setEnabled(true);
         }
         
        
    }                                             

    private void boxFiltrarPorActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	if(boxFiltrarPor.getItemCount()>0){
    	      if (((String)boxFiltrarPor.getSelectedItem()).toLowerCase().equals(Constant.PALABRA)){
    	          boxTipoPalabra.setEnabled(true);
    	      }
    	      else
    	    	  if (!((String)boxQueMostrar.getSelectedItem()).toLowerCase().equals(Constant.PALABRA))
    	    		  boxTipoPalabra.setEnabled(false);
    	    }
    }                                             

    private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {
    	String queMostrar = "";
    	String cantAux = "";
    	String filtrarPor = "";
    	String tipoPalabra = "";
    	boolean error = false;
    	
    	boxQueMostrar.setEnabled(false);
    	switch (boxQueMostrar.getSelectedIndex()){
    	case 0:
    		JOptionPane.showMessageDialog(null, "Seleccione un tipo de elemento a mostrar.");
    		if (TablaMuestraFiltro.getRowCount() == 0)
    			boxQueMostrar.setEnabled(true);
    		error = true;
    		break;
    	case 1:
    		queMostrar = (String)boxQueMostrar.getSelectedItem();
			tipoPalabra = (String)boxTipoPalabra.getSelectedItem();
			jButton1.setEnabled(true);
			break;
    	default:
    		if (boxCantidad.getSelectedIndex() == 0){
    			JOptionPane.showMessageDialog(null, "Seleccione una cantidad.");
    			if (TablaMuestraFiltro.getRowCount() == 0)
        			boxQueMostrar.setEnabled(true);
    			error = true;
    			break;
    		}
    		if (boxFiltrarPor.getSelectedIndex() == 0){
    			JOptionPane.showMessageDialog(null, "Seleccione un elemento a filtrar.");
    			if (TablaMuestraFiltro.getRowCount() == 0)
        			boxQueMostrar.setEnabled(true);
    			error = true;
    			break;
    		}
    		if (cantBox != null)
    			cantAux = boxCantidad.getSelectedItem() + ": " + cantBox.toString();
    		else{
    			if (TablaMuestraFiltro.getRowCount() == 0)
        			boxQueMostrar.setEnabled(true);
    			JOptionPane.showMessageDialog(null, "Seleccione una cantidad.");
    			boxCantidad.setSelectedIndex(0);
    			error = true;
    			break;
    		}
    		queMostrar = (String)boxQueMostrar.getSelectedItem();
			filtrarPor = (String)boxFiltrarPor.getSelectedItem();
			if (boxTipoPalabra.isEnabled())
				tipoPalabra = (String)boxTipoPalabra.getSelectedItem();
			jButton1.setEnabled(true);
    	}

    	if (!error){
	    	if (TablaMuestraFiltro.getRowCount() >= 1){
	        	Object opcion = JOptionPane.showInputDialog(null,"Seleccione un filtro",
	        			  "Filtro", JOptionPane.QUESTION_MESSAGE, null,
	        			  new Object[] {"And", "Or"},"Seleccione");
	        	if (opcion != null){
		        	DefaultTableModel modelo = (DefaultTableModel) TablaMuestraFiltro.getModel();
		        	Object[] datos = {queMostrar, filtrarPor , cantAux, tipoPalabra, opcion};
		        	modelo.addRow(datos);
		        	addFiltro(false, opcion.toString().toLowerCase());
	        	}
	        }
	    	else{
	    		DefaultTableModel modelo = (DefaultTableModel) TablaMuestraFiltro.getModel();
	    		Object[] datos = {queMostrar, filtrarPor , cantAux, tipoPalabra, ""};
	    		modelo.addRow(datos);
	        	addFiltro(true, null);
	    	}
	    	boxCantidad.setSelectedIndex(0);
	    	boxFiltrarPor.setSelectedIndex(0);
	    	boxTipoPalabra.setSelectedIndex(0);
    	}
    	cantBox = null;
    }                                            

    private void addFiltro(boolean conf, String opcion) {
		switch (boxQueMostrar.getSelectedIndex()){
		case 1:
			filtro = new FilterWordType(((String)boxTipoPalabra.getSelectedItem()).toLowerCase());
			break;
		case 2:
			if (boxTipoPalabra.getSelectedIndex() == 0){
				setFilter(new FilterWord(), boxCantidad.getSelectedIndex(), conf, opcion);
			}
			else{
				setFilter(new FilterWordType(((String)boxTipoPalabra.getSelectedItem()).toLowerCase()), boxCantidad.getSelectedIndex(), conf, opcion);
			}
			break;
		case 3:
			if (boxFiltrarPor.getSelectedIndex() == 1)
				setFilter(new FilterSentence(), boxCantidad.getSelectedIndex(), conf, opcion);
			else
				setFilter(new FilterWordType(((String)boxTipoPalabra.getSelectedItem()).toLowerCase()), boxCantidad.getSelectedIndex(), conf, opcion);
			break;
		case 4:
			if (boxFiltrarPor.getSelectedIndex() == 2)
				setFilter(new FilterSentence(), boxCantidad.getSelectedIndex(), conf, opcion);
			else
				if (boxFiltrarPor.getSelectedIndex() == 3)
					setFilter(new FilterWordType(((String)boxTipoPalabra.getSelectedItem()).toLowerCase()), boxCantidad.getSelectedIndex(), conf, opcion);
				else
					setFilter(new FilterParagraph(), boxCantidad.getSelectedIndex(), conf, opcion);
			break;
		}
	}
    
    private void setFilter (Filter f, int umbral, boolean conf, String opcion){
    	switch(umbral){
    	case 0:
    		if (conf)
    			filtro = new FilterQuantityToList(f, Constant.MAYOR, 0);
    		else{
    			if (Constant.AND.equals(opcion))
    				filtro = new FilterAnd(filtro, new FilterQuantityToList(f, Constant.MAYOR, 0));
    			else
    				filtro = new FilterOr(filtro, new FilterQuantityToList(f, Constant.MAYOR, 0));
    		}
    		break;
    	case 1:
    		if (conf)
    			filtro = new FilterQuantityToList(f, Constant.MENOR, cantBox);
    		else{
    			if (Constant.AND.equals(opcion))
    				filtro = new FilterAnd(filtro, new FilterQuantityToList(f, Constant.MENOR, cantBox));
    			else
    				filtro = new FilterOr(filtro, new FilterQuantityToList(f, Constant.MENOR, cantBox));
    		}
    		break;
    	case 2:
    		if (conf)
    			filtro = new FilterQuantityToList(f, Constant.IGUAL, cantBox);
    		else{
    			if (Constant.AND.equals(opcion))
    				filtro = new FilterAnd(filtro, new FilterQuantityToList(f, Constant.IGUAL, cantBox));
    			else
    				filtro = new FilterOr(filtro, new FilterQuantityToList(f, Constant.IGUAL, cantBox));
    		}
    		break;
    	case 3:
    		if (conf)
    			filtro = new FilterQuantityToList(f, Constant.MAYOR, cantBox);
    		else{
    			if (Constant.AND.equals(opcion))
    				filtro = new FilterAnd(filtro, new FilterQuantityToList(f, Constant.MAYOR, cantBox));
    			else
    				filtro = new FilterOr(filtro, new FilterQuantityToList(f, Constant.MAYOR, cantBox));
    		}
    		break;
    	}
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
            java.util.logging.Logger.getLogger(mainwindowFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainwindowFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainwindowFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainwindowFilter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainwindowFilter(elements, selected).setVisible(true);
            }
        });
    }
                    
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JTable TablaMuestraFiltro;
    private javax.swing.JTextArea areaMuestraTexto;
    private javax.swing.JComboBox<String> boxCantidad;
    private javax.swing.JComboBox<String> boxFiltrarPor;
    private javax.swing.JComboBox<String> boxQueMostrar;
    private javax.swing.JComboBox<String> boxTipoPalabra;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;                
}