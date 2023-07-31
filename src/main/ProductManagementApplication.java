package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frame.ProductCategoryRegisterFrame;
import frame.ProductColorRegisterFrame;
import frame.ProductRegisterFrame;
import frame.ProductSearchFrame;

public class ProductManagementApplication extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductManagementApplication frame = new ProductManagementApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductManagementApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 235, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton productSearchFrameOpenButton = new JButton("상품 조회");
		productSearchFrameOpenButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		productSearchFrameOpenButton.setBounds(12, 50, 412, 34);
		productSearchFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductSearchFrame productSearchFrame = ProductSearchFrame.getInstance();
				productSearchFrame.setVisible(true);
			}
		});
		contentPane.add(productSearchFrameOpenButton);
		
		JButton productRegisterFrameOpenButton = new JButton("상품 등록");
		productRegisterFrameOpenButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		productRegisterFrameOpenButton.setBounds(12, 94, 412, 34);
		productRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductRegisterFrame productRegisterFrame = new ProductRegisterFrame();
				productRegisterFrame.setVisible(true);
			}
		});
		contentPane.add(productRegisterFrameOpenButton);
		
		JButton productColorRegisterFrameOpenButton = new JButton("상품 색상 등록");
		productColorRegisterFrameOpenButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		productColorRegisterFrameOpenButton.setBounds(12, 138, 412, 34);
		productColorRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductColorRegisterFrame productColorRegisterFrame = new ProductColorRegisterFrame();
				productColorRegisterFrame.setVisible(true);
			}
		});
		contentPane.add(productColorRegisterFrameOpenButton);
		
		JButton productCategoryRegisterFrameOpenButton = new JButton("상품 카테고리 등록");
		productCategoryRegisterFrameOpenButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		productCategoryRegisterFrameOpenButton.setBounds(12, 182, 412, 34);
		productCategoryRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductCategoryRegisterFrame productCategoryRegisterFrame = new ProductCategoryRegisterFrame();
				productCategoryRegisterFrame.setVisible(true);
			}
		});
		contentPane.add(productCategoryRegisterFrameOpenButton);
	}
}
