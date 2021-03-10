
  package com.smartup.p_rh.users;
  
  import java.util.List;


  
  
  public interface IUserService {
  
  public User saveUser(User user);
  
  public User updateUser(User user);
  
  public boolean checkIfIdExists(Integer id);
  
  public void deleteUser(Integer id);
  
  public List<User> getAllUser(); 
  
  public User findUserByEmail(String email);
  
  public boolean  existsUserByNom(String nom);
  
  public Boolean existsUserByEmail(String email);
  
	public User findUserById(Integer userId);

   
  }
 