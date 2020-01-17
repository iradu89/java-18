import java.util.Arrays;

public class Library {
    private Book[] books;

    public Library() {
        this.books = new Book[1];
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void addBook(Book bookToAdd) {
        //pas1 - cream un nou array cu dimensiunea si valorile lui  = cu vechiu array + 1
        Book[] newArray = Arrays.copyOf(books, books.length + 1);
        //pas2 - in noul array adaugam pe ultima pozitie noul element
        newArray[books.length] = bookToAdd;
        books = newArray;
    }

    public void removeBook(Book bookToRemove, Book[] arrayToRemoveBookFrom) {
        //pas1 - cream un nou array cu dimensiunea lui  = cu vechiu array - 1
        Book[] newArray = new Book[arrayToRemoveBookFrom.length - 1];
        //pas2 - itereaza prin fiecare index si verifica daca trebuie sters
        for (int i = 0, k = 0; i < arrayToRemoveBookFrom.length; i++) {
            if (i == findBookIndex(bookToRemove)) {
                continue;
            }
            newArray[k++] = arrayToRemoveBookFrom[i];
        }
        books = newArray;

    }

    //gaseste indexul cartii cautate
    public int findBookIndex(Book book) {
        String search = book.getName();
        for (int i = 0; 0 < books.length; i++) {
            if (search.equals(books[i].getName())) {
                return i;
            }
        }
        return -1;
    }

}
