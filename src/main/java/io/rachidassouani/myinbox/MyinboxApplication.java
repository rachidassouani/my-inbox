package io.rachidassouani.myinbox;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.rachidassouani.myinbox.emaillist.EmailListItem;
import io.rachidassouani.myinbox.emaillist.EmailListItemKey;
import io.rachidassouani.myinbox.emaillist.EmailListItemRepository;
import io.rachidassouani.myinbox.folders.Folder;
import io.rachidassouani.myinbox.folders.FolderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MyinboxApplication {

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private EmailListItemRepository emailListItemRepository;


	public static void main(String[] args) {
		SpringApplication.run(MyinboxApplication.class, args);
	}

	@PostConstruct
	public void init() {

		folderRepository.save(new Folder("rachid@gmail.com", "inbox", "blue"));
		folderRepository.save(new Folder("rachid@gmail.com", "sent", "green"));
		folderRepository.save(new Folder("rachid@gmail.com", "important", "yellow"));

		for (int i = 0; i < 10; i++) {
			EmailListItemKey key = new EmailListItemKey();
			key.setUserId("rachid@gmail.com");
			key.setLabel("Inbox");
			key.setTimeUUID(Uuids.timeBased());

			EmailListItem emailListItem = new EmailListItem();
			emailListItem.setId(key);
			emailListItem.setTo(Arrays.asList("rachid@gmail.com"));
			emailListItem.setSubject("test");
			emailListItem.setUnread(true);

			emailListItemRepository.save(emailListItem);
		}
	}
}
