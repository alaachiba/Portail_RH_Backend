package com.smartup.p_rh.documents;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.smartup.p_rh.users.UserServiceImp;

@Service
public class FileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;
	@Autowired
	private UserServiceImp userService;

	public FileDB store(MultipartFile file, int id) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		FileDB.setUser(userService.findUserById(id));
		return fileDBRepository.save(FileDB);
	}

	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
}