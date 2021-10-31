package com.smartup.p_rh.users;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartup.p_rh.role.RoleRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/api/user")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Utilisateur")
public class UserController {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImp userService;

	@Autowired
	RoleRepository roleDao;

	@PutMapping("/updateUser")
	@ApiOperation(value = "Modifier un utilisateur", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "L'utilisateur n'existe pas"),
			@ApiResponse(code = 200, message = "L'utilisateur a été modifié"),
			@ApiResponse(code = 304, message = "Erreur lors de la modification de type de l'utilisateur") })
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO UserDTORequest) {
		if (!userService.checkIfIdExists(UserDTORequest.getIdUser())) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		User userRequest = mapUserDTOToUser(UserDTORequest);
		User userResponse = userService.updateUser(userRequest);
		if (userResponse != null) {
			UserDTO UserDTO = mapUserToUserDTO(userResponse);
			return new ResponseEntity<UserDTO>(UserDTO, HttpStatus.OK);
		}

		return new ResponseEntity<UserDTO>(HttpStatus.NOT_MODIFIED);
	}

	@GetMapping("/searchByEmail/{email}")
	@ApiOperation(value = "Rechercher un utilisateur par son adresse mail", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "L'utilisateur n'existe pas"),
			@ApiResponse(code = 200, message = "L'utilisateur a été trouvé") })
	public ResponseEntity<UserDTO> searchUserByEmail(@PathVariable String email) {
		User user = userService.findUserByEmail(email);
		if (user != null) {
			UserDTO userDTO = mapUserToUserDTO(user);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/allUser")
	@ApiOperation(value = "Afficher tous les utilisateurs", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des utilisateurs affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucun utilisateur touvé"), })
	public ResponseEntity<List<UserDTO>> AllUser() {
		List<User> users = userService.getAllUser();
		if (!CollectionUtils.isEmpty(users)) {
			users.removeAll(Collections.singleton(null));
			List<UserDTO> userDTOs = users.stream().map(user -> {
				return mapUserToUserDTO(user);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteUser/{idUser}")
	@ApiOperation(value = "Supprimer un utilisateur s'il existe", response = String.class)
	@ApiResponse(code = 204, message = "L'utilisateur a été supprimé avec succées")
	public ResponseEntity<String> deleteUser(@PathVariable Integer idUser) {
		userService.deleteUser(idUser);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/getallotherEmploye/{email}")
	@ApiOperation(value = "Afficher tous les autres employés", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des autres employés affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune autre employé touvé"), })
	public ResponseEntity<List<UserDTO>> getallotherEmploye(@PathVariable String email) {
		List<User> users = userService.getallotherEmploye(email);
		if (!CollectionUtils.isEmpty(users)) {
			users.removeAll(Collections.singleton(null));
			List<UserDTO> userssDtos = users.stream().map(user -> {
				return mapUserToUserDTO(user);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<UserDTO>>(userssDtos, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/changepassword")
	@ApiOperation(value = "Modifier la mo de passe", response = Passwordparser.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La mot de passe a été modifiée"),
			@ApiResponse(code = 304, message = "Erreur lors de la modification de mot de passe") })
	public ResponseEntity<String> changeUserPassword(@RequestBody Passwordparser req) {
		return userService.changeuserpassword(req.getoldpass(), req.getnewpassword(), req.getId());

	}

	private UserDTO mapUserToUserDTO(User user) {
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		return userDTO;
	}

	private User mapUserDTOToUser(UserDTO userDTORequest) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDTORequest, User.class);
		return user;
	}

}
