package com.example.btl_appreadbooks.Category_Books;

import com.example.btl_appreadbooks.books.Books;

import java.util.List;

public class Category_BooksTheLoai {
    private List<Books> Books;

    public Category_BooksTheLoai(List<Books> books) {
        Books = books;
    }

    public List<Books> getBooks() {
        return Books;
    }

    public void setBooks(List<Books> books) {
        Books = books;
    }
}
