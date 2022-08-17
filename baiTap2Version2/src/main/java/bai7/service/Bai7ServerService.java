package bai7.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class Bai7ServerService {
    public String createFileS(MultipartFile file){
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo( new File("E:\\new_code\\Documents\\server\\" + fileName));
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
