package com.emilie.library7WebClient.Controllers;

import com.emilie.library7WebClient.Entities.Library;
import com.emilie.library7WebClient.Proxy.FeignProxy;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/libraries")
public class LibraryController {

    private final FeignProxy feignProxy;

    private static final String LIBRARY = "libraries";

    @Autowired
    public  LibraryController(FeignProxy feignProxy){
        this.feignProxy=feignProxy;
    }


    @GetMapping("/{id}")
    public String library(@PathVariable("id") Long id, Model model){
        Library library = feignProxy.getLibraryById( id );
        model.addAttribute("library", library );

        return "libraryContactDetails";
    }

    @GetMapping("/list")
    public String libraryList(Model model){
        List<Library> libraries = feignProxy.getLibraryList();
        model.addAttribute( "libraries", libraries );
        return "libraryList";
    }

}
