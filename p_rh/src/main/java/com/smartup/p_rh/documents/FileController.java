package com.smartup.p_rh.documents;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.smartup.p_rh.message.response.ResponseFile;
import com.smartup.p_rh.message.response.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/rest/api/file")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Documents administratifs")
public class FileController {

	@Autowired
	private FileStorageService storageService;

	@ApiOperation(value = "Importer un document administratif", response = FileDB.class)
	@ApiResponse(code = 201, message = "Le document administratif a été importer avec succées")
	@PostMapping("/upload/{id}")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int id) {
		String message = "";
		try {
			storageService.store(file, id);

			message = "Le document administratif a été importer avec succées: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Un erreur lors de l'importation: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	@ApiOperation(value = "Afficher tous les documents administratifs", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des documents administratifs affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucun document administratif touvé"), })
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length,
					dbFile.getUser());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/files/{id}")
	@ApiOperation(value = "Afficher un document administratif par id", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Le document affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune document touvé"), })
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		FileDB fileDB = storageService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
				.body(fileDB.getData());
	}
}
