package com.form2bgames.megarisk.api.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientDatabase {
	public static final String userFile = "data/user/clients.jdb";
	private static ArrayList<Client> clients = new ArrayList<Client>();

	@SuppressWarnings("unchecked")
	public static void loadDB() {
		File f = new File(userFile);
		if (!f.exists()) // doesn't exist, so all the users are already in the
							// arrayList
			return;

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
		} catch (IOException e) {
		}

		try {
			clients = (ArrayList<Client>) ois.readObject();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
			throw new RuntimeException("Users could not be loaded.");
		}
		try {
			ois.close();
		} catch (IOException e) {
		}
	}

	public static void writeDB() {
		File f = new File(userFile);
		File rootDir = f.getParentFile();
		if (!rootDir.exists()) { // doesn't exist, create it
			try {
				if (!rootDir.createNewFile())
					throw new RuntimeException("users could not be saved");
			} catch (IOException e) {
				throw new RuntimeException("users could not be saved");
			}

		}
		if (!f.exists()) { // doesn't exist, create it
			try {
				if (!f.createNewFile())
					throw new RuntimeException("users could not be saved");
			} catch (IOException e) {
				throw new RuntimeException("users could not be saved");
			}

		} else {
			try {
				f.delete();
				if (!f.createNewFile())
					throw new RuntimeException("users could not be saved");
			} catch (IOException e) {
				throw new RuntimeException("users could not be saved");
			}
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
			oos.writeObject(clients);
		} catch (Exception e) {
			throw new RuntimeException("users could not be saved");
		}
	}

	public static boolean processVerify(String user, String auth) {

		for (Client c : clients) {
			if (c.getName().equals(user)) { // we have the correct username,
											// cannot be more than one
				if (c.isTokenEqual(auth))
					return true;
				else
					return false;
			}
		}

		return false;
	}

	public static boolean isClientNameAvailable(String unm) {

		for (Client c : clients) {
			if (c.getName().equals(unm))
				return false;
		}

		return true;
	}

	public static boolean addClient(Client c) {
		for (Client us : clients) {
			if (us.getName().equals(c.getName()))
				return false;
		}
		clients.add(c);
		return true;
	}

	public static Client getClientByName(String clName) {
		for (Client us : clients) {
			if (us.getName().equals(clName))
				return us;
		}
		return null;
	}

	public static boolean removeClient(Client c) {
		int n = JOptionPane.showConfirmDialog(null, "Remove Client?", "Confirm removing client",
				JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			clients.remove(c);
			return true;
		} else {
			return false;
		}
		
	}
}
