package com.changgou.goods.service.file;

import com.changgou.domain.FastDFSFile;
import com.changgou.exception.NullParamException;
import com.changgou.exception.RunFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 * 文件管理的服务
 * @author: hui.jin
 * @date: 2021/9/27 16:01
 */
@Slf4j
@Service
public class FileManageService {

    public String uploadFile(MultipartFile file) {

        String url;
        try {
            if (file == null) {
                throw new NullParamException("文件");
            }

            String originalFilename = file.getOriginalFilename();
            if (!StringUtils.hasLength(originalFilename)) {
                throw new NullParamException("文件");
            }

            String[] upload = FastDFSClient.upload(FastDFSFile.builder()
                    .context(file.getBytes())
                    .name(originalFilename)
                    .ext(originalFilename.substring(originalFilename.lastIndexOf(".") + 1))
                    .build());
            url = FastDFSClient.getTrackerUrl()+upload[0]+"/"+upload[1];
        } catch (IOException e) {
            throw new RunFileException(file.getName(), e);
        }
        return url;
    }
}
