package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class UserManagePanel extends JPanel {
    private DefaultListModel<String> userModel; // 用于存储用户列表
    private JList<String> userList; // 显示用户列表

    // 数据库配置
    private final String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=BM;encrypt=false";
    private final String dbUser = "sa";
    private final String dbPassword = "123456";

    public UserManagePanel() {
        setLayout(new BorderLayout());

        // 用户列表模型
        userModel = new DefaultListModel<>();
        userList = new JList<>(userModel);
        JScrollPane scrollPane = new JScrollPane(userList);

        // 加载用户信息
        loadUsersFromDatabase();

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // 添加用户按钮
        JButton addUserButton = new JButton("添加用户");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserDialog(); // 调用弹出对话框的方法
            }
        });

        // 删除用户按钮
        JButton deleteUserButton = new JButton("删除用户");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedUser = userModel.get(selectedIndex);
                    String userId = selectedUser.split(": ")[1]; // 提取用户ID
                    if (deleteUserFromDatabase(userId)) {
                        userModel.remove(selectedIndex);
                        JOptionPane.showMessageDialog(null, "用户删除成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "删除用户失败！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选择要删除的用户！");
                }
            }
        });

        // 将按钮添加到按钮面板
        buttonPanel.add(addUserButton);
        buttonPanel.add(deleteUserButton);

        // 添加组件到主面板
        add(scrollPane, BorderLayout.CENTER); // 用户列表
        add(buttonPanel, BorderLayout.SOUTH); // 按钮面板
    }

    // 弹出对话框接收新用户信息
    private void addUserDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField userIdField = new JTextField(15);
        JTextField userNameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        panel.add(new JLabel("用户ID："));
        panel.add(userIdField);
        panel.add(new JLabel("用户名："));
        panel.add(userNameField);
        panel.add(new JLabel("密码："));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "添加新用户", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String userId = userIdField.getText().trim();
            String userName = userNameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (userId.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "所有字段都必须填写！");
            } else {
                if (addUserToDatabase(userId, userName, password)) {
                    userModel.addElement("普通用户: " + userId); // 添加到用户列表
                    JOptionPane.showMessageDialog(null, "用户添加成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "用户添加失败！");
                }
            }
        }
    }

    // 从数据库加载用户信息
    private void loadUsersFromDatabase() {
        userModel.clear();
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "SELECT user_id FROM yonghu"; // 假设普通用户表为 `yonghu`
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String username = rs.getString("user_id");
                    userModel.addElement("普通用户: " + username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载用户信息失败！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 添加用户到数据库
    private boolean addUserToDatabase(String userId, String userName, String password) {
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "INSERT INTO yonghu (user_id, user_name, user_password) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userId);
                stmt.setString(2, userName);
                stmt.setString(3, password);
                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 从数据库删除用户
    private boolean deleteUserFromDatabase(String userId) {
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            String sql = "DELETE FROM yonghu WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userId);
                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
