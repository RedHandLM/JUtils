package lsc.jdk.enumsu;

public enum Book {
    BOOK(0),

    EBOOK(1);

    private int value;

    private int getValue() {
        return value;
    }

    private Book(int value) {
        this.value = value;
    }

}





