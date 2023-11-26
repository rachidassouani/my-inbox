package io.rachidassouani.myinbox;

import io.rachidassouani.myinbox.folders.Folder;
import io.rachidassouani.myinbox.folders.FolderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyinboxApplication {

	@Autowired
	private FolderRepository folderRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyinboxApplication.class, args);
	}



	@PostConstruct
	public void init() {
		folderRepository.save(new Folder("rachid@gmail.com", "inbox", "blue"));
		folderRepository.save(new Folder("rachid@gmail.com", "sent", "green"));
		folderRepository.save(new Folder("rachid@gmail.com", "important", "yellow"));
	}
}
