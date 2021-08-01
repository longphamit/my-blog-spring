package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.BlogDTO;
import com.longpc.myblogrestapi.dto.MemoDTO;
import com.longpc.myblogrestapi.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/memo")
public class MemoResource {
    @Autowired
    private MemoService memoService;
    @PostMapping
    public ResponseEntity insert(@RequestPart(value = "image", required = false) MultipartFile[] image,
                                 @RequestPart(value = "memo") MemoDTO memoDTO){
        try {
            List<MultipartFile> imageList = Arrays.asList(image);
            memoService.insert(memoDTO, imageList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

}
