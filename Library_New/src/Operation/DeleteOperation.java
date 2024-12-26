package Operation;

import BookRack.BookRack;
import BookRack.Book;
import java.util.Scanner;

public class DeleteOperation implements IOperation {
    @Override
    public void work(BookRack bookRack) {
        System.out.println("删除图书操作进行中......");
        Scanner scanner = new Scanner(System.in);

        // 输入要删除的书名
        System.out.println("请输入您想删除的书名：");
        String name = scanner.nextLine();

        // 输入验证：书名不能为空
        if (name.trim().isEmpty()) {
            System.out.println("书名不能为空，请重新输入！");
            return ;
        }

        // 遍历书架查找要删除的书
        int usedSize = bookRack.getUsedSize();
        int flag = -1;
        for (int i = 0; i < usedSize; i++) {
            Book tempBook = bookRack.getBook(i);
            if (tempBook.getName().equals(name)) {
                flag = i;
                break;
            }
        }

        if (flag == -1) {
            System.out.println("查无此书，无法删除");
            return ;
        }

        // 删除图书，后续书籍前移
        for (int i = flag; i < usedSize - 1; i++) {
            bookRack.setBook(bookRack.getBook(i + 1), i);
        }

        // 将最后一个位置设为空，防止残留引用
        bookRack.setBook(null, usedSize - 1);
        bookRack.setUsedSize(usedSize - 1); // 图书数量减一
        System.out.println("删除成功！");
        return ;
    }
}
