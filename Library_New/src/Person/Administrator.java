package Person;

import Operation.*;
import java.util.Scanner;

public class Administrator extends User {
    // 构造方法，初始化管理员的功能操作
    public Administrator(String name) {
        super(name);
        operations = new String[]{
                "添加图书",
                "删除图书",
                "展示库存",
                "管理用户"
        };
        iOperations = new IOperation[]{

                new AddOperation(),
                new DeleteOperation(),
                new ShowOperation(),
                new FindOperation(),

        };
    }
    public String[] getOperationsDescriptions() {
        return operations;  // 返回管理员的操作选项
    }



}