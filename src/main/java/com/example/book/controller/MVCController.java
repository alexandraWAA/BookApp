package com.example.book.controller;

import com.example.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("com")
public class MVCController {

    private BookService bookService;

    public MVCController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/web")
    public String showBooks(Model model){
        model.addAttribute("books", bookService.getBook());
        return "bookapp";
    }
}
