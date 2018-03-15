package com.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.StringWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.core.Parameter;
import com.core.WordCount;

public class View extends JFrame implements ActionListener {
	JButton open = null;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JFileChooser jfc = new JFileChooser();// 文件选择器
	JButton btn1 = new JButton("选择");
	JButton exeBtn = new JButton("执行");
	JTextArea area = new JTextArea(10, 25);
	JTextField text = new JTextField(15);
	JPanel main = new JPanel();

	public View() {
		BoxLayout bl = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(bl);
		this.add(main);
		this.setSize(600, 300);
		panel1 = new JPanel();
		FlowLayout p = new FlowLayout();
		p.setAlignment(FlowLayout.LEFT);
		panel1.setLayout(p);
		panel1.add(new JLabel("选择文件"));
		panel1.add(text);
		panel1.add(btn1);

		panel2 = new JPanel();
		panel2.setLayout(p);
		panel2.add(exeBtn);
		panel3 = new JPanel();
		panel3.setLayout(p);
		panel3.add(area);

		main.add(panel1);
		main.add(panel2);
		main.add(panel3);
		jfc.setCurrentDirectory(new File("c:\\"));
		btn1.addActionListener(this);
		exeBtn.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn1)) {
			jfc.setFileSelectionMode(0);
			int state = jfc.showOpenDialog(null);
			if (state == 1) {
				return;
			} else {
				File f = jfc.getSelectedFile();
				text.setText(f.getAbsolutePath());
			}
		} else if (e.getSource() == exeBtn) {
			String fp = text.getText();
			Parameter p = new Parameter("wc.exe -c -w -l -a".split(" "));
			StringWriter writer=new StringWriter();
			p.parse(new File(fp),writer);
			WordCount.newInstance().execute(p);
			String s=writer.toString();
			area.setText(s);
		}
	}
}