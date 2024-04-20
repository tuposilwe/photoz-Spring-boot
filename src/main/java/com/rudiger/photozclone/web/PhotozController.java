package com.rudiger.photozclone.web;

import com.rudiger.photozclone.model.Photo;
import com.rudiger.photozclone.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {
    private final PhotoService photoService;

    public PhotozController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get(){
        return photoService.get();
    }

    @GetMapping("/photoz/{id}")
    public Optional<Photo> get(@PathVariable String id){

        Optional<Photo> photo = Optional.ofNullable(photoService.get(id));
        if(photo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id){
        Optional<Photo> photo = Optional.ofNullable(photoService.remove(id));
        if(photo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photoService.save(file.getOriginalFilename(),file.getContentType(), file.getBytes());
        return photo;
    }

}






















