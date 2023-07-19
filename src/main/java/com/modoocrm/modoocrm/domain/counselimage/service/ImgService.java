package com.modoocrm.modoocrm.domain.counselimage.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImgService {

    @Value("${upload.path}")
    private String uploadPath;

    public String createClientFolder(Client client){
        String folderPath = uploadPath + "내담자_" + client.getClientName() + client.getBirth(); //생일은 붙힌 이유는 이름이 똑같은 사람이 왔을 때 구분하기 위함
        File folder = new File(folderPath);
        if (!folder.exists()){
            folder.mkdirs();
        }
        return folderPath; //폴더 경로
    }

    public String getClientImageFileName(Client client, String imageType){
        return "내담자_" + client.getClientName() + "_" + imageType;
    }

    public void saveImageToFolder(String filePath, MultipartFile multipartFile) throws IOException {
        //파일 확장자 추출
        String originalFileName = multipartFile.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null){
            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex>0){
                fileExtension = originalFileName.substring(dotIndex);
            }
        }
        //저장 경로에 확장자 추가
        String filePathWithExtension = filePath + fileExtension;
        File destWithExension = new File(filePathWithExtension);

        //파일 저장
        multipartFile.transferTo(destWithExension);
    }

    public String generateImageLink(Client client, String fileName, String folderPath){
        String imageFolderPath = folderPath;
        String imageFileName = fileName;
        String imagePath = imageFolderPath + "/" + imageFileName; //이미지의 실제 경로
        return  imagePath;
    }
}
