import java.util.ArrayList;

public class Member {
     private String name;
     private int ID;
     private int balance;
     private ArrayList<Book>borrowedBook=new ArrayList<>();
     private ArrayList<Magazine>borrowedMagazines=new ArrayList<>();
     public Member(String name,int ID)
     {
          this.name=name;
          this.ID=ID;
     }
     public void setBalance(int amount)
     {
          this.balance=amount;
     }
     public void borrowBook(Book book)
     {
          this.borrowedBook.add(book);
     }
     public void borrowMagazine(Magazine magazine)
     {
         this.borrowedMagazines.add(magazine);
     }
     public void returnBook(Book book)
     {
          this.borrowedBook.remove(book);
     }
     public void returnMagazine(Magazine magazine)
     {
          this.borrowedMagazines.remove(magazine);
     }
     public Book getBookFromBorrowedBooks(int ISBN)
     {
          for (Book book : this.borrowedBook) {
               if (book.getISBN() == ISBN)
               {
                    return book;
               }

          }
          return null;
     }
     public Magazine getMagazineFromBorrowedMagazines(int ISSN,int number)
     {
          for (Magazine magazine : this.borrowedMagazines) {
               if (magazine.getISSN() == ISSN && magazine.getNumber() == number)
               {
                    return magazine;
               }
          }
          return null;
     }
     public int getBalance()
     {
        return this.balance;
     }
     public int getID()
     {
        return this.ID;
     }
     public String getName()
     {
          return this.name;
     }


}
