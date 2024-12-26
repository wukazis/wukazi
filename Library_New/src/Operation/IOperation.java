package Operation;

import BookRack.BookRack;

public interface IOperation {
    // 执行操作，返回是否成功的结果
    void work(BookRack bookRack) ;
}
