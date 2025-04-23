package org.springframework.samples.mvc.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    @ModelAttribute
    public void ajaxAttribute(WebRequest request, Model model) {
        model.addAttribute("ajaxRequest", isAjaxRequest(request));
    }

    @GetMapping
    public String fileUploadForm() {
        return "fileupload";
    }

    @PostMapping
    public String processUpload(@RequestParam MultipartFile file, Model model) {
        model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
        return "fileupload";
    }

    // Helper method to replace AjaxUtils.isAjaxRequest
    private boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null && "XMLHttpRequest".equals(requestedWith);
    }
}
