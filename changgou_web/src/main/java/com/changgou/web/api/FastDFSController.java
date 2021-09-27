package com.changgou.web.api;

import com.changgou.constant.ServiceConstants;
import com.changgou.entity.Result;
import com.changgou.goods.service.file.FileManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: hui.jin
 * @date: 2021/9/27 18:00
 */
@Slf4j
@Api(value = "文件传输api")
@RestController
@RequiredArgsConstructor
@RequestMapping(ServiceConstants.PATH_V2)
public class FastDFSController {


    private FileManageService fileManageService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @ApiOperation(value = "文件上传")
    @PostMapping(value = "uploadFile/")
    public Result<String[]> uploadFile(@RequestBody MultipartFile file){
        return Result.success("文件上传成功", fileManageService.uploadFile(file));

    }
}
