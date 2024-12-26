package GUI;

import BookRack.BookRack;
import Operation.AddOperation;
import Operation.FindOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel; // 用于显示不同的视图
    private BookRack bookRack;

    public OperationPanel(BookRack bookRack) {
        this.bookRack = bookRack;

        // 使用 CardLayout 来切换视图
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 创建操作按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 添加图书按钮
        JButton addBookButton = new JButton("添加图书");
        addBookButton.addActionListener(e -> cardLayout.show(cardPanel, "AddBookPanel"));

        // 查找图书按钮
        JButton findBookButton = new JButton("查找图书");
        findBookButton.addActionListener(e -> cardLayout.show(cardPanel, "FindBookPanel"));

        // 将按钮添加到按钮面板
        buttonPanel.add(addBookButton);
        buttonPanel.add(findBookButton);

        // 创建添加图书面板（输入表单）
        AddOperationPanel addOperationPanel = new AddOperationPanel(bookRack, cardPanel, cardLayout);





        // 创建查找图书面板（暂时可以简单显示一个消息）
        JPanel findBookPanel = new JPanel();
        findBookPanel.add(new JLabel("查找图书功能"));
        JButton backButton = new JButton("返回");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonPanel"));
        findBookPanel.add(backButton);

        // 将各个面板添加到 cardPanel 中
        cardPanel.add(buttonPanel, "ButtonPanel"); // 默认显示按钮面板
        cardPanel.add(addOperationPanel, "AddBookPanel"); // 添加图书面板
        cardPanel.add(findBookPanel, "FindBookPanel"); // 查找图书面板

        // 设置主布局
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
    }
}
