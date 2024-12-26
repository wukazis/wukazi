package BookRack;

// 书架类
public class BookRack {
    private Book[] books; // 存放的所有书籍
    private int usedSize; // 书架上已经放置的书籍数量

    // 默认构造方法，初始化书架
    public BookRack() {
        this.books = new Book[10]; // 初始容量为 10
        // 添加默认书籍
        this.books[0] = new Book("三国演义", "罗贯中", 20, "小说");
        this.books[1] = new Book("西游记", "吴承恩", 9, "小说");
        this.books[2] = new Book("红楼梦", "曹雪芹", 19, "小说");
        this.usedSize = 3; // 初始化书架已有书籍数量
    }

    // 获取某个位置的书籍
    public Book getBook(int pos) {
        if (pos < 0 || pos >= usedSize) {
            throw new IndexOutOfBoundsException("索引无效，超出书架范围！");
        }
        return books[pos];
    }

    // 设置某个位置的书籍
    public void setBook(Book book, int pos) {
        if (pos < 0 || pos >= books.length) {
            throw new IndexOutOfBoundsException("索引无效，超出书架范围！");
        }
        books[pos] = book;
        if (pos >= usedSize) {
            usedSize = pos + 1;
        }
    }

    // 获取书架中书籍的数量
    public int getUsedSize() {
        return usedSize;
    }

    // 设置书架中书籍的数量
    public void setUsedSize(int usedSize) {
        if (usedSize < 0 || usedSize > books.length) {
            throw new IllegalArgumentException("书架大小无效！");
        }
        this.usedSize = usedSize;
    }

    // 添加书籍到书架
    public void addBook(Book book) {
        if (usedSize == books.length) {
            expandCapacity();
        }
        books[usedSize] = book;
        usedSize++;
    }

    // 扩容方法，增加书架容量
    private void expandCapacity() {
        Book[] newBooks = new Book[books.length * 2];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        books = newBooks;
    }

}
