package Operation;

import BookRack.BookRack;
import BookRack.Book;
import java.util.Scanner;

public class ReturnOperation implements IOperation {

    @Override
    public void work(BookRack bookRack) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("归还图书操作进行中......");
        System.out.println("请输入您想归还的书名：");
        String name = scanner.nextLine().trim();  // 去除空格

        // 输入验证
        if (name.isEmpty()) {
            System.out.println("书名不能为空，请重新输入！");
            return;
        }

        boolean found = false;
        for (int i = 0; i < bookRack.getUsedSize(); i++) {
            Book tempBook = bookRack.getBook(i);
            if (name.equals(tempBook.getName())) {
                if (tempBook.isBorrowed()) {
                    tempBook.setBorrowed(false); // 设置为未借出
                    System.out.println("归还成功!");
                } else {
                    System.out.println("该书并未被借出，无需归还！");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("没有你要归还的图书：" + name);
        }
    }
}
