package com.springunivers.util;

import org.hsqldb.util.DatabaseManagerSwing;

public class StartHSQLDB {
	private static String URL = "file:/opendigytal/springunivers/database/springuniversdb";

	public static void main(String[] args) { // local();
		//server();
		local();
	}

	private static void local() {
		final String[] dbArgs = { "--user", "sa", "--password", "sa", "--url", "jdbc:hsqldb:" + URL };
		DatabaseManagerSwing.main(dbArgs);

	}

	private static void server() {
		final String[] dbArg = {"--database.0", URL, "--dbname.0", "springuniversdb;user=sa;password=sa", "--port", "5454" };
		org.hsqldb.server.Server.main(dbArg);
		
		//final String[] dbArgsServer = { "--url", "jdbc:hsqldb:hsql://localhost:5454/springuniversdb" };
		//DatabaseManagerSwing.main(dbArgsServer);
	}
}

