package com.henu.paperfile.controller;

import java.io.*;
import java.util.*;

import com.henu.paperfile.fastdfs.FastDFSClient;
import com.henu.papercommon.utils.FileUtils;
import com.henu.paperfile.dto.FileDTO;
import com.henu.paperfile.dto.convert.FileConvert;
import com.henu.paperfile.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.henu.paperfile.domain.FileDO;
import com.henu.papercommon.utils.PageUtils;
import com.henu.papercommon.utils.Query;
import com.henu.papercommon.utils.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-12 12:17:36
 */

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${app.filePath}")
    String filePath;

    @Value("${app.pre}")
    String filePre;

    @Autowired
    FastDFSClient fastDFSClient;

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public ResultBean get(@PathVariable Long id) {
        FileDTO fileDTO = FileConvert.MAPPER.do2dto(fileService.get(id));
        return ResultBean.data(fileDTO);
    }

    @GetMapping("getToken")
    public ResultBean getToken() {
        return ResultBean.ok().put("url", "http://localhost:8002/api-cms/file/upload");
    }

    @PostMapping("upload")
    public ResultBean upload(MultipartFile file) {
        /*try {
            *//*String resPath = FileUtils.saveFile(file.getBytes(), filePath, key);
            fileService.save(new FileDO() {{
                setCreateDate(new Date());
                setUrl("http://localhost:8004" + filePre + "/"+resPath);
                setType(1);
            }});
            return ResultBean.ok().put("resPath", resPath);*//*
            String fileRealName = file.getOriginalFilename();//获得原始文件名;
            int random = (int) (Math.random() * 100 + 1);
            int random1 = (int) (Math.random() * 100 + 1);
            filePath = filePath + random + File.separator + random1 + File.separator + fileRealName;
            File savedFile = new File(filePath);
            boolean isCreateSuccess = savedFile.createNewFile(); // 是否创建文件成功
            if(isCreateSuccess){      //将文件写入
                file.transferTo(savedFile);
            }
            String fileId = FastDFSClientUtils.upload(savedFile, filePath);
            logger.info("本地文件：" + fileRealName + "，上传成功！ 文件ID为：" + fileId);
            return ResultBean.ok().put("fileId",fileId);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("文件上传失败");
        }*/
        logger.info("lige1:" + file.toString());

        if (file != null && !file.isEmpty())
        {
            try
            {

                String path = fastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename());

                return ResultBean.ok().put("resPath",path);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return ResultBean.error("文件上传失败");
            }
        }
        else
        {
            return ResultBean.error("参数丢失");
        }
    }

    @PostMapping("download")
    public void download(HttpServletResponse response, String path){
        try{
            // 判断文件是否存在
            if (fastDFSClient.getFileInfo(path) != null)
            {
                byte[] buffer = fastDFSClient.downloadFile(path);
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + FileUtils.getOriginalFilename(path));
                response.addHeader("Content-Length", "" + buffer.length);
                // 通过文件流的形式写到客户端
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                // 写完以后关闭文件流
                toClient.flush();
                toClient.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 分页查询
     */
    @GetMapping
    public ResultBean list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<FileDO> fileList = fileService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        int total = fileService.count(query);
//        PageUtils pageUtils = new PageUtils(fileDTOS, total);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return ResultBean.page(pageUtils);
    }

    /**
     * 保存
     */
    @PostMapping
    public ResultBean save(FileDO file) {
        return ResultBean.operate(fileService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping
    public ResultBean update(FileDO file) {
        return ResultBean.operate(fileService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public ResultBean remove(Long id) {
        return ResultBean.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public ResultBean remove(@RequestParam("ids[]") Long[] ids) {
        return ResultBean.operate(fileService.batchRemove(ids) > 0);
    }
}
