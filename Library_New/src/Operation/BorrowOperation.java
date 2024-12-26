package Operation;

import BookRack.BookRack;
import BookRack.Book;
import java.util.Scanner;

public class BorrowOperation implements IOperation {
    @Override
    public void work(BookRack bookRack) {
        System.out.println("借阅图书操作进行中......");
        Scanner scanner = new Scanner(System.in);

        // 输入要借阅的书名
        System.out.println("请输入您想借阅的书名：");
        String name = scanner.nextLine();

        // 输入验证：书名不能为空
        if (name.trim().isEmpty()) {
            System.out.println("书名不能为空，请重新输入！");
            return ;
        }

        // 遍历整个书架查找图书
        for (int i = 0; i < bookRack.getUsedSize(); i++) {
            Book temp = bookRack.getBook(i);
            if (name.equals(temp.getName())) {
                if (temp.isBorrowed()) {
                    System.out.println("该图书已被借阅，无法借阅！");
                } else {
                    temp.setBorrowed(true); // 标记为已借阅
                    System.out.println("借阅成功！");
                }
                return ;
            }
        }

        // 如果没有找到该图书
        System.out.println("没有查询到您想要借阅的图书，请重新尝试！");
        return ;
    }
}
