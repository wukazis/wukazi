package GUI;

import javax.swing.*;
import java.awt.*;
import Operation.ExitOperation;
import BookRack.BookRack;

public class ExitPanel extends JPanel {

    public ExitPanel(BookRack bookRack) {
        // 创建退出按钮
        JButton exitButton = new JButton("退出");
        exitButton.addActionListener(e -> {
            // 执行退出操作
            ExitOperation exitOperation = new ExitOperation();
            exitOperation.work(bookRack);
        });

        // 添加退出按钮到面板
        setLayout(new BorderLayout());
        add(exitButton, BorderLayout.CENTER);
    }
}
