package appswing;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import requisito.Fachada;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

public class TelaPrincipal {
	private JFrame frame;
	private JMenu mnTutor;
	private JMenu mnPet;
	private JMenu mnServico;
	private JMenu mnConsulta;
	private JLabel lblFastpet;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		initialize();
		frame.setTitle("FastPet"); // Título da janela
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 128, 128));
		frame.setTitle("FastPet");
		frame.setBounds(100, 100, 684, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblFastpet = new JLabel("FastPet");
		lblFastpet.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 79));
		lblFastpet.setHorizontalAlignment(SwingConstants.CENTER);
		lblFastpet.setBounds(108, 97, 434, 227);
		frame.getContentPane().add(lblFastpet);
		
		JLabel lblSelec = new JLabel("Selecione uma das opções do menuBar");
		lblSelec.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelec.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		lblSelec.setBounds(104, 338, 412, 74);
		frame.getContentPane().add(lblSelec);
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuBar.setMargin(new Insets(100, 0, 0, 0));
		frame.setJMenuBar(menuBar);
		
		// Menu Tutor
		mnTutor = new JMenu("Tutor |");
		mnTutor.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 29));
		mnTutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { new TelaTutor(); }
		});
		menuBar.add(mnTutor);

		// Menu Pet
		mnPet = new JMenu("Pet |");
		mnPet.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 29));
		mnPet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { new TelaPet(); }
		});
		menuBar.add(mnPet);

		// Menu Serviço
		mnServico = new JMenu("Serviço |");
		mnServico.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 29));
		mnServico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { new TelaServico(); }
		});
		menuBar.add(mnServico);
		
		// Menu Consultas
		mnConsulta = new JMenu("Consultas |");
		mnConsulta.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 29));
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { new TelaConsulta(); }
		});
		menuBar.add(mnConsulta);
	}
}