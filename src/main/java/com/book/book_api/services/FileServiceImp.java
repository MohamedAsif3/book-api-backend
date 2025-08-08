package com.book.book_api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Service
public class FileServiceImp implements FileService{
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException { //path: where file should be saved, file: file recieved from the client
        String fileName = file.getOriginalFilename();

        String filePath = path + File.separator + fileName;

//        images/myfile.txt
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }
//      copy file content to path
        CompletableFuture.runAsync(() -> {
            try {
                Files.copy(file.getInputStream(), Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath); //Returns An InputStream that reads the content of the file.
    }
}
