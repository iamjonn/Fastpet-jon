package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Pet;
import modelo.Servico;
import requisito.Fachada;

@SuppressWarnings("unused")
public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton buttonConsultar;
	private JLabel labelMensagem;
	private JLabel labelResultados;
	private JComboBox<String> comboBox;
	private JLabel lblConsultasDePets;

	public TelaConsulta() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Consultas JPQL");
		frame.setBounds(100, 100, 729, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 268, 657, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setBackground(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		labelMensagem = new JLabel("");
		labelMensagem.setForeground(Color.BLUE);
		labelMensagem.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(labelMensagem);

		labelResultados = new JLabel("resultados:");
		labelResultados.setForeground(new Color(255, 255, 255));
		labelResultados.setBounds(31, 243, 431, 14);
		frame.getContentPane().add(labelResultados);

		buttonConsultar = new JButton("Consultar");
		buttonConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index < 0) {
					labelResultados.setText("Consulta não selecionada");
					return;
				}
				
				switch(index) {
					case 0: // Pets por raça
						String raca = JOptionPane.showInputDialog("Digite a raça:");
						List<Pet> resultado1 = Fachada.consultarPetsPorRaca(raca);
						listagemPet(resultado1);
						break;
					case 1: // Pets com mais de N serviços
						String n = JOptionPane.showInputDialog("Digite N:");
						int numero = Integer.parseInt(n);
						List<Pet> resultado2 = Fachada.petsWithMoreThanNservices(numero);
						listagemPet(resultado2);
						break;
					case 2: // Serviços por tipo e data
						String tipo = JOptionPane.showInputDialog("Digite o tipo de serviço:");
						String data = JOptionPane.showInputDialog("Digite a data (dd/MM/yyyy):");
						List<Servico> resultado3 = Fachada.servicesByTipoAndDate(tipo, data);
						listagemServico(resultado3);
						break;
				}
			}
		});
		buttonConsultar.setBounds(403, 129, 166, 41);
		frame.getContentPane().add(buttonConsultar);

		comboBox = new JComboBox<>();
		comboBox.setToolTipText("Selecione a consulta");
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
			"Pets por raça",
			"Pets com mais de N serviços",
			"Serviços por tipo e data"
		}));
		comboBox.setBounds(117, 130, 180, 41);
		frame.getContentPane().add(comboBox);
		
		lblConsultasDePets = new JLabel("Consultas");
		lblConsultasDePets.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 42));
		lblConsultasDePets.setForeground(Color.WHITE);
		lblConsultasDePets.setBounds(257, 48, 190, 41);
		frame.getContentPane().add(lblConsultasDePets);
	}

	public void listagemPet(List<Pet> lista) {
		try{
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

	public void listagemServico(List<Servico> lista) {
		try{
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			model.addColumn("ID");
			model.addColumn("Tipo");
			model.addColumn("Data/Hora");
			
			for(Servico servico : lista) 
				model.addRow(new Object[]{servico.getId(), servico.getTipo(), servico.getDatahora()} );
			
			labelResultados.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			labelMensagem.setText(erro.getMessage());
		}
	}
}