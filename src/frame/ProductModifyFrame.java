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

public class ProductModifyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productNameTextField;
	private JTextField productPriceTextField;
	private JButton registerModifyButton;
	private JComboBox colorComboBox;
	private JComboBox categoryComboBox;
	private JLabel productIdLabel;
	private JTextField productIdTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductModifyFrame frame = new ProductModifyFrame(1);
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
	public ProductModifyFrame(int productId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 231, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 수정");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 412, 29);
		contentPane.add(titleLabel);
		
		productIdLabel = new JLabel("상품번호");
		productIdLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productIdLabel.setBounds(12, 56, 66, 15);
		contentPane.add(productIdLabel);
		
		productIdTextField = new JTextField();
		productIdTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productIdTextField.setColumns(10);
		productIdTextField.setBounds(90, 49, 334, 29);
		productIdTextField.setEnabled(false);
		contentPane.add(productIdTextField);
		
		JLabel productNameLabel = new JLabel("상품명");
		productNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productNameLabel.setBounds(12, 92, 66, 15);
		contentPane.add(productNameLabel);
		
		productNameTextField = new JTextField();
		productNameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productNameTextField.setBounds(90, 85, 334, 29);
		productNameTextField.setColumns(10);
		
		contentPane.add(productNameTextField);
		
		JLabel productPriceLabel = new JLabel("가격");
		productPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productPriceLabel.setBounds(12, 128, 66, 15);
		contentPane.add(productPriceLabel);
		
		productPriceTextField = new JTextField();
		productPriceTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productPriceTextField.setColumns(10);
		productPriceTextField.setBounds(90, 121, 334, 29);
		contentPane.add(productPriceTextField);
		
		JLabel productColorLabel = new JLabel("색상");
		productColorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productColorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productColorLabel.setBounds(12, 165, 66, 15);
		contentPane.add(productColorLabel);
		
		colorComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(colorComboBox, ProductColorService.getInstance().getProductColorNameList());
		colorComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		colorComboBox.setBounds(90, 160, 334, 29);
		contentPane.add(colorComboBox);
		
		JLabel productCategoryLabel = new JLabel("카테고리");
		productCategoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		productCategoryLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productCategoryLabel.setBounds(12, 200, 66, 15);
		contentPane.add(productCategoryLabel);
		
		categoryComboBox = new JComboBox();
		CustomSwingComboBoxUtil.setComboBoxModel(categoryComboBox, ProductCategoryService.getInstance().getProductCategoryNameList());
		categoryComboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		categoryComboBox.setBounds(90, 195, 334, 29);
		contentPane.add(categoryComboBox);
		
		registerModifyButton = new JButton("수정하기");
		registerModifyButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		registerModifyButton.setBounds(12, 234, 412, 29);
		registerModifyButton.addMouseListener(new MouseAdapter() {
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
				
				if(ProductService.getInstance().isProductNameDuplicated(productName)) {
					JOptionPane.showMessageDialog(contentPane, "이미 존재하는 상품명입니다.", "중복 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Product product = Product.builder()
						.productId(productId)
						.productName(productName)
						.productPrice(Integer.parseInt(productPrice))
						.productColor(ProductColor.builder().productColorName(productColorName).build())
						.productCategory(ProductCategory.builder().productCategoryName(productCategoryName).build())
						.build();
				
				if(!ProductService.getInstance().modifyProduct(product)) {
					JOptionPane.showMessageDialog(contentPane, "상품 수정 중 오류가 발생하였습니다.", "수정 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "상품을 수정하였습니다.", "수정 성공", JOptionPane.PLAIN_MESSAGE);
				ProductSearchFrame.getInstance().setSearchProductTableModel();
				dispose();
			}
		});
		contentPane.add(registerModifyButton);
		
		Product product = ProductService.getInstance().getProductByProductId(productId);
		if(product != null) {			
			productIdTextField.setText(Integer.toString(product.getProductId()));
			productNameTextField.setText(product.getProductName());
			productPriceTextField.setText(Integer.toString(product.getProductPrice()));
			colorComboBox.setSelectedItem(product.getProductColor().getProductColorName());
			categoryComboBox.setSelectedItem(product.getProductCategory().getProductCategoryName());
		}
	}
}
