package utils;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CustomSwingTextUtil {
	
	public static boolean isTextEmpty(Component targetComponent, String str) {	//생성하지 않고 util 형식으로 쓰기 위해 스태틱 메소드로 선언
		if(str != null) {
			if(!str.isBlank()) {
				return false;
			}
		}
		JOptionPane.showMessageDialog(targetComponent, "내용을 입력하세요!", "입력 오류", JOptionPane.ERROR_MESSAGE);
		return true;	//빈 값이면 true
	}
	
	public static void clearTextField(JTextField textField) {
		textField.setText("");
	}
}
