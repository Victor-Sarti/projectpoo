package br.senac.sp.projectpoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.senac.sp.projectpoo.dao.ConnectionFactory;
import br.senac.sp.projectpoo.dao.MarcaDAO;
import br.senac.sp.projectpoo.modelo.Marca;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class frameMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField textField_2;
	private Marca marca;
	private MarcaDAO dao;
	// janela para salvar ou abrir um arquivo
	private JFileChooser chooser;
	private FileFilter imageFilter;
	private File selecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameMarca frame = new frameMarca();
					frame.setVisible(true);
					ConnectionFactory.getConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frameMarca() {
		dao = new MarcaDAO(ConnectionFactory.getConexao());

		chooser = new JFileChooser();
		imageFilter = new FileNameExtensionFilter("imagens", ImageIO.getReaderFileSuffixes());

		setTitle("Cadastro de Marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 12, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 50, 46, 14);
		contentPane.add(lblNome);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(63, 11, 137, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(63, 49, 249, 20);
		contentPane.add(tfNome);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic('s');
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(frameMarca.this, "Informe o nome", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					tfNome.requestFocus(); // para o cursor ficar piscando
				} else {
					marca = new Marca();
					marca.setNome(tfNome.getText().trim());

					try {
						if (selecionado != null) {
							byte[] imagemBytes = Files.readAllBytes(selecionado.toPath());
							marca.setLogo(imagemBytes);
						}
						dao.inserir(marca);
						limpar();
					} catch (SQLException | IOException e1) {
						JOptionPane.showMessageDialog(frameMarca.this, e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalvar.setBounds(25, 91, 89, 23);
		contentPane.add(btnSalvar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic('e');
		btnExcluir.setBounds(124, 91, 89, 23);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(223, 91, 89, 23);
		contentPane.add(btnLimpar);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 145, 401, 105);
		contentPane.add(textField_2);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chooser.setFileFilter(imageFilter);
					if (chooser.showOpenDialog(frameMarca.this) == JFileChooser.APPROVE_OPTION) {
						selecionado = chooser.getSelectedFile();
						try {
							BufferedImage bufImg = ImageIO.read(selecionado);
							Image imagem = bufImg.getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(), Image.SCALE_SMOOTH);
							ImageIcon imgLabel = new ImageIcon(imagem);
							lbLogo.setIcon(imgLabel);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		lbLogo.setBackground(Color.RED);
		lbLogo.setBounds(339, 23, 113, 87);
		lbLogo.setOpaque(true);
		contentPane.add(lbLogo);
	}

	private void limpar() {
		tfId.setText("");
		tfNome.setText("");
		marca = null;
		tfNome.requestFocus();
	}
}
