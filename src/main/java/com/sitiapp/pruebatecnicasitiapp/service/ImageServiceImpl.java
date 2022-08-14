package com.sitiapp.pruebatecnicasitiapp.service;

import com.sun.jdi.event.ExceptionEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {


    @Value("${prueba.application.pathResource}")
    private String path;
    private Path rootFolder = Paths.get("src//main//resources/image");


    @Override
    public boolean save(MultipartFile file) throws Exception {
        try {
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            Path location = Paths.get(this.path + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), location,StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            log.error("error en save image " + e.getMessage());
            return false;
        }
    }

    @Override
    public Resource load(String name) throws Exception {
        try {
            Path image = this.rootFolder.resolve(name);
            Resource resource = new UrlResource(image.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //    @Override
    public Stream<Path> loadAll() throws Exception {
        return null;
    }

}
