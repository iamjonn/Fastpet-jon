package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Pet;
import requisito.Fachada;

@SuppressWarnings("unused")
public class TelaPet {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldApelido;
	private JTextField textFieldRaca;
	private JTextField textFieldCpfTutor;
	private JButton buttonListar;
	private JButton buttonCriar;
	private JButton buttonExcluir;
	private JButton buttonSelecionarFoto;
	private JLabel labelMensagem;
	private JLabel labelResultados;
	private byte[] fotoBytes = null;
	private JLabel lblGerenciarPets;

	public TelaPet() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Gerenciar Pets");
		frame.setBounds(100, 100, 642, 513);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 304, 568, 159);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setBackground(Color.YELLOW);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		labelMensagem = new JLabel("");
		labelMensagem.setForeground(Color.BLUE);
		labelMensagem.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(labelMensagem);

		labelResultados = new JLabel("resultados:");
		labelResultados.setForeground(new Color(255, 255, 255));
		labelResultados.setBounds(31, 289, 431, 14);
		frame.getContentPane().add(labelResultados);

		JLabel lblApelido = new JLabel("Apelido:");
		lblApelido.setForeground(new Color(255, 255, 255));
		lblApelido.setHorizontalAlignment(SwingConstants.LEFT);
		lblApelido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApelido.setBounds(31, 87, 71, 14);
		frame.getContentPane().add(lblApelido);

		textFieldApelido = new JTextField();
		textFieldApelido.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldApelido.setBounds(95, 78, 188, 33);
		frame.getContentPane().add(textFieldApelido);

		JLabel lblRaca = new JLabel("Raça:");
		lblRaca.setForeground(new Color(255, 255, 255));
		lblRaca.setHorizontalAlignment(SwingConstants.LEFT);
		lblRaca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRaca.setBounds(203, 134, 63, 14);
		frame.getContentPane().add(lblRaca);

		textFieldRaca = new JTextField();
		textFieldRaca.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldRaca.setBounds(240, 127, 175, 29);
		frame.getContentPane().add(textFieldRaca);

		JLabel lblCpfTutor = new JLabel("CPF do Tutor:");
		lblCpfTutor.setForeground(new Color(255, 255, 255));
		lblCpfTutor.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpfTutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpfTutor.setBounds(318, 87, 90, 14);
		frame.getContentPane().add(lblCpfTutor);
		
		textFieldCpfTutor = new JTextField();
		textFieldCpfTutor.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldCpfTutor.setBounds(402, 78, 188, 33);
		frame.getContentPane().add(textFieldCpfTutor);

		buttonCriar = new JButton("Criar novo pet");
		buttonCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textFieldApelido.getText().isEmpty() || textFieldRaca.getText().isEmpty() || textFieldCpfTutor.getText().isEmpty()) {
						labelMensagem.setText("Apelido, Raça e CPF são obrigatórios.");
						return;
					}
					String apelido = textFieldApelido.getText();
					String raca = textFieldRaca.getText();
					String cpfTutor = textFieldCpfTutor.getText();
					
					// Criar o pet com a foto
					Fachada.cadastrarPet(apelido, raca, cpfTutor); // É necessário adaptar a Fachada para receber a foto.
					
					labelMensagem.setText("Pet cadastrado: " + apelido);
					listagem();
				}
				catch(Exception ex) {
					labelMensagem.setText(ex.getMessage());
				}
			}
		});
		buttonCriar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonCriar.setBounds(240, 171, 162, 33);
		frame.getContentPane().add(buttonCriar);

		buttonListar = new JButton("Listar");
		buttonListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		buttonListar.setBounds(240, 245, 134, 33);
		frame.getContentPane().add(buttonListar);
		
		buttonExcluir = new JButton("Deletar selecionado");
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);
						Fachada.excluirPet(id);
						labelMensagem.setText("Pet apagado.");
						listagem();
					} else {
						labelMensagem.setText("Nenhum pet selecionado.");
					}
				}
				catch(Exception ex) {
					labelMensagem.setText(ex.getMessage());
				}
			}
		});
		buttonExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonExcluir.setBounds(45, 171, 185, 33);
		frame.getContentPane().add(buttonExcluir);

		buttonSelecionarFoto = new JButton("Selecionar Foto");
		buttonSelecionarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						fotoBytes = Files.readAllBytes(selectedFile.toPath());
						labelMensagem.setText("Foto selecionada: " + selectedFile.getName());
					} catch (IOException ex) {
						labelMensagem.setText("Erro ao ler arquivo da foto.");
						fotoBytes = null;
					}
				}
			}
		});
		buttonSelecionarFoto.setBounds(429, 171, 146, 33);
		frame.getContentPane().add(buttonSelecionarFoto);
		
		lblGerenciarPets = new JLabel("Gerenciar pets");
		lblGerenciarPets.setForeground(new Color(255, 255, 255));
		lblGerenciarPets.setHorizontalAlignment(SwingConstants.LEFT);
		lblGerenciarPets.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 44));
		lblGerenciarPets.setBounds(174, 30, 304, 37);
		frame.getContentPane().add(lblGerenciarPets);
	}

	public void listagem() {
		try{
			List<Pet> lista = Fachada.listarPets();
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.addColumn("ID");
			model.addColumn("Apelido");
			model.addColumn("Raça");
			model.addColumn("Tutor");
			model.addColumn("Serviços");

			for(Pet pet : lista) 
				model.addRow(new Object[]{pet.getId(), pet.getApelido(), pet.getRaca(), pet.getTutor().getNome(), pet.getServicos().size()} );
			
			labelResultados.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			labelMensagem.setText(erro.getMessage());
		}
	}
}