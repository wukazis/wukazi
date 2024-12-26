package GUI;

import Person.Administrator;
import Person.NormalUser;
import Person.User;
import BookRack.BookRack;
import javax.swing.*;
import java.awt.*;

public class UserMenuFrame extends JFrame {
    private JTabbedPane tabbedPane; // 选项卡
    private User currentUser;
    private BookRack bookRack;

    public UserMenuFrame(User user,boolean status) {
        this.currentUser = user;
        this.bookRack = new BookRack(); // 创建一个书架实例

        // 设置窗口属性
        setTitle("图书管理系统");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // 初始化选项卡
        if(status) {
            tabbedPane = new JTabbedPane(); // 创建选项卡面板
            tabbedPane.addTab("操作", new OperationPanel(bookRack));
            tabbedPane.addTab("用户管理", new UserManagePanel());
            tabbedPane.addTab("管理员信息", new UserInfoPanel());
            tabbedPane.addTab("退出", new ExitPanel());
        }
        else {
            tabbedPane = new JTabbedPane(); // 创建选项卡面板
            tabbedPane.addTab("操作", new UserOperationPanel(currentUser));
            tabbedPane.addTab("查找图书", new FindOperationPanel());
            tabbedPane.addTab("个人信息", new UserInfoPanel());
            tabbedPane.addTab("退出", new ExitPanel());
        }
        // 添加选项卡到主窗口
        add(tabbedPane, BorderLayout.CENTER);
    }

    // 操作面板
    /*class OperationPanel extends JPanel {
        public OperationPanel() {
            setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            String[] operations = currentUser.getOperationsDescriptions();
            for (String operation : operations) {
                JButton button = new JButton(operation);
                button.addActionListener(e -> {
                    // 执行对应的操作
                    int choice = getOperationIndex(operation);
                    currentUser.doOperation(choice, bookRack);
                });
                add(button);
            }
        }

        private int getOperationIndex(String operation) {
            String[] operations = currentUser.getOperationsDescriptions();
            for (int i = 0; i < operations.length; i++) {
                if (operations[i].equals(operation)) {
                    return i;
                }
            }
            return -1;
        }
    }*/

    // 查看图书面板
    class ViewBooksPanel extends JPanel {
        public ViewBooksPanel() {
            add(new JLabel("这里显示图书信息"));
            // 你可以添加更多的界面元素来展示图书
        }
    }

    // 用户信息面板
    class UserInfoPanel extends JPanel {
        public UserInfoPanel() {
            add(new JLabel("用户信息"));
            add(new JLabel("姓名：" + currentUser.getName()));
            // 展示其他用户信息
        }
    }

    // 退出面板
    class ExitPanel extends JPanel {
        public ExitPanel() {
            JButton exitButton = new JButton("退出");
            exitButton.addActionListener(e -> {
                // 弹出确认对话框
                int option = JOptionPane.showConfirmDialog(null,
                        "您确定要退出系统吗？",
                        "退出确认",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // 如果用户选择 "Yes"
                if (option == JOptionPane.YES_OPTION) {
                    System.out.println("退出系统...");
                    System.exit(0); // 正常退出
                } else {
                    // 如果用户选择 "No"
                    System.out.println("已取消退出操作！");
                }
            });  // 退出程序
            add(exitButton);
        }
    }


}
