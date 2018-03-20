package com.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.core.Parameter;
import com.core.WordCount;

//界面
public class View extends JFrame implements ActionListener {
	JButton open = null;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JFileChooser jfc = new JFileChooser();// 文件选择器
	JFileChooser jfc2 = new JFileChooser();// 文件选择器
	JButton btn1 = new JButton("选择");
	JButton btn2 = new JButton("选择");
	JButton exeBtn = new JButton("执行");
	JTextField text = new JTextField(15);
	JTextField text2 = new JTextField(15);
	JPanel main = new JPanel();

	public View() {//设置界面ui
		BoxLayout bl = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(bl);
		this.add(main);
		this.setSize(600, 300);
		panel1 = new JPanel();
		FlowLayout p = new FlowLayout();
		p.setAlignment(FlowLayout.LEFT);
		panel1.setLayout(p);
		panel1.add(new JLabel("选择统计文件"));
		panel1.add(text);
		panel1.add(btn1);

		panel2 = new JPanel();
		panel2.setLayout(p);
		panel2.add(new JLabel("输出文件"));
		panel2.add(text2);
		panel2.add(btn2);

		panel3 = new JPanel();
		panel3.setLayout(p);
		panel3.add(exeBtn);

		main.add(panel1);
		main.add(panel2);
		main.add(panel3);

		jfc.setCurrentDirectory(new File("c:\\"));
		btn1.addActionListener(this);

		jfc2.setCurrentDirectory(new File("d:\\"));
		btn2.addActionListener(this);

		exeBtn.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {//事件处理
		if (e.getSource().equals(btn1)) {//选择源文件
			jfc.setFileSelectionMode(0);
			int state = jfc.showOpenDialog(null);
			if (state == 1) {
				return;
			} else {
				File f = jfc.getSelectedFile();
				text.setText(f.getAbsolutePath());
			}
		} else if (e.getSource().equals(btn2)) {//选择输出文件
			jfc2.setFileSelectionMode(0);
			int state = jfc2.showOpenDialog(null);
			if (state == 1) {
				return;
			} else {
				File f = jfc2.getSelectedFile();
				text2.setText(f.getAbsolutePath());
			}
		} else if (e.getSource().equals(exeBtn)) {//执行
			String fp = text.getText();
			String st = text2.getText();
			File in = new File(fp);
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(st));
				String cmd = "wc.exe -c -l - w -a";
				Parameter p = new Parameter(cmd.split(" "));
				p.parse(in, writer, null);
				WordCount.newInstance().execute(p);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				System.exit(0);
			}
		}
	}
}