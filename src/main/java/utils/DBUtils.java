package utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Product;
import beans.Role;
import beans.UserAccount;
import beans.OrderObject;
import java.util.Date;

public class DBUtils {
	public static void commit(Connection conn)throws SQLException{
		conn.prepareStatement("Commit").executeQuery();
	}
	public static void updateOrderStatus(Connection conn, int codOrder, String newStatus) throws SQLException{
		String sql = "Update orders set orders.status_order = ? where orders.cod_order = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, newStatus);
		pstm.setInt(2, codOrder);
		pstm.executeUpdate();
	}
	public static void updateUserRole(Connection conn, int codUser, int codNewRole) throws SQLException{
		String sql = "Update users set roles_cod_role = ? where cod_user = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, codNewRole);
		pstm.setInt(2, codUser);
		pstm.executeUpdate();
	}
	public static List<UserAccount> queryUsersAccounts(Connection conn) throws SQLException{
		String sql = "Select * from view_users a ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<UserAccount> list = new ArrayList<UserAccount>();
		while (rs.next()) {

			UserAccount user = new UserAccount();
			user.setCod(rs.getString("cod_user").toString());
			user.setNickName(rs.getString("nick_name"));
			user.setFirstName(rs.getString("first_name"));
			user.setSecondName(rs.getString("second_name"));
			user.setBirthDate( rs.getDate("birth_date"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setPassword(rs.getString("password"));
			user.setAccessRights(rs.getString("name"));
			list.add(user);
		}
		return list;
	}
	public static void buyProduct(Connection conn, Product product, UserAccount user)throws SQLException {
		String sql = "insert into orders (cod_order, status_order, users_cod_user, products_cod_product, hour, min, date_time) " +
				"values (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, 0);
		pstm.setString(2, "Ready");
		pstm.setString(3, user.getCod());
		pstm.setString(4, product.getCode());
		Calendar rightNow = Calendar.getInstance();
		//hour
		pstm.setInt(5, rightNow.get(Calendar.HOUR_OF_DAY));
		//min
		pstm.setInt(6, rightNow.get(Calendar.MINUTE));
		//date
		Date curDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(curDate.getTime());
		pstm.setDate(7,sqlDate);
		System.out.println(sqlDate.toString());

		pstm.executeUpdate();
	}

	public static void addNewUser(Connection conn, UserAccount newUser)throws SQLException{
		String sql = "insert into users (cod_user, nick_name, first_name, second_name, birth_date, phone_number, password, roles_cod_role) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?)";
//TO_DATE('24/11/2000 0:00:00','DD/MM/YY HH24:MI:SS')
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, 0);
		pstm.setString(2, newUser.getNickName());
		pstm.setString(3, newUser.getFirstName());
		pstm.setString(4,newUser.getSecondName());
		//SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
		Date dat = newUser.getBirthDate();

		java.sql.Date sqlDate = new java.sql.Date(dat.getTime());

		System.out.println(sqlDate.toString());
		pstm.setDate(5, sqlDate);
		pstm.setString(6,newUser.getPhoneNumber());
		pstm.setString(7,newUser.getPassword());
		int aRights;
		switch (newUser.getAccessRights()) {
			case "Admin":  aRights = 1;
				break;
			case "Worcker":  aRights = 2;
				break;
			case "Client":  aRights = 3;
				break;
			default: aRights = 3;
				break;
		}
		pstm.setInt(8,aRights);
    	//SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
		System.out.println(sqlDate.toString());
		//pstm.setString(5, "01.10.00");
		pstm.executeUpdate();
	}
	public static UserAccount findUser(Connection conn, 
			String nickName, String password) throws SQLException {

		String sql = 
		"Select * from view_users a where a.nick_name = ? and a.password = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, nickName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		System.out.println("Get result set!");
		if (rs.next()) {
			System.out.println("Start!");
			UserAccount user = new UserAccount();
			user.setCod(rs.getString("cod_user").toString());
			user.setNickName(nickName);
			user.setFirstName(rs.getString("first_name"));
			user.setSecondName(rs.getString("second_name"));
			user.setBirthDate( rs.getDate("birth_date"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setPassword(password);
			user.setAccessRights(rs.getString("name"));
			
			return user;
		}
		System.out.println("nothing!");
		return null;
	}

	public static UserAccount findUser(Connection conn, String nickName) throws SQLException {

		String sql = "Select * from view_users a where a.nick_name = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, nickName);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			
			UserAccount user = new UserAccount();
			user.setCod(rs.getString("cod_user").toString());
			user.setNickName(nickName);
			user.setFirstName(rs.getString("first_name"));
			user.setSecondName(rs.getString("second_name"));
			user.setBirthDate(rs.getDate("birth_date"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setPassword(rs.getString("password"));
			user.setAccessRights(rs.getString("name"));
			
			return user;
		}
		return null;
	}
	
	public static UserAccount findUserInfo(Connection conn, String firstName, String secondName) throws SQLException {

		String sql = "Select * from view_users a where a.first_name = ? and a.second_name = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, firstName);
		pstm.setString(2, secondName);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			
			UserAccount user = new UserAccount();
			user.setCod(rs.getString("cod_user").toString());
			user.setNickName(rs.getString("nick_name"));
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			SimpleDateFormat sdate = new SimpleDateFormat("");
			Date bdate = (rs.getDate("birth_date"));
			user.setBirthDate(bdate);
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setPassword(rs.getString("password"));
			user.setAccessRights(rs.getString("name"));
			
			return user;
		}
		return null;
	}

	public static List<Product> queryProduct(Connection conn) throws SQLException {
		String sql = "Select a.cod_product, a.name_product, a.price, a.average_mass from products a ";
				
		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			String code = rs.getString("cod_product");
			String name = rs.getString("name_product");
			float price = rs.getFloat("price");
			float averageMass = rs.getFloat("average_mass");
			Product product = new Product();
			product.setCode(code);
			product.setName(name);
			product.setPrice(price);
			product.setMass(averageMass);
			list.add(product);
		}
		return list;
	}
	public static List<Role> queryRole(Connection conn) throws SQLException{
		String sql = "Select a.cod_role, a.name from roles a ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Role> list = new ArrayList<Role>();
		while (rs.next()) {
			int code = rs.getInt("cod_role");
			String name = rs.getString("name");
			Role role = new Role(code, name);
			list.add(role);
		}
		return list;
	}
	public static Product findProduct(Connection conn, String code) throws SQLException {
		String sql = "Select a.cod_product, a.name_product, a.price, a.average_mass from products a "+
				"where a.cod_product = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("name_product");
			float price = rs.getFloat("price");
			float mass = rs.getFloat("average_mass");
			Product product = new Product(code, name, price, mass);
			return product;
		}
		return null;
	}
	
	public static List<Product> findProductFromName(Connection conn, String nameProduct) throws SQLException {
		String sql = "Select * from products a "+
				"where a.name = ?";
		List<Product> list = new ArrayList<Product>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, nameProduct);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String code = rs.getString("cod_product");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			float mass = rs.getFloat("average_mass");
			Product product = new Product(code, name, price, mass);
			list.add(product);
		}
		return list;
	}

	public static void updateProduct(Connection conn, Product product) throws SQLException {
		String sql = "Update products set name_product = ?, price = ?, average_mass = ? where cod_product = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, product.getName());
		pstm.setFloat(2, product.getPrice());
		pstm.setFloat(3, product.getMass());
		pstm.setString(4, product.getCode());
		pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into Product(cod_product, name_product, price, average_mass) values (?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, product.getCode());
		pstm.setString(2, product.getName());
		pstm.setFloat(3, product.getPrice());
		pstm.setFloat(4,product.getMass());

		pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, String code) throws SQLException {
		String sql = "Delete From Product where cod_product= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, code);

		pstm.executeUpdate();
	}

	public static List<OrderObject> listOrder(Connection conn, UserAccount user)throws SQLException{
		if(user == null){
			return null;
		}//сделать новый объект для заказ который наследуется от продукта.
		// потому что нам нужно как минимум будет представлять все заказы. и
		// + нам нужно будет знать код заказа.

		String sql = "Select b.cod_order, a.cod_product, a.name_product, a.price, a.average_mass, b.status_order, b.hour, b.min, b.date_time from products a, orders b "+
				"where b.users_cod_user = ? and a.cod_product = b.products_cod_product ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, user.getCod());




		ResultSet rs = pstm.executeQuery();
		List<OrderObject> list = new ArrayList<OrderObject>();
		while (rs.next()) {
			int codOrder = rs.getInt("cod_order");
			String code = rs.getString("cod_product");
			String name = rs.getString("name_product");
			float price = rs.getFloat("price");
			float averageMass = rs.getFloat("average_mass");
			String statusOrder = rs.getString("status_order");
			int hour = rs.getInt("hour");
			int min = rs.getInt("min");
			java.sql.Date dateTime = rs.getDate("date_time");
			Date uDate = new Date(dateTime.getTime());
			OrderObject ord = new OrderObject(codOrder,code, name, price, averageMass,
					user.getNickName(), user.getCod(), statusOrder,hour, min, uDate);
			list.add(ord);
		}
		return list;

	}
	public static List<OrderObject> listOrder(Connection conn, UserAccount user, String status)throws SQLException{
		if(user == null){
			return null;
		}//сделать новый объект для заказ который наследуется от продукта.
		// потому что нам нужно как минимум будет представлять все заказы. и
		// + нам нужно будет знать код заказа.

		String sql = "Select b.cod_order, a.cod_product, a.name_product, a.price, a.average_mass, b.status_order, b.hour, b.min, b.date_time from products a, orders b "+
				"where b.users_cod_user = ? and b.status_order = ? and a.cod_product = b.products_cod_product ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, user.getCod());
		pstm.setString(2, status);



		ResultSet rs = pstm.executeQuery();
		List<OrderObject> list = new ArrayList<OrderObject>();
		while (rs.next()) {
			int codOrder = rs.getInt("cod_order");
			String code = rs.getString("cod_product");
			String name = rs.getString("name_product");
			float price = rs.getFloat("price");
			float averageMass = rs.getFloat("average_mass");
			String statusOrder = rs.getString("status_order");
			int hour = rs.getInt("hour");
			int min = rs.getInt("min");
			java.sql.Date dateTime = rs.getDate("date_time");
			Date uDate = new Date(dateTime.getTime());
			OrderObject ord = new OrderObject(codOrder,code, name, price, averageMass,
					user.getNickName(), user.getCod(), statusOrder,hour, min, uDate);
			list.add(ord);
		}
		return list;

	}
	public static List<OrderObject> listOrder(Connection conn)throws SQLException{

		String sql = "Select b.cod_order, a.cod_product, a.name_product, a.price, a.average_mass, b.status_order, b.hour, b.min, b.date_time, c.cod_user, c.nick_name from products a, orders b, users c "+
				"where a.cod_product = b.products_cod_product and c.cod_user = b.users_cod_user ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<OrderObject> list = new ArrayList<OrderObject>();
		while (rs.next()) {
			int codOrder = rs.getInt("cod_order");
			String code = rs.getString("cod_product");
			String name = rs.getString("name_product");
			float price = rs.getFloat("price");
			float averageMass = rs.getFloat("average_mass");
			String statusOrder = rs.getString("status_order");
			int hour = rs.getInt("hour");
			int min = rs.getInt("min");
			String codUser = rs.getString("cod_user");
			String nickName = rs.getString("nick_name");
			java.sql.Date dateTime = rs.getDate("date_time");
			Date uDate = new Date(dateTime.getTime());
			OrderObject ord = new OrderObject(codOrder,code, name, price, averageMass,
					nickName, codUser, statusOrder,hour, min, uDate);
			list.add(ord);
		}
		return list;

	}
	public static List<OrderObject> listOrder(Connection conn, String status)throws SQLException{

		String sql = "Select b.cod_order, a.cod_product, a.name_product, a.price, a.average_mass, b.status_order, b.hour, b.min, b.date_time, c.cod_user, c.nick_name from products a, orders b, users c "+
				"where a.cod_product = b.products_cod_product and c.cod_user = b.users_cod_user and b.status_order = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, status);

		ResultSet rs = pstm.executeQuery();
		List<OrderObject> list = new ArrayList<OrderObject>();
		while (rs.next()) {
			int codOrder = rs.getInt("cod_order");
			String code = rs.getString("cod_product");
			String name = rs.getString("name_product");
			float price = rs.getFloat("price");
			float averageMass = rs.getFloat("average_mass");
			String statusOrder = rs.getString("status_order");
			int hour = rs.getInt("hour");
			int min = rs.getInt("min");
			String codUser = rs.getString("cod_user");
			String nickName = rs.getString("nick_name");
			java.sql.Date dateTime = rs.getDate("date_time");
			Date uDate = new Date(dateTime.getTime());

			OrderObject ord = new OrderObject(codOrder,code, name, price, averageMass,
					nickName, codUser, statusOrder,hour, min, uDate);
			list.add(ord);
		}
		return list;

	}
}