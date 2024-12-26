package Person;

import Operation.*;
import java.util.Scanner;

public class NormalUser extends User {
    // 构造方法，初始化普通用户的功能操作
    public NormalUser(String name) {
        super(name);
        operations = new String[] {
                "1. 查找图书",
                "2. 借阅图书",
                "3. 归还图书",
                "4. 退出"
        };
        iOperations = new IOperation[]{

                new FindOperation(),
                new BorrowOperation(),
                new ReturnOperation(),
                new ExitOperation()
        };
    }

    @Override
    public String[] getOperationsDescriptions() {
        return operations;  // 返回普通用户的操作选项
    }
}
