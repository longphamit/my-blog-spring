package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.MemoDTO;
import com.longpc.myblogrestapi.entity.MemoEntity;
import com.longpc.myblogrestapi.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/memo")
public class MemoResource {
    @Autowired
    private MemoService memoService;
    @PostMapping("/auth")
    public ResponseEntity insert(@RequestPart(value = "image") MultipartFile[] image,
                                 @RequestPart(value = "memo")@Valid MemoDTO memoDTO){
        try {
            List<MultipartFile> imageList = Arrays.asList(image);
            memoService.insert(memoDTO, imageList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
    @PutMapping("/auth")
    public ResponseEntity update(@RequestBody MemoDTO memoDTO){
        try {
            memoService.insert(memoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
    @DeleteMapping("/auth/{memoId}")
    public ResponseEntity delete(@PathVariable("memoId")String memoId){
        try {
            memoService.delete(memoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
    @GetMapping
    public ResponseEntity getAll(){
        try {
            List<MemoEntity> listMemo=memoService.getAll();
            return ResponseEntity.ok().body(listMemo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

}
