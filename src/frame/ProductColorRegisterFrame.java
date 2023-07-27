package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.ProductColor;
import service.ProductColorService;
import utils.CustomSwingTextUtil;

public class ProductColorRegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productColorNameTextField;
	private JButton registerSubmitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductColorRegisterFrame frame = new ProductColorRegisterFrame();
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
	public ProductColorRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 201);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 231, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 색상 등록");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 412, 29);
		contentPane.add(titleLabel);
		
		JLabel productColorNameLabel = new JLabel("색상명");
		productColorNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productColorNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productColorNameLabel.setBounds(12, 74, 66, 15);
		contentPane.add(productColorNameLabel);
		
		productColorNameTextField = new JTextField();
		productColorNameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productColorNameTextField.setBounds(90, 67, 334, 29);
		contentPane.add(productColorNameTextField);
		productColorNameTextField.setColumns(10);
		
		registerSubmitButton = new JButton("등록하기");
		registerSubmitButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		registerSubmitButton.setBounds(12, 117, 412, 37);
		registerSubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productColorName = productColorNameTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productColorName)) { return;	}
				if(ProductColorService.getInstance().isProductColorNameDuplicated(productColorName)) {
					JOptionPane.showMessageDialog(contentPane, "이미 존재하는 색상명입니다.", "중복 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ProductColor productColor = ProductColor.builder()
						.productColorName(productColorName)
						.build();
				
				if(!ProductColorService.getInstance().registerProductColor(productColor)) {
					JOptionPane.showMessageDialog(contentPane, "색상 등록 중 오류가 발생하였습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "새로운 색상을 등록하였습니다.", "등록 성공", JOptionPane.PLAIN_MESSAGE);
				CustomSwingTextUtil.clearTextField(productColorNameTextField);
			}
		});
		contentPane.add(registerSubmitButton);
	}
}
