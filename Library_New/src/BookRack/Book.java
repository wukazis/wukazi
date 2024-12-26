package BookRack;

// 表示书籍的类
public class Book {
    private String name; // 书名
    private String author; // 作者
    private int price; // 价格
    private String type; // 类型
    private boolean isBorrowed; // 是否被借出

    // 构造方法
    public Book(String name, String author, int price, String type) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("书名不能为空！");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("作者名不能为空！");
        }
        if (price < 0) {
            throw new IllegalArgumentException("价格不能为负！");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("书籍类型不能为空！");
        }

        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
        this.isBorrowed = false; // 默认未借出
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("书名不能为空！");
        }
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("作者名不能为空！");
        }
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("价格不能为负！");
        }
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("书籍类型不能为空！");
        }
        this.type = type;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    // 打印书籍的全部信息
    @Override
    public String toString() {
        return String.format("书籍信息：书名：'%s', 作者：'%s', 价格：%d, 类型：'%s', 借阅状态：%s",
                name, author, price, type, isBorrowed ? "已借出" : "未借出");
    }
}
