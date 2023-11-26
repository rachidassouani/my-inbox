package io.rachidassouani.myinbox.folders;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface FolderRepository extends CassandraRepository<Folder, String> {
    List<Folder> findAllByUserId(String userId);
}
