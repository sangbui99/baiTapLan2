package bai7.server;

import bai7.service.Bai7ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
/*@MultipartConfig*//*(fileSizeThreshold = 1024 * 1024, // 1M
        maxFileSize = 1024 * 1024 * 5, // 5M
        maxRequestSize = 1024 * 1024 * 5 * 5) // 25M*/
public class Bai7ServerController {
    @Autowired
    private Bai7ServerService bai7ServerService;

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    @ResponseBody
    public String createFileC(@RequestBody MultipartFile file) {
        return bai7ServerService.createFileS(file);
    }
}
