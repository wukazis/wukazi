package Operation;

import BookRack.BookRack;
import BookRack.Book;

import javax.swing.*;
import java.util.Scanner;

public class FindOperation implements IOperation {
    @Override
    public void work(BookRack bookRack) {}
    public void searchBook(BookRack bookRack, String name, JTextArea resultArea) {
        int usedSize = bookRack.getUsedSize();
        boolean found = false;

        resultArea.setText(""); // 清空之前的结果

        for (int i = 0; i < usedSize; i++) {
            Book temp = bookRack.getBook(i);
            if (name.equals(temp.getName())) {
                resultArea.append("存在这本书，信息如下：\n");
                resultArea.append(temp.toString() + "\n");
                found = true;
                break; // 找到后可以跳出循环
            }
        }

        if (!found) {
            resultArea.append("没有找到您要找的书，书名为：" + name + "\n");
        }
    }
}
