package com.ali.controller;

import com.ali.database.model.Dubsmash;
import com.ali.database.repository.DubsmashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/dubsmash")
public class dubsmash {

    @Autowired
    DubsmashRepository dubsmashRepository;

    @GetMapping
    public ModelAndView getDubsmashView(@RequestParam long id) {
        ModelAndView mv = new ModelAndView("dubsmash");
        Optional<Dubsmash> dubsmash = dubsmashRepository.findById(id);
        String dubsmashId = Long.toString(id);
        mv.addObject("dubsmashId", dubsmashId);
        return mv;
    }

    @GetMapping(value = "/video/{id}")
    public ResponseEntity getVideo(@PathVariable("id") long id) {
        Optional<Dubsmash> dubsmash = dubsmashRepository.findById(id);
        if (dubsmash.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        FileSystemResource fileSystemResource =
                new FileSystemResource(dubsmash.get().fileAddress);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("video/mp4"))
                .contentLength(fileSystemResource.getFile().length())
                .body(fileSystemResource);
    }
}
