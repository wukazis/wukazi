package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FindOperationPanel extends JPanel {
    private JTextField bookNameField;
    private JTextArea resultArea;
    private JButton searchButton;

    // 数据库配置
    private final String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=BM;encrypt=false";
    private final String dbUser = "sa";
    private final String dbPassword = "123456";

    public FindOperationPanel() {
        setLayout(new BorderLayout());

        // 创建输入面板
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("请输入书名："));
        bookNameField = new JTextField(20);
        inputPanel.add(bookNameField);

        // 创建搜索按钮
        searchButton = new JButton("查找");
        inputPanel.add(searchButton);

        // 创建显示结果的区域
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false); // 禁止编辑
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // 添加组件到面板
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 按钮事件监听
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户输入的书名
                String bookName = bookNameField.getText().trim();
                if (!bookName.isEmpty()) {
                    searchBookFromDatabase(bookName);
                } else {
                    resultArea.setText("书名不能为空，请输入有效的书名！");
                }
            }
        });
    }

    /**
     * 从数据库中模糊查询书籍
     */
    private void searchBookFromDatabase(String bookName) {
        String sql = "SELECT * FROM book WHERE book_name LIKE ?";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 设置查询参数，模糊匹配
            stmt.setString(1, "%" + bookName + "%");

            // 执行查询
            ResultSet rs = stmt.executeQuery();

            // 显示查询结果
            StringBuilder resultBuilder = new StringBuilder();
            while (rs.next()) {
                String id = rs.getString("book_id");
                String name = rs.getString("book_name");
                String author = rs.getString("book_auth");
                String price = rs.getString("book_price");
                resultBuilder.append("ID: ").append(id)
                        .append("  书名: ").append(name)
                        .append("  作者: ").append(author)
                        .append("  价格: ").append(price)
                        .append("\n");
            }

            if (resultBuilder.length() == 0) {
                resultArea.setText("未找到匹配的书籍！");
            } else {
                resultArea.setText(resultBuilder.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("查询书籍时出错！");
        }
    }
}
