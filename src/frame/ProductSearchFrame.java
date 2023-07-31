package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Product;
import service.ProductService;
import utils.CustomSwingTableUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductSearchFrame extends JFrame {
	
	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField searchTextField;
	private DefaultTableModel searchProductTableModel;
	private JTable productTable;
	private JButton productModifyButton;
	private JButton productRemoveButton;
	
	private static ProductSearchFrame instance;
	
	private ProductSearchFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(236, 249, 249));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("상품 조회");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		titleLabel.setBounds(12, 10, 315, 27);
		contentPane.add(titleLabel);

		productModifyButton = new JButton("수정");
		productModifyButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productModifyButton.setBounds(772, 15, 95, 23);
		productModifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!productModifyButton.isEnabled()) { return; }
				
				int productId = Integer.parseInt((String)searchProductTableModel.getValueAt(productTable.getSelectedRow(), 0));
				ProductModifyFrame productModifyFrame = new ProductModifyFrame(productId);
				productModifyFrame.setVisible(true);
			}
		});
		contentPane.add(productModifyButton);
		
		productRemoveButton = new JButton("삭제");
		productRemoveButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productRemoveButton.setBounds(879, 15, 95, 23);
		productRemoveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!productRemoveButton.isEnabled()) { return; }
				int productId = Integer.parseInt((String)searchProductTableModel.getValueAt(productTable.getSelectedRow(), 0));
				if(!ProductService.getInstance().removeProduct(productId)) {
					JOptionPane.showMessageDialog(contentPane, "상품 삭제 중 오류가 발생하였습니다.", "삭제 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "선택한 상품을 삭제하였습니다.", "삭제 성공", JOptionPane.PLAIN_MESSAGE);
				setSearchProductTableModel();
			}
		});
		contentPane.add(productRemoveButton);
		
		JLabel searchLabel = new JLabel("검색");
		searchLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchLabel.setBounds(548, 47, 29, 27);
		contentPane.add(searchLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "상품명", "색상", "카테고리"}));
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		comboBox.setBounds(583, 47, 103, 27);
		contentPane.add(comboBox);
		
		searchTextField = new JTextField();
		searchTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		searchTextField.setColumns(10);
		searchTextField.setBounds(699, 47, 275, 27);
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {	//엔터를 눌렀을 때 검색
					setSearchProductTableModel();
				}
			}
		});
		contentPane.add(searchTextField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 84, 962, 369);
		contentPane.add(scrollPane);
		
		productTable = new JTable();
		productTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productRemoveButton.setEnabled(true);
				productModifyButton.setEnabled(true);
			}
		});
		setSearchProductTableModel();
		scrollPane.setViewportView(productTable);
		
	}
	
	public static ProductSearchFrame getInstance(){
		if(instance == null) {
			instance = new ProductSearchFrame();
		}
		return instance;
	}
	
	public void setSearchProductTableModel() {
		String searchOption = comboBox.getSelectedItem().toString();
		String searchValue = searchTextField.getText();
		
		List<Product> searchProductList = ProductService.getInstance().searchProduct(searchOption, searchValue);
		String[][] searchProductModelArray = CustomSwingTableUtil.searchProductListToArray(searchProductList);	//List<Product> -> String[][]
		
		searchProductTableModel = new DefaultTableModel(
			searchProductModelArray,
			new String[] {
				"product_id", "product_name", "product_price", "product_color_id", "product_color_name", "product_category_id", "product_category_name"
			}
		);
		
		productTable.setModel(searchProductTableModel);
		productRemoveButton.setEnabled(false);
		productModifyButton.setEnabled(false);
	}
	
}
