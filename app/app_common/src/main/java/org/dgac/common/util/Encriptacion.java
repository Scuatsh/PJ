package org.dgac.common.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.dgac.common.constantes.ConstantesSistema;

// TODO: Auto-generated Javadoc
/**
 * The Class Encriptacion.
 */
public class Encriptacion 
{

	/** The base64 encrypted string. */
	private static String base64EncryptedString = "";

	/**
	 * Método que permite la encriptación de una cadena String a md5.
	 * @author Yosemar Andrade Reyes
	 * @param texto el parámetro texto define el texto a encriptar.
	 * @return  texto encriptado.
	 */
	public static String Encriptar(String texto) {

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(ConstantesSistema.LLAVE_ENCRIPTACION_MD5.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception error) {
			error.printStackTrace();
			System.out.println("Error Encode[Encriptar] causado por -->"+error.getMessage());
			return null;
		}
		return base64EncryptedString;
	}

	/**
	 * Método que permite la descriptor  de una cadena String a md5.
	 *
	 * @author Yosemar Andrade Reyes
	 * @param texto el parámetro texto define el texto a Desencriptar.
	 * @return  texto Desencriptado.
	 * @throws Exception the exception
	 */
	public static String Desencriptar(String texto) throws Exception {

		try {
			byte[] message = Base64.decodeBase64(texto.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(ConstantesSistema.LLAVE_ENCRIPTACION_MD5.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plainText = decipher.doFinal(message);
			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception error) 
		{
			error.printStackTrace();
			System.out.println("Error Encode[Desencriptar] causado por -->"+error.getMessage());
			return null;
		}
		return base64EncryptedString;
	}
	
	
	/**
	 * metodo main para pobrar encriptacion.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {

		String encriptado = Encriptar("juan chupalo");
		System.out.println("cadena encriptada --> "+encriptado);
		String desencriptado = null;
		try {
			desencriptado = Desencriptar(encriptado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("cadena desencriptada --> "+desencriptado);
	}

}
