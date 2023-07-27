package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entity.Product;
import entity.ProductCategory;
import entity.ProductColor;
import service.ProductCategoryService;
import service.ProductColorService;
import service.ProductService;
import utils.CustomSwingComboBoxUtil;
import utils.CustomSwingTextUtil;

public class ProductRegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productNameTextField;
	private JTextField productPriceTextField;
	private JButton registerSubmitButton;
	private JComboBox colorComboBox;
	private JComboBox categoryComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductRegisterFrame frame = new ProductRegisterFrame();
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
	public ProductRegisterFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 277);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 231, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 등록");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 412, 29);
		contentPane.add(titleLabel);
		
		JLabel productNameLabel = new JLabel("상품명");
		productNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productNameLabel.setBounds(12, 57, 66, 15);
		contentPane.add(productNameLabel);
		
		productNameTextField = new JTextField();
		productNameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productNameTextField.setBounds(90, 50, 334, 29);
		contentPane.add(productNameTextField);
		productNameTextField.setColumns(10);
		
		JLabel productPriceLabel = new JLabel("가격");
		productPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productPriceLabel.setBounds(12, 93, 66, 15);
		contentPane.add(productPriceLabel);
		
		productPriceTextField = new JTextField();
		productPriceTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productPriceTextField.setColumns(10);
		productPriceTextField.setBounds(90, 86, 334, 29);
		contentPane.add(productPriceTextField);
		
		JLabel productColorLabel = new JLabel("색상");
		productColorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productColorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productColorLabel.setBounds(12, 130, 66, 15);
		contentPane.add(productColorLabel);
		
		colorComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(colorComboBox, ProductColorService.getInstance().getProductColorNameList());
		colorComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		colorComboBox.setBounds(90, 125, 334, 29);
		contentPane.add(colorComboBox);
		
		JLabel productCategoryLabel = new JLabel("카테고리");
		productCategoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productCategoryLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productCategoryLabel.setBounds(12, 165, 66, 15);
		contentPane.add(productCategoryLabel);
		
		categoryComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(categoryComboBox, ProductCategoryService.getInstance().getProductCategoryNameList());
		categoryComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		categoryComboBox.setBounds(90, 160, 334, 29);
		contentPane.add(categoryComboBox);
		
		registerSubmitButton = new JButton("등록하기");
		registerSubmitButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		registerSubmitButton.setBounds(12, 197, 412, 29);
		registerSubmitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productName = productNameTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productName)) { return; }
				
				String productPrice = productPriceTextField.getText();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productPrice)) { return; }
				
				String productColorName = colorComboBox.getSelectedItem().toString();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productColorName)) { return; }
				
				String productCategoryName = categoryComboBox.getSelectedItem().toString();
				if(CustomSwingTextUtil.isTextEmpty(contentPane, productCategoryName)) { return; }
				
				Product product = Product.builder()
						.productName(productName)
						.productPrice(Integer.parseInt(productPrice))
						.productColor(ProductColor.builder().productColorName(productColorName).build())
						.productCategory(ProductCategory.builder().productCategoryName(productCategoryName).build())
						.build();
				
				if(!ProductService.getInstance().registerProduct(product)) {
					JOptionPane.showMessageDialog(contentPane, "상품 등록 중 오류가 발생하였습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "새로운 상품을 등록하였습니다.", "등록 성공", JOptionPane.PLAIN_MESSAGE);
				CustomSwingTextUtil.clearTextField(productNameTextField);
				CustomSwingTextUtil.clearTextField(productPriceTextField);
				colorComboBox.setSelectedIndex(0);
				categoryComboBox.setSelectedIndex(0);
			}
		});
		contentPane.add(registerSubmitButton);
	}
}
