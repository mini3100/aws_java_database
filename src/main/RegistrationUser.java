package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import config.DBConnectionMgr;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationUser extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationUser frame = new RegistrationUser();
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
	public RegistrationUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(215, 234, 228));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		usernameTextField.setBounds(76, 10, 248, 21);
		usernameTextField.setColumns(10);
		contentPane.add(usernameTextField);
		
		passwordTextField = new JTextField();
		passwordTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(76, 42, 248, 21);
		contentPane.add(passwordTextField);
		
		JLabel usernameLabel = new JLabel("아이디");
		usernameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		usernameLabel.setBounds(12, 10, 52, 15);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		passwordLabel.setBounds(12, 42, 52, 15);
		contentPane.add(passwordLabel);
		
		JButton addUserButton = new JButton("추가");
		addUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!insertUser(usernameTextField.getText(), passwordTextField.getText())) {
					JOptionPane.showMessageDialog(contentPane, "유저 추가 실패", "실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				updateUserListTable(table);
			}
		});
		addUserButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		addUserButton.setBounds(12, 80, 312, 23);
		contentPane.add(addUserButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 114, 312, 159);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table.setModel(getUserTableModel());
		scrollPane.setViewportView(table);
	}
	
	public boolean insertUser(String username, String password) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		try {
			con = pool.getConnection();
			
			String sql = "insert into user_tb values(0, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			result = pstmt.executeUpdate() != 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	private void updateUserListTable(JTable jTable) {
		jTable.setModel(getUserTableModel());
	}
	
	public DefaultTableModel getUserTableModel() {
		String [] header = new String[] { "user_id", "user_name", "password" };
		List<List<Object>> userList = getUserList();
		
		Object[][] userModelArray = new Object[userList.size()][userList.get(0).size()];
		for(int i = 0; i < userList.size(); i++) {
			for(int j = 0; j < userList.get(i).size(); j++) {
				userModelArray[i][j] = userList.get(i).get(j);
			}
		}
		
		return new DefaultTableModel(userModelArray, header);
	}
	
	public List<List<Object>> getUserList() {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<List<Object>> mstList = new ArrayList();
		try {
			con = pool.getConnection();
			String sql = "select * from user_tb";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				List<Object> rowList = new ArrayList();
				rowList.add(rs.getInt(1));
				rowList.add(rs.getString(2));
				rowList.add(rs.getString(3));
				mstList.add(rowList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return mstList;
	}

}
