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
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Servico;
import requisito.Fachada;

@SuppressWarnings("unused")
public class TelaServico {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton buttonListar;
	private JLabel labelMensagem;
	private JLabel labelResultados;
	private JLabel lblListarServios;

	public TelaServico() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Gerenciar Serviços");
		frame.setBounds(100, 100, 729, 385);
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
		scrollPane.setBounds(21, 187, 674, 148);
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
		labelResultados.setBounds(21, 172, 431, 14);
		frame.getContentPane().add(labelResultados);

		buttonListar = new JButton("Listar");
		buttonListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		buttonListar.setBounds(282, 128, 139, 33);
		frame.getContentPane().add(buttonListar);
		
		lblListarServios = new JLabel("Listar serviços");
		lblListarServios.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 42));
		lblListarServios.setForeground(Color.WHITE);
		lblListarServios.setBounds(207, 41, 301, 55);
		frame.getContentPane().add(lblListarServios);
	}

	public void listagem() {
		try{
			List<Servico> lista = Fachada.listarServicos();
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