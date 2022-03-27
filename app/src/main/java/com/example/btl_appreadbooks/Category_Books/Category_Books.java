package com.example.btl_appreadbooks.Category_Books;

import com.example.btl_appreadbooks.books.Books;

import java.util.List;

public class Category_Books {
    private String category;
    private List<com.example.btl_appreadbooks.books.Books> Books;

    public Category_Books(String category, List<com.example.btl_appreadbooks.books.Books> books) {
        this.category = category;
        Books = books;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<com.example.btl_appreadbooks.books.Books> getBooks() {
        return Books;
    }

    public void setBooks(List<com.example.btl_appreadbooks.books.Books> books) {
        Books = books;
    }
}
