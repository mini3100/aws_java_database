package utils;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class CustomSwingComboBoxUtil {

	public static void setComboBoxModel(JComboBox comboBox, List<String> modelList) {
		if(comboBox == null || modelList == null) {
			return;
		}
		String[] modelArray = new String[modelList.size()];

		for (int i = 0; i < modelList.size(); i++) {
			modelArray[i] = modelList.get(i);
		}
		comboBox.setModel(new DefaultComboBoxModel<>(modelArray));
	}

}
