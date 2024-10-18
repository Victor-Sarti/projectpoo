package br.senac.sp.projectpoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senac.sp.projectpoo.modelo.Marca;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class frameMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField textField_2;
	private JTextField lbLogo;
	private Marca marca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameMarca frame = new frameMarca();
					frame.setVisible(true);
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
		setTitle("Cadastro de Marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
				if(tfNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(frameMarca.this, "Informe o nome", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					tfNome.requestFocus(); //para o cursor ficar piscando 
				}else {
					marca = new Marca();
					marca.setNome(tfNome.getText().trim());
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
		btnLimpar.setBounds(223, 91, 89, 23);
		contentPane.add(btnLimpar);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 145, 401, 105);
		contentPane.add(textField_2);
		
		lbLogo = new JTextField();
		lbLogo.setBackground(Color.LIGHT_GRAY);
		lbLogo.setBounds(322, 11, 89, 89);
		lbLogo.setOpaque(true);
		contentPane.add(lbLogo);
	}
}
