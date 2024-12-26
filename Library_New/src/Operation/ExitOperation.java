package Operation;

import BookRack.BookRack;
import javax.swing.JOptionPane;

public class ExitOperation implements IOperation {

    @Override
    public void work(BookRack bookRack) {
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
    }
}
