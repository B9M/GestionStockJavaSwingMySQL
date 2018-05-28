package Application;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {
	Connection cnx;
	Statement stm;
	String SQL;
	
	String url;
	String username;
	String password;
	Socket client;
	int port;
	String host;
	
	public BDD(String url, String username, String password, String Host,int port){
		this.url=url;
		this.username=username;
		this.password=password;
		this.host=host;
		this.port=port;
	}
	
	public Connection connexionOnDatabase(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cnx= DriverManager.getConnection(url, username, password);
		}
		catch(Exception e ){
			System.out.println(e.getMessage());
		}
		
		return cnx;
	}
	
	public Connection closeConnexion(){
		
		try {
			cnx.close();
		}
		catch(Exception e){
			
			System.err.println(e);;
		}
		
		return cnx;
	}
	
	public ResultSet executionQuery(String sql){
		
		connexionOnDatabase();
		ResultSet resultset = null;
		try {
			stm = cnx.createStatement();
			resultset=stm.executeQuery(sql);
			System.out.println(sql);
					
		}
		catch (SQLException ex) {System.err.println(ex);}
		
		return resultset;
	}
	
	public String executionOnUpdate(String sql){
		
		connexionOnDatabase();
		String result="";
		try {
			stm = cnx.createStatement();
			System.out.println(sql);
			stm.executeUpdate(sql);
			
			result=sql;
			System.out.println("aaaaaa"+result);
		}
	//	catch (SQLException ex) {result=ex.toString();}
		catch (SQLException ex) {System.err.println(ex);}
		
		return result;
	}
	
	public ResultSet querySelectAll(String nomTable){
		
		connexionOnDatabase();
		SQL = "SELECT * FROM "+nomTable;
		System.out.println(SQL);
		return this.executionQuery(SQL);
	}
	

	public ResultSet querySelectAll(String nomTable, String etat){
		
		connexionOnDatabase();
		SQL = "SELECT * FROM "+nomTable+" WHERE "+etat;
		System.out.println(SQL);
		return this.executionQuery(SQL);
	}

	public ResultSet querySelectAll(String[] nomColone, String nomTable){
		connexionOnDatabase();
		int i;
		SQL+= "SELECT";
		
		for (i=0; i<=nomColone.length-1;i++){
			SQL+=nomColone[i];
			if (i <  nomColone.length-1){ 
				SQL+= ",";
			}
		}
		SQL+=" FROM "+nomTable;
		return this.executionQuery(SQL);
	}
	
	public ResultSet querySelectAll(String[] nomColone, String nomTable,String etat){
		connexionOnDatabase();
		int i;
		SQL+= " SELECT ";
		
		for (i=0; i<=nomColone.length-1;i++){
			SQL+=nomColone[i];
			if (i <  nomColone.length-1){ 
				SQL+= ",";
			}
		}
		SQL+=" FROM "+nomTable+" WHERE "+etat;
		return this.executionQuery(SQL);
	}

	public ResultSet querySelect(String[] nomColone, String nomTable){
		connexionOnDatabase();
		int i;
		SQL=" SELECT ";
		for (i=0; i<=nomColone.length-1;i++){
			SQL+=nomColone[i];
			if (i<nomColone.length-1)
				SQL+=" , ";
		}
		SQL+=" FROM "+nomTable;
		return this.executionQuery(SQL);
	}

	public ResultSet fsSelectCommand(String[] nomColone, String nomTable,String etat){
		connexionOnDatabase();
		int i;
		SQL=" SELECT ";
		for (i=0; i<=nomColone.length-1;i++){
			SQL+=nomColone[i];
			if (i<nomColone.length-1)
				SQL+=" , ";
		}
		SQL+=" FROM "+nomTable+" WHERE "+etat;
		return this.executionQuery(SQL);
	}
	
	public String queryInsert(String nomTable, String[] contenuTable){
		connexionOnDatabase();
		int i;
		SQL="Insert into "+nomTable+" values (";
		for (i=0;i<contenuTable.length-1;i++){
		SQL+="'"+contenuTable[i]+"'";
		if (i<contenuTable.length-1){
			SQL+=",";
		}
		}
		
		SQL+=")";
		return this.executionOnUpdate(SQL);
		
	}
	
	public String queryInsert(String nomTable , String[] nomColone, String[] contenuTable){
		connexionOnDatabase();
		int i,j;
		SQL= "insert INTO "+nomTable+"(";
		
		for (i=0; i<= ((nomColone.length)-1);i++){
			SQL+=nomColone[i];
			if (i <  ((nomColone.length)-1)){ 
				SQL+= ",";
			}
		}
		SQL+=") VALUES(";
		for (j=0;j <= contenuTable.length-1;j++){
		SQL +="'"+contenuTable[j]+"'";
		if (j < (contenuTable.length-1))
			SQL +=",";
		}
		
		SQL+=")";
		return this.executionOnUpdate(SQL);
		
	}
	
	public String queryUpdate (String nomTable,String[] nomColone, String[] conteuTable, String etat){
		connexionOnDatabase();
		int i;
		SQL ="UPDATE " + nomTable + " SET ";
		
		for (i=0;i <= (nomColone.length-1);i++){
			SQL += nomColone[i] + "='" +conteuTable[i] + "'";
			if (i < (nomColone.length-1)){
				SQL+=",";
			}
			
		}
		SQL+=" WHERE "+etat;
		return this.executionOnUpdate(SQL);
	}
	
	public String queryDelete(String nomTable){
		connexionOnDatabase();
		SQL="DELETE FROM "+nomTable;
		return this.executionOnUpdate(SQL);
	}
	
	public String queryDelete(String nomTable,String etat){
		connexionOnDatabase();
		SQL="DELETE FROM "+nomTable+" WHERE "+etat;
		return this.executionOnUpdate(SQL);
	}
}

