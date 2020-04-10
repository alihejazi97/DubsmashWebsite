package com.ali;

import com.ali.controller.Ffmpeg_Exception;
import com.ali.database.model.User;
import com.ali.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Arrays;

public class StorageService {

    @Autowired
    Environment env;

    @Autowired
    UserRepository userRepository;

    public File mixAudioVideo(File videoFile,File audioFile,String videoTitle,String username) throws IOException, InterruptedException ,Ffmpeg_Exception{
        File file = makeFile(videoTitle + ".mp4", username);
        String [] command = {"ffmpeg","-y","-i",videoFile.getPath(),"-i",audioFile.getPath(),
                "-shortest","-map","0:v:0","-map","1:a:0",
                "-vsync","2",file.getPath()};
        System.out.println("command");
        for (int i = 0; i < command.length; i++) {
            System.out.print(command[i] + " ");
        }
        System.out.println();
        Process p = new ProcessBuilder(Arrays.asList(command)).inheritIO().start();
        int result = p.waitFor();
        if (result == 0)
            return file;
        throw new Ffmpeg_Exception();
    }

    private File makeFile(String fileName,String username) throws IOException {
        String path = env.getProperty("BASE_PATH");
        path += "\\" + username;
        File parent = new File(path);
        parent.mkdirs();
        path += "\\"  + fileName;
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
        return file;
    }


    public void addUserSpace(MultipartFile multipartFile,String username){
        User user = userRepository.findByusername(username);
        user.spaceUsed += multipartFile.getSize();
        userRepository.save(user);
    }

    public File save2File(MultipartFile multipartFile,String username) throws IOException{
        File file = makeFile(multipartFile.getOriginalFilename(), username);
        multipartFile.transferTo(file);
        return file;
    }
}

