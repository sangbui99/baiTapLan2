package bai7.client;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class Client {
    public void get(){

    }
    public void modify(String clientName,String pathFolder) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path sourceDirPath = Paths.get(pathFolder);
        sourceDirPath.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

        for (; ; ) {
            synchronized (pathFolder) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path name = ev.context();
                    Path child = sourceDirPath.resolve(name);

                    String comment = clientName + "  " + event.kind().name() + "  " + child;

                    try {
                        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
                        FileSystemResource value = new FileSystemResource(new File(String.valueOf(child)));
                        map.add("file", value);
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.exchange("http://localhost:8080/client", HttpMethod.POST, requestEntity, String.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        }
    }

}
