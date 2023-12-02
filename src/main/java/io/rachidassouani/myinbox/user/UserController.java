package io.rachidassouani.myinbox.user;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.rachidassouani.myinbox.emaillist.EmailListItem;
import io.rachidassouani.myinbox.emaillist.EmailListItemRepository;
import io.rachidassouani.myinbox.folders.Folder;
import io.rachidassouani.myinbox.folders.FolderRepository;
import io.rachidassouani.myinbox.folders.FolderService;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private FolderService folderService;

    @Autowired
    private EmailListItemRepository emailListItemRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        // get user folders
        String userId = "rachid@gmail.com";
        List<Folder> userFolders = folderRepository.findAllByUserId(userId);
        model.addAttribute("userFolders", userFolders);

        // get default folders
        List<Folder> defaultFolders = folderService.fetchDefaultFolders(userId);
        model.addAttribute("defaultFolders", defaultFolders);

        // get emails
        String folderLabel = "Inbox";

        List<EmailListItem> emailList = emailListItemRepository
                .findAllById_UserIdAndId_Label("rachid@gmail.com", folderLabel);

        PrettyTime prettyTime = new PrettyTime();
        emailList.stream().forEach(emailItem -> {
            UUID uuid = emailItem.getId().getTimeUUID();
            Date emailDateTime = new Date(Uuids.unixTimestamp(uuid));
            emailItem.setAgoTimeString(prettyTime.format(emailDateTime));
        });
        model.addAttribute("emailList", emailList);

        return "my-inbox";
    }
}
