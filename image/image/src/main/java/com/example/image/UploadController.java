package com.example.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    private UploadDataRepository repository;

    @GetMapping("/")
    public String home(Model model) {
        List<UploadData> dataList = repository.findAll();
        model.addAttribute("dataList", dataList);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadData(@RequestParam("text") String text, @RequestParam("image") String imageBase64) {
        UploadData data = new UploadData();
        data.setText(text);
        data.setImageBase64(imageBase64);
        repository.save(data);
        return "redirect:/";
    }

    private byte[] decodeImage(String base64Image) {
        return Base64.getDecoder().decode(base64Image);
    }
}
