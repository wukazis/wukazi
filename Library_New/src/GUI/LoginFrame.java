package GUI;

import Person.Administrator;
import Person.NormalUser;
import Person.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {

    private JTextField nameField;
    private JPasswordField passwordField;  // 密码输入框
    private JRadioButton adminButton;
    private JRadioButton userButton;
    private JButton loginButton;
    private boolean Status = true;

    // JDBC 配置
    private final String dbURL="jdbc:sqlserver://localhost:1433;databaseName=BM;encrypt=false";  // 替换为实际的数据库信息
    private final String dbUser = "sa";  // 数据库用户名
    private final String dbPassword = "123456";  // 数据库密码

    public LoginFrame() {
        setTitle("图书管理系统 - 登录");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建UI组件
        nameField = new JTextField(20);
        passwordField = new JPasswordField(20);  // 创建密码输入框
        adminButton = new JRadioButton("管理员");
        userButton = new JRadioButton("普通用户");
        loginButton = new JButton("登录");

        // 设置单选按钮组
        ButtonGroup group = new ButtonGroup();
        group.add(adminButton);
        group.add(userButton);

        // 创建面板并布局
        setLayout(new BorderLayout());

        // 上半部分：标签和文本框
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2)); // 4行2列，5px间距

        panel.add(new JLabel("账号："));
        panel.add(nameField);
        panel.add(new JLabel("密码："));
        panel.add(passwordField);  // 密码输入框
        add(panel, BorderLayout.NORTH);

        JPanel bpanel = new JPanel();
        JPanel rolePanel = new JPanel();
        rolePanel.add(adminButton);
        rolePanel.add(userButton);
        bpanel.add(rolePanel);
        bpanel.add(loginButton);
        bpanel.setLayout(new GridLayout(2, 1));
        add(bpanel, BorderLayout.SOUTH);

        // 登录按钮的事件监听
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();  // 获取密码
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "账号和密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (adminButton.isSelected()) {
                    // 验证管理员登录
                    if (validateLogin(username, password, "管理员")) {
                        User user = new Administrator(username);
                        openUserMenu(user);
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "管理员账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (userButton.isSelected()) {
                    // 验证普通用户登录
                    if (validateLogin(username, password, "普通用户")) {
                        User user = new NormalUser(username);
                        Status = false;
                        openUserMenu(user);
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "普通用户账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "请选择一个身份", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // 验证登录信息：用户名、密码和角色
    private boolean validateLogin(String username, String password, String role) {
        boolean isValid = false;
        String sql = "";

        // 根据角色选择不同的表进行查询
        if ("管理员".equals(role)) {
            sql = "SELECT * FROM guanliyuan WHERE admin_id = ? AND admin_password = ?";
        } else if ("普通用户".equals(role)) {
            sql = "SELECT * FROM yonghu WHERE user_id = ? AND user_password = ?";
        } else {
            // 如果角色无效，直接返回
            JOptionPane.showMessageDialog(null, "无效的角色", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 连接数据库并执行查询
        try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    isValid = true;  // 如果找到匹配的记录，则验证通过
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }


    // 打开用户菜单界面
    private void openUserMenu(User user) {
        new UserMenuFrame(user, Status).setVisible(true);
        this.dispose(); // 关闭登录窗口
    }

    public boolean checkLogin() {
        return Status;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
