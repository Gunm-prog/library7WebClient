package com.emilie.library7WebClient.Services;


import com.emilie.library7WebClient.Entities.Book;
import com.emilie.library7WebClient.Entities.Library;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    private final FeignProxy feignProxy;

    public BookServiceImpl(FeignProxy feignProxy) {
        this.feignProxy=feignProxy;
    }

    public Book getById (Long id){
        return this.feignProxy.getById();
    }

    public List<Book> getBookList(){
        return this.feignProxy.getBookList();
    }

    public List<Library> getLibraryList(){return feignProxy.getLibraryList();}


}
