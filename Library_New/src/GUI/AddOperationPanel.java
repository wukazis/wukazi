package GUI;

import BookRack.BookRack;
import Operation.AddOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOperationPanel extends JPanel {
    private JTextField nameField, authorField, priceField, typeField;
    private JButton addButton,backButton;
    private BookRack bookRack;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AddOperationPanel(BookRack bookRack, JPanel cardPanel, CardLayout cardLayout) {
        this.bookRack = bookRack;
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        // 使用 BorderLayout 将面板分为上下两部分
        setLayout(new BorderLayout());

        // 上半部分：标签和文本框
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2)); // 4行2列，5px间距

        // 创建标签和文本框
        nameField = new JTextField(20);
        authorField = new JTextField(20);
        priceField = new JTextField(20);
        typeField = new JTextField(20);

        // 添加表单元素
        inputPanel.add(new JLabel("书名："));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("作者："));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("价格："));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("类型："));
        inputPanel.add(typeField);

        // 将输入面板添加到上半部分
        add(inputPanel, BorderLayout.NORTH);

        // 下半部分：提交按钮
        JPanel actionPanel = new JPanel();
        addButton = new JButton("添加图书");
        actionPanel.add(addButton);
        backButton = new JButton("返回");

        // 将按钮面板添加到下半部分
        
        actionPanel.add(addButton);
        actionPanel.add(backButton);
        add(actionPanel, BorderLayout.SOUTH);
        // 按钮点击事件监听
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String author = authorField.getText().trim();
                String priceText = priceField.getText().trim();
                String type = typeField.getText().trim();

                // 验证输入
                if (name.isEmpty() || author.isEmpty() || priceText.isEmpty() || type.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "所有字段都必须填写！", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int price = 0;
                try {
                    price = Integer.parseInt(priceText); // 尝试将价格转换为整数
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "价格必须是一个有效的整数！", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 创建 AddOperation 并执行添加图书操作
                AddOperation addOperation = new AddOperation();
                addOperation.work(bookRack, name, author, price, type); // 执行图书添加操作

                // 显示成功消息
                JOptionPane.showMessageDialog(null, "图书添加成功！", "操作成功", JOptionPane.INFORMATION_MESSAGE);

                // 清空输入框
                nameField.setText("");
                authorField.setText("");
                priceField.setText("");
                typeField.setText("");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 返回按钮面板
                cardLayout.show(cardPanel, "ButtonPanel");
            }
        });
    }
}
