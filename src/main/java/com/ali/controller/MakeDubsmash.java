package com.ali.controller;

import com.ali.StorageService;
import com.ali.database.model.Dubsmash;
import com.ali.database.model.User;
import com.ali.database.repository.DubsmashRepository;
import com.ali.database.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.*;
import java.security.Principal;
@Controller
@RequestMapping("/makeDubsmash")
public class MakeDubsmash {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DubsmashRepository dubsmashRepository;

    @GetMapping
    public ModelAndView makeDubsmash(){
        ModelAndView mv = new ModelAndView("make_dubsmash");
        return mv;
    }

    @PostMapping
    public ResponseEntity getDubsmashFile(@RequestParam("video") MultipartFile video,
                                          @RequestParam("audio") MultipartFile audio,
                                          @RequestParam("image") MultipartFile image,
                                          @RequestParam("videoTitle") String videoTitle,
                                          Principal principal) throws InterruptedException, IOException, Ffmpeg_Exception {
        dubsmashCreate(video,audio,image,videoTitle,principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Async
    @Transactional(rollbackOn = Exception.class)
    public void dubsmashCreate(MultipartFile video,MultipartFile audio, MultipartFile image, String videoTitle,Principal principal) throws IOException, Ffmpeg_Exception, InterruptedException{
        File videoFile = storageService.save2File(video, principal.getName());
        File audioFile = storageService.save2File(audio, principal.getName());
        File imgFile = storageService.save2File (image, principal.getName());
        File dubsmashFile = storageService.mixAudioVideo(videoFile,audioFile,videoTitle,principal.getName());
        User user = userRepository.findByusername(principal.getName());
        String dubsmashHash = DigestUtils.sha256Hex(new FileInputStream(dubsmashFile));
        dubsmashRepository.save(new Dubsmash(dubsmashHash, dubsmashFile.getPath(), imgFile.getPath(), user, videoTitle));
    }

    @Bean
    @Scope("singleton")
    StorageService storageService(){
        return new StorageService();
    }

    @Autowired
    StorageService storageService;

    @Autowired
    Environment environment;
}
