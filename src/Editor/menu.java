package Editor;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Structure.Constant;
import Structure.Element;
import Structure.ElementCompound;
import Structure.Section;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
class menu extends JFrame {
	private final int tamaño = 17;
	private JTextPane areaTexto;
	private String texto = "#$&";
	private JButton cor,cop,peg,nue,gua,ab,acerca,ayuda;
	private JScrollPane scroll;
	private JComboBox<String> tFuente;
	private JComboBox<String> tEstilo;
	private Font areaFuente;
	private ArrayList<Element> elements = new ArrayList<>();
	private Instances instances;
	private BufferedReader input;

	@SuppressWarnings("deprecation")
	public menu () {
		super("Editor de textos");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\pen.png"));
		setTitle("Editor de texto");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al intentar cargar L&F");
		}
		areaFuente= new Font("Arial", Font.PLAIN, tamaño);
		areaTexto= new JTextPane();
		areaTexto.setFont(areaFuente);
		scroll= new JScrollPane(areaTexto);
		menus ();
		barra();
		barra2();
		
		getContentPane().add(scroll,BorderLayout.CENTER);

		setSize(800,580);
		setVisible(true);
		setLocationRelativeTo(null);
		try {
			instances = new Instances();
			instances.initDictionary();
//			initDictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		show ();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void menus () {
		JMenuBar menus = new JMenuBar();
		JMenu archivo= new JMenu("Archivo");
		JMenuItem nue= new JMenuItem("Nuevo", new ImageIcon("images/nuevo.png"));
		nue.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					nuevo();
				}
			}
		);
		//***************************
		JMenuItem nue1= new JMenuItem("Nuevo", new ImageIcon("images/salir.png"));
		nue1.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				}
			}
		);
		//*******************************
		JMenuItem sal= new JMenuItem("Salir",new ImageIcon("images/salir.gif"));
		sal.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					String d=JOptionPane.showInputDialog("Desea salir y guardar el archivo S/N");
					if (d.equals("s")) {
						guardar();
						System.exit(0);
					}
					else if(d.equals("n")) {
						System.exit(0);
					}
					else {
						JOptionPane.showMessageDialog(null,"Caracter invalido");
					}
				}
			}
		);

		JMenuItem abr= new JMenuItem("Abrir",new ImageIcon("images/abrir.png"));
		abr.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					abrir ();
				}
			}
		);
		JMenuItem guar= new JMenuItem("Guardar",new ImageIcon("images/save.png"));
		guar.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					guardar ();
				}
			}
		);
		JMenu editar= new JMenu("Editar");
		JMenuItem cor= new JMenuItem("Cortar", new ImageIcon("images/cut.png"));
		cor.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cortar ();
				}
			}
		);
		JMenuItem cop= new JMenuItem("Copiar",new ImageIcon("images/copy.png"));
		cop.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					copiar ();
				}
			}
		);
		JMenuItem peg= new JMenuItem ("Pegar",new ImageIcon("images/paste.png"));
		peg.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pegar ();
				}
			}
		);

		JMenu about= new JMenu("Ayuda");
		JMenuItem ayu= new JMenuItem("Ayuda",new ImageIcon("images/bombillo.png"));
		ayu.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					ayuda ();
				}
			}
		);
		JMenuItem acer= new JMenuItem("Acerca de...",new ImageIcon("images/chulo.png"));
		acer.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					acerca();
				}
			}
		);
		archivo.add(nue);
		archivo.add(sal);
		archivo.addSeparator();
		archivo.add(abr);
		archivo.add(guar);
		editar.add(cor);
		editar.add(cop);
		editar.add(peg);
		editar.addSeparator();
		about.add(ayu);
		about.add(acer);
		menus.add(archivo);
		menus.add(editar);
		menus.add(about);
		setJMenuBar(menus);

	}

	public void barra () {
		JToolBar barras= new JToolBar();

		nue= new JButton ();
		nue.setIcon(new ImageIcon("images/nuevo.png"));
		nue.setMargin(new Insets(3,0,0,0));
		nue.setToolTipText("Nuevo");
		nue.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					nuevo ();
				}
			}
		);
		barras.add(nue);

		ab= new JButton();
		ab.setIcon(new ImageIcon("images/abrir.png"));
		ab.setMargin(new Insets(2,0,0,0));
		ab.setToolTipText("Abrir");
		ab.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					abrir ();
				}
			}
		);
		barras.add(ab);

		gua= new JButton();
		gua.setIcon(new ImageIcon("images/save.png"));
		gua.setMargin(new Insets(2,0,0,0));
		gua.setToolTipText("Guardar");
		gua.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					guardar ();
				}
			}
		);
		barras.add(gua);

		barras.addSeparator();

		cor= new JButton();
		cor.setIcon(new ImageIcon("images/cut.png"));
		cor.setMargin(new Insets(2,0,0,0));
		cor.setToolTipText("Cortar");
		cor.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cortar ();
				}
			}
		);
		barras.add(cor);


		cop= new JButton();
		cop.setIcon(new ImageIcon("images/copy.png"));
		cop.setMargin(new Insets(-3,0,0,0));
		cop.setToolTipText("Copiar");
		cop.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					copiar ();
				}
			}
		);
		barras.add(cop);

		peg= new JButton();
		peg.setIcon(new ImageIcon("images/paste.png"));
		peg.setMargin(new Insets(2,0,0,0));
		peg.setToolTipText("Pegar");
		peg.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pegar ();
				}
			}
		);
		barras.add(peg);

		JButton del= new JButton();
		del.setIcon(new ImageIcon("images/borrador.png"));
		del.setMargin(new Insets(2,0,0,0));
		del.setToolTipText("Borrar todo");
		del.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					areaTexto.setText("");
				}
			}
		);
		barras.add(del);

		barras.addSeparator();

		ayuda= new JButton();
		ayuda.setIcon(new ImageIcon("images/bombillo.png"));
		ayuda.setMargin(new Insets(2,0,0,0));
		ayuda.setToolTipText("Ayuda");
		ayuda.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					ayuda ();
				}
			}
		);
		barras.add(ayuda);

		acerca= new JButton();
		acerca.setIcon(new ImageIcon("images/chulo.png"));
		acerca.setMargin(new Insets(5,2,0,0));
		acerca.setToolTipText("Acerca de...");
		acerca.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					acerca ();
				}
			}
		);
		barras.add(acerca);

		barras.addSeparator();

		tFuente= new JComboBox<String>();
		tFuente.addItem("Tamaño Fuente");
		tFuente.addItem("10");
		tFuente.addItem("20");
		tFuente.addItem("30");
		tFuente.addItem("40");
		tFuente.addItem("50");
		tFuente.addItem("Personalizar");
		tFuente.setToolTipText("Tamaño de fuente");
		tFuente.addItemListener(
			new ItemListener () {
				public void itemStateChanged(ItemEvent e) {

					int elegido;
					elegido=tFuente.getSelectedIndex();
					switch (elegido) {

						case 1:
							areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle() , 10);
							areaTexto.setFont(areaFuente);
							break;

						case 2:
							areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle(), 20);
							areaTexto.setFont(areaFuente);
							break;

						case 3:
							areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle(), 30);
							areaTexto.setFont(areaFuente);
							break;

						case 4:
							areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle(), 40);
							areaTexto.setFont(areaFuente);
							break;

						case 5:
							areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle(), 50);
							areaTexto.setFont(areaFuente);
							break;
						case 6:
							int tamaño = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño de la fuente"));
							areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle(), tamaño);
							areaTexto.setFont(areaFuente);
							break;
					}
				}
			}
		);
		
		JButton btnMostrar = new JButton("Filtrar");
		btnMostrar.setIcon(new ImageIcon("images/filter.png"));
		btnMostrar.setToolTipText("Filtrar");
		btnMostrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				boolean selected = false;
				if (!areaTexto.getText().isEmpty()){
					if (!areaTexto.getText().equals(texto)) {
						texto = areaTexto.getText();
						elements.clear();
						if (areaTexto.getSelectedText() != null){
							ElementCompound section = new Section (" ",0);
							section.setContent(instances.createInstances(areaTexto.getSelectedText()));
							elements.add(section);
							System.out.println(elements);
							selected = true;
						}
						else{
							if (!areaTexto.getText().substring(0, 1).equals("\t")){
								ElementCompound section = new Section (" ",0);
								section.setContent(instances.createInstances(areaTexto.getText()));
								elements.add(section);
								selected = true;
							}
							else{
								Reader reader = new Reader(areaTexto.getText(), 0);
								instances.setReader(reader);
								elements.addAll(instances.init(0));
							}
						}

					}
					else{
						if (areaTexto.getSelectedText() != null){
							elements.clear();
							ElementCompound section = new Section (" ",0);
							section.setContent(instances.createInstances("\t"+areaTexto.getSelectedText()));
							elements.add(section);
							selected = true;
						}
					}
					if (!elements.isEmpty()){
						mainwindowFilter op = new mainwindowFilter(elements, selected);
						op.show();
					}
				}
				else
					JOptionPane.showMessageDialog(null,"Ingrese un texto a filtrar");
			}
		});
		barras.add(btnMostrar);
		
		JButton btnIndice = new JButton("Indice");
		btnIndice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!areaTexto.getText().isEmpty()){
					if (areaTexto.getText().substring(0, 1).equals("\t")){
						elements.clear();
						Reader reader = new Reader(areaTexto.getText(), 0);
						instances.setReader(reader);
						elements.addAll(instances.init(0));
						optionsIndex op = new optionsIndex(elements);
						op.show();
					}
					else
						JOptionPane.showMessageDialog(null,"Error de formato");
				}
				else
					JOptionPane.showMessageDialog(null,"Ingrese un texto para generar el índice");
			}
		});
		btnIndice.setIcon(new ImageIcon("images/index.png"));
		btnIndice.setToolTipText("Indice");
		barras.add(btnIndice);
		barras.add(tFuente);

		
		barras.addSeparator();
		
		tEstilo = new JComboBox<String>();
		tEstilo.addItem("Tipo Fuente");
		String [] fuentes;
		fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 1; i < fuentes.length; i++)
			tEstilo.addItem(fuentes[i]);
		
		tEstilo.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (areaTexto.getSelectedText() != null){
							SimpleAttributeSet atributo = new SimpleAttributeSet();
							StyleConstants.setFontFamily(atributo, (String) tEstilo.getSelectedItem());
							StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
							areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
						}
					}
				}
		);
		barras.add(tEstilo);
		getContentPane().add(barras,BorderLayout.NORTH);
	}
	
	public void barra2(){
		JToolBar toolBar = new JToolBar();
		scroll.setColumnHeaderView(toolBar);
		
		
		JToggleButton btnBold = new JToggleButton("");
		btnBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
					if (btnBold.isSelected()){
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setBold(atributo, true);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
					}
					else{
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setBold(atributo, false);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
					}
			}
		});
		toolBar.add(btnBold);
		btnBold.setToolTipText("Negrita");
		btnBold.setIcon(new ImageIcon("images/bold.png"));
		
		JToggleButton btnItalic = new JToggleButton("");
		btnItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
					if (btnBold.isSelected()){
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setItalic(atributo, true);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
					}
					else{
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setItalic(atributo, false);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
					}
			}
		});
		toolBar.add(btnItalic);
		btnItalic.setToolTipText("Cursiva");
		btnItalic.setIcon(new ImageIcon("images/italic.png"));
		
		JToggleButton btnUnderline = new JToggleButton("");
		btnUnderline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
					if (btnBold.isSelected()){
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setUnderline(atributo, true);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
					}
					else{
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setUnderline(atributo, false);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
					}
			}
		});
		toolBar.add(btnUnderline);
		btnUnderline.setToolTipText("Subrayado");
		btnUnderline.setIcon(new ImageIcon("images/underline.png"));
		
		toolBar.addSeparator();
		
		JButton btnBlack = new JButton("     ");
		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.BLACK);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnBlack.setForeground(Color.BLACK);
		btnBlack.setBackground(Color.BLACK);
		btnBlack.setToolTipText("Negro");
		toolBar.add(btnBlack);
		
		toolBar.addSeparator();
		
		JButton btnRed = new JButton("     ");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.RED);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnRed.setBackground(Color.RED);
		btnRed.setForeground(Color.RED);
		btnRed.setToolTipText("Rojo");
		toolBar.add(btnRed);
		
		toolBar.addSeparator();
		
		JButton btnCyan = new JButton("     ");
		btnCyan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.CYAN);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnCyan.setBackground(Color.CYAN);
		btnCyan.setForeground(Color.CYAN);
		btnCyan.setToolTipText("Cyan");
		toolBar.add(btnCyan);
		
		toolBar.addSeparator();
		
		JButton btnDarkGray = new JButton("     ");
		btnDarkGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.DARK_GRAY);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnDarkGray.setForeground(Color.DARK_GRAY);
		btnDarkGray.setBackground(Color.DARK_GRAY);
		btnDarkGray.setToolTipText("Gris oscuro");
		toolBar.add(btnDarkGray);
		
		toolBar.addSeparator();
		
		JButton btnGreen = new JButton("     ");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.GREEN);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnGreen.setForeground(Color.GREEN);
		btnGreen.setBackground(Color.GREEN);
		btnGreen.setToolTipText("Verde");
		toolBar.add(btnGreen);
		
		toolBar.addSeparator();
		
		JButton btnLigthGray = new JButton("     ");
		btnLigthGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.LIGHT_GRAY);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnLigthGray.setForeground(Color.LIGHT_GRAY);
		btnLigthGray.setBackground(Color.LIGHT_GRAY);
		btnLigthGray.setToolTipText("Gris claro");
		toolBar.add(btnLigthGray);
		
		toolBar.addSeparator();
		
		JButton btnMagenta = new JButton("     ");
		btnMagenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.MAGENTA);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnMagenta.setForeground(Color.MAGENTA);
		btnMagenta.setBackground(Color.MAGENTA);
		btnMagenta.setToolTipText("Magenta");
		toolBar.add(btnMagenta);
		
		toolBar.addSeparator();
		
		JButton btnOrange = new JButton("     ");
		btnOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.ORANGE);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnOrange.setBackground(Color.ORANGE);
		btnOrange.setForeground(Color.ORANGE);
		btnOrange.setToolTipText("Naranja");
		toolBar.add(btnOrange);
		
		toolBar.addSeparator();
		
		JButton btnBlue = new JButton("     ");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.BLUE);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnBlue.setForeground(Color.BLUE);
		btnBlue.setBackground(Color.BLUE);
		btnBlue.setToolTipText("Azul");
		toolBar.add(btnBlue);
		
		toolBar.addSeparator();
		
		JButton btnPink = new JButton("     ");
		btnPink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.PINK);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnPink.setForeground(Color.PINK);
		btnPink.setBackground(Color.PINK);
		btnPink.setToolTipText("Rosa");
		toolBar.add(btnPink);
		
		toolBar.addSeparator();
		
		JButton btnYellow = new JButton("     ");
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (areaTexto.getSelectedText() != null)
						StyleConstants.setFontFamily(atributo, areaTexto.getFont().getFontName());
						StyleConstants.setFontSize(atributo, areaTexto.getFont().getSize()); 
						StyleConstants.setForeground(atributo, Color.YELLOW);
						areaTexto.getStyledDocument().setCharacterAttributes(areaTexto.getSelectionStart(),areaTexto.getSelectionEnd()-areaTexto.getSelectionStart(),atributo,false);
			}
		});
		btnYellow.setBackground(Color.YELLOW);
		btnYellow.setForeground(Color.YELLOW);
		btnYellow.setToolTipText("Amarillo");
		toolBar.add(btnYellow);
	}

	public void nuevo () {
		new menu ();
	}

	public void abrir () {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result= fileChooser.showOpenDialog(this);
		if (result== JFileChooser.CANCEL_OPTION) return;
		File name= fileChooser.getSelectedFile();
		if(name.exists()) {
			if (name.isFile()) {
				try {
					input = new BufferedReader(new FileReader (name));
					StringBuffer buffer= new StringBuffer();
					String text;
					areaTexto.setText("");
					while ((text=input.readLine()) !=null)
						buffer.append(text+ "\n");
					areaTexto.setText(buffer.toString());
				}
				catch (IOException ioException) {
					JOptionPane.showMessageDialog(null,"Error en el archivo", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (name.isDirectory ()) {
				String directory[] = name.list();
				areaTexto.setText("\n\nContenido del directorio:\n");
				for (int i=0;i<directory.length; i++)
					areaTexto.setText(directory [i]+"\n");
			}
			else {
				JOptionPane.showMessageDialog(null," No existe "," Error ",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void guardar () {
		JOptionPane.showMessageDialog(null,"¡Por favor no olvide colocar la extension del archivo (*.txt)!");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result= fileChooser.showSaveDialog(this);
		if (result== JFileChooser.CANCEL_OPTION) return;
			File name= fileChooser.getSelectedFile();
			try {
				PrintWriter output= new PrintWriter(new FileWriter( name));
				output.write(areaTexto.getText());
				output.close();
			}
			catch (IOException ioException) {
				JOptionPane.showMessageDialog(null,"Error en el archivo","Error",JOptionPane.ERROR_MESSAGE);
			}
	}

	public void cortar () {
		areaTexto.cut();

	}

	public void copiar () {
		areaTexto.copy();
	}

	public void pegar () {
		areaTexto.paste();
	}

	public void ayuda () {
		JOptionPane.showMessageDialog(null," Nuevo: Abre una nueva ventana\n Abrir: Abre un documento existente\n Guardar: Guarda el documento\n Salir: Salir del programa\n Cortar: ctrl+x\n Copiar: ctrl+c\n Pegar: ctrl+v\n Filtrar: Filtra el documento\n Indice: Genera un índice del documento\n Salir sin guardar: alt+F4");
	}

	public void tamaño_f () {
		int tamaño = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño de la fuente"));
		areaFuente= new Font(areaTexto.getFont().getFontName(), areaTexto.getFont().getStyle(), tamaño);
		areaTexto.setFont(areaFuente);
	}

	public void acerca () {
		JOptionPane.showMessageDialog(null,"Editor de textos\nDesarrollado por:  Rodríguez Nicolás - Leiva Nicolás\nIngenieria de sistemas\nUniversidad Nacional del Centro de la Provincia de Buenos Aires\nE-mail: nicolas.arodriguez02@gmail.com\n             nicoleiva08@gmail.com");
	}
	public String getJTextPane(){
		return areaTexto.getText();
	}

	public static void main (String []args) {
		new menu();
	}
	
}