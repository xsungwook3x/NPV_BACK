package NPV.demo.service;

import NPV.demo.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class CertificateService implements CertificateRepository {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public void init(){
        try{
            Files.createDirectories(Paths.get(uploadPath));
        }catch(IOException e){
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    @Override
    public void store(MultipartFile file) {
        try{
            if(file.isEmpty()){
                throw new Exception("ERROR : File is empty");
            }
            Path root = Paths.get(uploadPath);
            if(!Files.exists(root)){
                init();
            }
            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            }
        }catch(Exception e){
            throw new RuntimeException("Could not store the file. Error: "+e.getMessage());
        }
    }

    @Override
    public Path load(String filename) {
        return Paths.get(uploadPath).resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try{
            Path file = load(filename);
            Resource resource =new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            else{
                throw new RuntimeException("Could not read file: "+filename);
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("Could not read file: "+filename, e);
        }
    }

    @Override
    public void update(MultipartFile file, String filename) {
        delete(filename);
        store(file);
    }

    @Override
    public void delete(String filename) {
        try {
            Files.deleteIfExists(Paths.get(uploadPath+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
