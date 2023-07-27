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

import entity.ProductCategory;
import service.ProductCategoryService;
import utils.CustomSwingTextUtil;

public class ProductCategoryRegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productCategoryNameTextField;
	private JButton registerSubmitButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductCategoryRegisterFrame frame = new ProductCategoryRegisterFrame();
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
	public ProductCategoryRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 231, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("카테고리 등록");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 412, 29);
		contentPane.add(titleLabel);
		
		JLabel productCategoryNameLabel = new JLabel("카테고리명");
		productCategoryNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productCategoryNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productCategoryNameLabel.setBounds(12, 69, 66, 15);
		contentPane.add(productCategoryNameLabel);
		
		productCategoryNameTextField = new JTextField();
		productCategoryNameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productCategoryNameTextField.setBounds(90, 62, 334, 29);
		contentPane.add(productCategoryNameTextField);
		productCategoryNameTextField.setColumns(10);
		
		registerSubmitButton = new JButton("등록하기");
		registerSubmitButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		registerSubmitButton.setBounds(12, 114, 412, 39);
		registerSubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productCategoryName = productCategoryNameTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productCategoryName)) { return; }
				if(ProductCategoryService.getInstance().isProductCategoryNameDuplicated(productCategoryName)) {
					JOptionPane.showMessageDialog(contentPane, "이미 존재하는 카테고리명입니다.", "중복 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ProductCategory productCategory = ProductCategory.builder()
						.productCategoryName(productCategoryName)
						.build();
				if(!ProductCategoryService.getInstance().registerProductCategory(productCategory)) {
					JOptionPane.showMessageDialog(contentPane, "카테고리 등록 중 오류가 발생하였습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "새로운 카테고리가 등록하였습니다.", "등록 성공", JOptionPane.PLAIN_MESSAGE);
				CustomSwingTextUtil.clearTextField(productCategoryNameTextField);
			}
		});
		contentPane.add(registerSubmitButton);
	}
}
