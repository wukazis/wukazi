package GUI;

import Person.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserOperationPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel; // 用于显示不同的视图
    private User currentUser; // 当前登录用户
    private final String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=BM;encrypt=false";
    private final String dbUser = "sa";
    private final String dbPassword = "123456";

    public UserOperationPanel(User user) {
        this.currentUser = user; // 从外部获取当前用户

        // 使用 CardLayout 来切换视图
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 创建操作按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 借阅图书按钮
        JButton borrowBookButton = new JButton("借阅图书");
        borrowBookButton.addActionListener(e -> borrowBookDialog());

        // 归还图书按钮
        JButton returnBookButton = new JButton("归还图书");
        returnBookButton.addActionListener(e -> returnBookDialog());

        // 将按钮添加到按钮面板
        buttonPanel.add(borrowBookButton);
        buttonPanel.add(returnBookButton);

        // 将按钮面板添加到主界面
        add(buttonPanel, BorderLayout.NORTH);
    }

    /**
     * 弹出对话框借阅图书
     */
    private void borrowBookDialog() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField bookIdField = new JTextField(15);

        panel.add(new JLabel("图书ID："));
        panel.add(bookIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "借阅图书", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String bookId = bookIdField.getText().trim();
            String userId = currentUser.getName(); // 获取当前用户的姓名作为用户ID

            if (bookId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "图书ID不能为空！");
            } else {
                if (borrowBook(bookId, userId)) {
                    JOptionPane.showMessageDialog(null, "借阅成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "借阅失败！");
                }
            }
        }
    }

    /**
     * 借阅图书逻辑
     */
    private boolean borrowBook(String bookId, String userId) {
        String sql = "INSERT INTO jieyue (user_id, book_id, br_time, book_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userId);
                stmt.setString(2, bookId);
                stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // 借阅时间
                stmt.setInt(4, 0); // book_status = 0 表示借出

                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 弹出对话框归还图书
     */
    private void returnBookDialog() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField bookIdField = new JTextField(15);

        panel.add(new JLabel("图书ID："));
        panel.add(bookIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "归还图书", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String bookId = bookIdField.getText().trim();
            String userId = currentUser.getName(); // 获取当前用户的姓名作为用户ID

            if (bookId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "图书ID不能为空！");
            } else {
                if (returnBook(bookId, userId)) {
                    JOptionPane.showMessageDialog(null, "归还成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "归还失败！");
                }
            }
        }
    }

    /**
     * 归还图书逻辑
     */
    private boolean returnBook(String bookId, String userId) {
        String sql = "UPDATE jieyue SET book_status = ?, br_time = ? WHERE user_id = ? AND book_id = ? AND book_status = 0";
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, 1); // book_status = 1 表示已归还
                stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // 归还时间
                stmt.setString(3, userId);
                stmt.setString(4, bookId);

                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
