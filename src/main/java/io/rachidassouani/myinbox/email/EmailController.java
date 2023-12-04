package io.rachidassouani.myinbox.email;

import io.rachidassouani.myinbox.emaillist.EmailListItemRepository;
import io.rachidassouani.myinbox.folders.Folder;
import io.rachidassouani.myinbox.folders.FolderRepository;
import io.rachidassouani.myinbox.folders.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class EmailController {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private FolderService folderService;

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("emails/{id}")
    public String findEmailById(@PathVariable UUID id, Model model) {
        // get user folders
        String userId = "rachid@gmail.com";
        List<Folder> userFolders = folderRepository.findAllByUserId(userId);
        model.addAttribute("userFolders", userFolders);

        // get default folders
        List<Folder> defaultFolders = folderService.fetchDefaultFolders(userId);
        model.addAttribute("defaultFolders", defaultFolders);

        Optional<Email> optionalEmail = emailRepository.findById(id);
        if (!optionalEmail.isPresent()) {
            return "my_inbox";
        }
        model.addAttribute("email", optionalEmail.get());
        return "email";
    }
}