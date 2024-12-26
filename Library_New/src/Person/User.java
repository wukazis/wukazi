package Person;

import BookRack.BookRack;
import Operation.IOperation;

public abstract class User {
    protected String name; // 用户姓名
    protected String[] operations;
    protected IOperation[] iOperations; // 用户的操作集合

    // 构造方法，初始化用户姓名
    public User(String name) {
        this.name = name;
    }

    /**
     * 用户菜单（由子类实现）
     * @return 用户选择的操作编号
     */


    public String getName() {
        return name;
    }

    public void doOperation(int choice, BookRack bookRack) {
        if (choice < 0 || choice >= iOperations.length) {
            System.out.println("选择无效，操作失败！");
            return;
        }
        IOperation operation = iOperations[choice];
        operation.work(bookRack);
    }
    public abstract String[] getOperationsDescriptions();
}
