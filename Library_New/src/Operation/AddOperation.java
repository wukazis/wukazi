package Operation;

import BookRack.BookRack;
import BookRack.Book;
import java.util.Scanner;

public class AddOperation implements IOperation {
    @Override
    public void work(BookRack bookRack){}

    // 新的工作方法，用于从 GUI 获取输入并添加书籍
    public void work(BookRack bookRack, String name, String author, int price, String type) {
        // 创建图书对象
        Book book = new Book(name, author, price, type);

        // 检查书架是否已满
        int usedSize = bookRack.getUsedSize();
        if (usedSize >= 10) { // 假设书架的容量为10
            System.out.println("书架已满，无法添加新书！");
            return;
        }

        // 检查是否存在重复书籍
        for (int i = 0; i < usedSize; i++) {
            Book tempBook = bookRack.getBook(i);
            if (tempBook.getName().equals(name)) {
                System.out.println("不能重复添加同一本书，请重试！");
                return;
            }
        }

        // 添加图书到书架
        bookRack.setBook(book, usedSize);
        bookRack.setUsedSize(usedSize + 1);
        System.out.println("图书添加成功！");
    }


}
