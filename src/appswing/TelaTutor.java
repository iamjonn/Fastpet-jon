package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Tutor;
import requisito.Fachada;

@SuppressWarnings("unused")
public class TelaTutor {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldCpf;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JButton buttonListar;
	private JButton buttonCriar;
	private JLabel labelMensagem;
	private JLabel labelResultados;
	private JLabel lblCrieEListe;

	public TelaTutor() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Gerenciar Tutores");
		frame.setBounds(100, 100, 686, 508);
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
		scrollPane.setBounds(10, 321, 634, 137);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setBackground(Color.ORANGE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		labelMensagem = new JLabel("");
		labelMensagem.setForeground(Color.BLUE);
		labelMensagem.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(labelMensagem);

		labelResultados = new JLabel("resultados:");
		labelResultados.setForeground(new Color(255, 255, 255));
		labelResultados.setBounds(10, 304, 431, 14);
		frame.getContentPane().add(labelResultados);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(new Color(255, 255, 255));
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCpf.setBounds(10, 100, 71, 14);
		frame.getContentPane().add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldCpf.setBounds(93, 93, 262, 32);
		frame.getContentPane().add(textFieldCpf);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNome.setBounds(10, 184, 71, 14);
		frame.getContentPane().add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldNome.setBounds(93, 177, 262, 33);
		frame.getContentPane().add(textFieldNome);

		buttonCriar = new JButton("Criar novo tutor");
		buttonCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textFieldCpf.getText().isEmpty() || textFieldNome.getText().isEmpty()) {
						labelMensagem.setText("CPF e Nome são obrigatórios.");
						return;
					}
					String cpf = textFieldCpf.getText();
					String nome = textFieldNome.getText();
					String telefone = textFieldTelefone.getText();
					Fachada.cadastrarTutor(cpf, nome, telefone);
					labelMensagem.setText("Tutor cadastrado: " + nome);
					listagem();
				}
				catch(Exception ex) {
					labelMensagem.setText(ex.getMessage());
				}
			}
		});
		buttonCriar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonCriar.setBounds(395, 132, 204, 38);
		frame.getContentPane().add(buttonCriar);

		buttonListar = new JButton("Listar");
		buttonListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		buttonListar.setBounds(264, 260, 125, 33);
		frame.getContentPane().add(buttonListar);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(255, 255, 255));
		lblTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefone.setBounds(10, 144, 82, 14);
		frame.getContentPane().add(lblTelefone);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldTelefone.setBounds(93, 136, 262, 30);
		frame.getContentPane().add(textFieldTelefone);
		
		lblCrieEListe = new JLabel("Crie e liste tutores");
		lblCrieEListe.setForeground(new Color(255, 255, 255));
		lblCrieEListe.setHorizontalAlignment(SwingConstants.LEFT);
		lblCrieEListe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 54));
		lblCrieEListe.setBounds(118, 25, 423, 47);
		frame.getContentPane().add(lblCrieEListe);
	}

	public void listagem() {
		try{
			List<Tutor> lista = Fachada.listarTutores();
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.addColumn("CPF");
			model.addColumn("Nome");
			model.addColumn("Telefone");

			for(Tutor tutor : lista) 
				model.addRow(new Object[]{tutor.getCpf(), tutor.getNome(), tutor.getTelefone()} );
			
			labelResultados.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			labelMensagem.setText(erro.getMessage());
		}
	}
}