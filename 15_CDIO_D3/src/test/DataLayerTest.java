package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DTO.RoleDTO;
import DTO.UserDTO;
import dalSQL.Connector;
import dalSQL.MySQLUserDAO;
import exceptions.DALException;

public class DataLayerTest {
	

	private static MySQLUserDAO userdao= new MySQLUserDAO();
	
	private static UserDTO user1= new UserDTO(1, "user1", "u1", "010411-1452", "1ugf7&2", new ArrayList<RoleDTO>());
	private static UserDTO user2= new UserDTO(2, "user2", "u2", "020411-1452", "2ugf7&2", new ArrayList<RoleDTO>());
	private static UserDTO user3= new UserDTO(3, "user3", "u3", "030411-1452", "3ugf7&2", new ArrayList<RoleDTO>());
	private static UserDTO tempUser;
	
	
	@BeforeClass
	public static void setUpBeforeClass() {
		try {
			Connector.connectToDatabase();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		user1.addRole(new RoleDTO(1, null));
		user1.addRole(new RoleDTO(2, null));
		user2.addRole(new RoleDTO(3, null));
		user3.addRole(new RoleDTO(4, null));
		try {
			
			userdao.createUser(user1);
			userdao.createUser(user2);
			userdao.createUser(user3);
		} catch (DALException e) {
			System.err.println("Error in setUpBeforeClass");
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		try {
			userdao.deleteUser(user1.getUserId());
			userdao.deleteUser(user2.getUserId());
			userdao.deleteUser(user3.getUserId());
		} catch (DALException e) {
			System.err.println("Error in tearDownAfterClass");
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		assertEquals(user1.getCpr(),userdao.getUser(user1.getUserId()).getCpr());
		assertEquals(user1.getIni(),userdao.getUser(user1.getUserId()).getIni());
		assertEquals(user1.getPassword(),userdao.getUser(user1.getUserId()).getPassword());
//		assertEquals(user1.getRoles(),userdao.getUser(user1.getUserId()).getRoles());
		assertEquals(user1.getUserName(),userdao.getUser(user1.getUserId()).getUserName());
		
		assertEquals(user2.getCpr(),userdao.getUser(user2.getUserId()).getCpr());
		assertEquals(user2.getIni(),userdao.getUser(user2.getUserId()).getIni());
		assertEquals(user2.getPassword(),userdao.getUser(user2.getUserId()).getPassword());
//		assertEquals(user2.getRoles(),userdao.getUser(user2.getUserId()).getRoles());
		assertEquals(user2.getUserName(),userdao.getUser(user2.getUserId()).getUserName());
		
		assertEquals(user3.getCpr(),userdao.getUser(user3.getUserId()).getCpr());
		assertEquals(user3.getIni(),userdao.getUser(user3.getUserId()).getIni());
		assertEquals(user3.getPassword(),userdao.getUser(user3.getUserId()).getPassword());
//		assertEquals(user3.getRoles(),userdao.getUser(user3.getUserId()).getRoles());
		assertEquals(user3.getUserName(),userdao.getUser(user3.getUserId()).getUserName());
	}

	@After
	public void tearDown() throws Exception {
		user1= new UserDTO(1, "user1", "u1", "010411-1452", "1ugf7&2", new ArrayList<RoleDTO>());
		user2= new UserDTO(2, "user2", "u2", "020411-1452", "2ugf7&2", new ArrayList<RoleDTO>());
		user3= new UserDTO(3, "user3", "u3", "030411-1452", "3ugf7&2", new ArrayList<RoleDTO>());
		user1.addRole(new RoleDTO(1, null));
		user1.addRole(new RoleDTO(2, null));
		user2.addRole(new RoleDTO(3, null));
		user3.addRole(new RoleDTO(4, null));
		
		userdao.updateUser(user1);
		userdao.updateUser(user2);
		userdao.updateUser(user3);
	}

	@Test
	public void test1() {
		try {
			tempUser = userdao.getUser(1);
			
			
			tempUser.addRole(new RoleDTO(3,null));
			userdao.updateUser(tempUser);
			
			assertEquals(tempUser.getRoles().get(0).getRoleId(), userdao.getUser(1).getRoles().get(0).getRoleId());
			assertEquals(tempUser.getRoles().get(1).getRoleId(), userdao.getUser(1).getRoles().get(1).getRoleId());
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test2(){
		try {
			tempUser = userdao.getUser(1);
			tempUser.setIni("ekv543");
			userdao.updateUser(tempUser);
			assertEquals(tempUser.getIni(),userdao.getUser(1).getIni());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
