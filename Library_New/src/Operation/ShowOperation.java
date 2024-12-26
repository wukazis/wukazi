package Operation;

import BookRack.Book;
import BookRack.BookRack;

public class ShowOperation implements IOperation {
    @Override
    public void work(BookRack bookRack) {
        int usedSize = bookRack.getUsedSize();

        if (usedSize == 0) {
            System.out.println("书架为空，目前没有任何图书。");
            return;
        }

        System.out.println("图书列表如下：");
        for (int i = 0; i < usedSize; i++) {
            Book tempBook = bookRack.getBook(i);
            System.out.println(formatBookInfo(tempBook));  // 格式化图书信息
        }
    }

    // 自定义格式化图书信息输出，增强可读性
    private String formatBookInfo(Book book) {
        return String.format("书名: %-10s | 作者: %-10s | 价格: %-5d | 类型: %-5s | 是否借出: %-5s",
                book.getName(), book.getAuthor(), book.getPrice(), book.getType(), book.isBorrowed() ? "是" : "否");
    }
}
