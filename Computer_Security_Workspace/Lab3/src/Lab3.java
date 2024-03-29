/**
 * @file fat32.c
 * @brief This file contains the main function and all functions for the Lab3
 * @author Sanskar Lamsal
 * @Subject Computer Security
 * @Assignment Lab 3
 * @Date 12/04/2023
 * @note This program is written in Java and tested in Eclipse IDE. It goes through the following tasks:
 * 			1. Decrypting Message
 * 			2. Cracking Hashed Salted Password
 * 			3. Factoring N64
 * 			4. Recovering Message
 * 			5. Elapsed Time
 * The program provides a space to enter variables and filename for the password+salt file.
 * It then prints the decrypted message, cracked password and salt, factors of N64, private key, recovered message and elapsed time. 
 */

// Importing Libraries
import java.math.BigInteger;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.security.MessageDigest;

/*
 * This class contains the main function and all functions for the Lab3
 */
public class Lab3 {
	/*
	 * This is the main function of the program
	 * @param args Unused
	 * @return Nothing
	 * @note This main function calls all the functions and prints the results
	 * It also prints the elapsed time
	 */
	public static void main(String[] args){
		long StartTime = System.currentTimeMillis();
		BigInteger N, N1, N2, N3, C1, C2, C3, c, d, e, password_hash, N64;
		c = new BigInteger("22e09cdd7ffa0ee3254194e2ada6449f4c89284fa0709f351267dacf40388ad94e7ff581196c3b89337503fb58c3d872714efe62a4098501d156da72a4cfd1dd5e06e48cb3bf95116a08a5e784f9f827b8de777ece2da7ea8225b50008e20c441228a75079798ad22e27c24de9d821c8ee7c72463fdc5f2d8795c72923d71f7d", 16);
		d = new BigInteger("5a9532507751e0a540e8bdbc610716356333f74269308d6b99fa66712b6b3b1cf3b88be9083ede0a294702c9929849e997ce37b3638a1a25feca6fe2a608d52a13eaa4dade827fa4641bea06d0b37ee4742fee4135581aaf9cdd70fc80049131383f2f7cc29dfbdd9f43b7971997fe2a4d512583a4f2e72781c0dbb55e5b9c01", 16);
		N = new BigInteger("ab74f9a758bdedf3f1e85480efefd8b2d1eeaff55b3b148e4029a4feb664212f315f1c2abca0b2765a81fb6400b93b29e40551e006220d9aeb89928d2ccd5515d1fa4d1694cb0961fa1b4bd126fc52ec354d9e9719984ee31913717baf7cfc6e09b9c6c5c28eddf9ce85a219e903e7cd992f618bdee886e15e4c1478772c8767", 16);
		e = new BigInteger("10001", 16);
		N1 = new BigInteger("a171f509ce3fe3829d3f1f7bb069b8aa8ae472720cfa4c3d3dbf76636cccaaf2a9ab35f9c3de6e62110fb155d3856ae3bef6e66659827bfa894fcf0f395157b773b0e15731e8e4982f05669551a6021ff110b0c5fd1d113e47e2e1a3c0f2dc2c5489323218aab0c0b0362adea37725999839d19a309d8ef43f3c356af8e21f09", 16);
		N2 = new BigInteger("a66dd710dc53a7d6c0201b52ed887ac1d841ec99391e0d200965408dca914d49db0e50e7f17f354cbfdf49e7c58049bb15133e3cd7ae38a869afbfa091dad7b06902b02ac9c38b18ff42e96e65e80c387c2f91ab9a8fe58e44d7e6a0333229cd0e8a72c183c6b15c00f1bd4111c319a2a2d2bba1d12941fcb67c2b939e4fd72d", 16);       
		N3 = new BigInteger("98f785d12ace759f0964657ae1ffcd121851773f1718d93d6ded058e332aa96e96bc5421a6e8a404247f524ae87a1ef50eecd31a76b2f448609863ba02ae73ed19c3b9bb9ec6e594b70858dca30bbd372f6d1bdb1040157959b12017dd2f11b3de1d8d7a8b35cbabc720919ebae7acbe279144cdf415738ce408fbd33093623d", 16);        
		C1 = new BigInteger("14ce6dee638ab29a5004e4b9c2cb596694655260b28a7931822fec32164e45a58e9d0b519dc27f1723cca61ace78c0196ba63239ab049889a6609dd388309b81e329dda8a5ef691e0db20b9a0710f394e3150da3e22e2b8cac4f6b640be4ed00b0af6c33c299bf5f3e68a81e40e12c27fa9035377c01fc317c91c21055871bfa", 16);
		C2 = new BigInteger("02db8a41a6fc157d8511ef40d11ab1f630225d8bec229ad0fc13818e28474adb2332edd4fd841c0f2c33b9629b8eee38022b184e9b8d5f26e7e29aa5064815f096995051ee61f6f262718d5a23bd099460d70f9487b169411f40dbe0368d8dd357c1ea50bf27a322270075eaa4ffac28ef30110a955d757cc7224f657e5eafcb", 16);
		C3 = new BigInteger("8f6dfa30424193268f6e666b1e869fecc20cd59d74e3c3d92852d272e00cd1eeb3d61bcb08f2fd8d3103b87c33e02f3cd1e6e47de5aa284f0c66917ee5424d117303cf442c41b0f8827af800d701a0f63caaae6d90e40952889b19526f7d5b35a679e94ebe597244c7551a756e03b27d62caa2e0d0f6a5668455e6120fd28759", 16);      	 	 
		password_hash = new BigInteger("29b3c5e99107ec4e77047083ba1dba917c61edd7b0a49f2951a9febccff333b0", 16);
		N64  = new BigInteger("cd6273a337f86d5", 16);

		// Decrypting Message
		System.out.println("Decrypting Message: " + decrypt_message(N, e, d, c));
		
		// Cracking Hashed Salted Password
		System.out.print("Cracking Hashed Salted Password: ");
		String[] passwords = crack_hashed_password(password_hash, "Top_Passwords.txt");
		if (passwords == null) System.out.println("No matching passwords found");
		else System.out.println("Password = " + passwords[0] + " and salt = " + passwords[1]);
		
		// Factoring N64 - Fermat's Factorization Method
		System.out.print("Factors of " + N64.toString() + ": ");
		BigInteger[] factors = get_factors(N64);
		if (factors == null) System.out.println("Unable to factor " + N64.toString());
		else System.out.println("Factor1: " + factors[0] + " Factor2: " + factors[1]);
		BigInteger PrivateKey = get_private_key_from_p_q_e(factors[0], factors[1], e);
		System.out.println("Private Key: " + PrivateKey.toString() + " (0x" + PrivateKey.toString(16) + ")");
		
		// Recovering Message - Chinese Remainder Theorem
		System.out.println("Recovered Message: " + recoverMessage(N1, N2, N3, C1, C2, C3));
		System.out.println("\nElapsed time: " + (System.currentTimeMillis() - StartTime) / 1000.0 + " seconds");
	}
	
	/*
	 * This function decrypts the message
	 * @param N The modulus
	 * @param e The public exponent
	 * @param d The private exponent
	 * @param c The ciphertext
	 * @return The decrypted message
	 */
	public static BigInteger decrypt_message(BigInteger N, BigInteger e, BigInteger d, BigInteger c) {
		return c.modPow(d, N); // c^d mod N
	}	
	
	/*
	 * This function cracks the hashed salted password
	 * @param PWHash The hashed password
	 * @param passwordList The filename of the password+salt file
	 * @return The cracked password and salt
	 * @note This program creates an ArrayList of passwords and salts from the file 
	 * and then compares the hashed password with the hashed password+salt. It then 
	 * returns the cracked password and salt.
	 */
	public static String[] crack_hashed_password(BigInteger PWHash, String passwordList) {
		
		ArrayList<String> PWSaltList = new ArrayList<String>();
		byte[] hashedPasswordBytes = PWHash.toByteArray();
		
		try{			
			File file = new File(passwordList);
			Scanner fileReader = new Scanner(file);
			while(fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				PWSaltList.add(data);
			}
			fileReader.close();
		}
		catch(Exception e){
			System.out.println("Error While Reading the File: " + e);
			return null;
		}
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String passwordSalt;
			byte[] hashedBytes;
			for(String password: PWSaltList) {
				for(String salt: PWSaltList){
					passwordSalt = password + salt;
					hashedBytes = md.digest(passwordSalt.getBytes());
					
			        if (MessageDigest.isEqual(hashedBytes, hashedPasswordBytes)) {
			        	String[] password_salt = {password, salt};
						return password_salt;
			        }
				}
			}
		}
		catch(Exception e) {
			System.out.println("Message Digest Error: " + e);
			return null;	
		}
		return null;	
	}
	
	/*
	 * This function factors N64
	 * @param N The modulus
	 * @return The factors of N64
	 * @note This program uses Fermat's factorization method to factor N64.
	 */
	public static BigInteger[] get_factors(BigInteger N){
		BigInteger factor1 = BigInteger.ZERO; 
		BigInteger factor2 = BigInteger.ZERO;		
		BigInteger sum = BigInteger.ONE; // sum = factor1^2 + N
		
		while(!(factor1.multiply(factor1)).equals(sum)){ // While factor1^2 + N != factor1^2
			factor2 = factor2.add(BigInteger.ONE); // factor2++
			sum = N.add(factor2.pow(2)); // sum = factor1^2 + N
			factor1 = sum.sqrt(); // factor1 = sqrt(factor1^2 + N)
		}
		return new BigInteger[]{factor1.add(factor2), factor1.subtract(factor2)}; // Return the factors
 	}
	
	/*
	 * This function finds the private key from p, q and e
	 * @param p The first factor of N64
	 * @param q The second factor of N64
	 * @param e The public exponent
	 * @return The private key
	 * @note This method uses the following formula
	 * 			d = e^-1 mod phi(N)
	 * 			phi(N) = (p-1)(q-1)
	 */
	 public static BigInteger get_private_key_from_p_q_e(BigInteger p, BigInteger q, BigInteger e){
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // phi(N) = (p-1)(q-1)
		return e.modInverse(phi); // d = e^-1 mod phi(N)
	}
	
	/*
	 * This function recovers the message
	 * @param N1, N2, N3 The modulus
	 * @param C1, C2, C3 The ciphertext
	 * @return The recovered message
	 * @note This method uses the Chinese Remainder Theorem to recover the message
	 * 			X = (C1 * Num_N1 * Inverse_Num_N1) + (C2 * Num_N2 * Inverse_Num_N2) + (C3 * Num_N3 * Inverse_Num_N3)
	 */
	public static String recoverMessage(BigInteger N1, BigInteger N2, BigInteger N3, BigInteger C1, BigInteger C2, BigInteger C3){
		String message = "";

		BigInteger ProductN = N1.multiply(N2).multiply(N3); // Product of N1, N2 and N3

		BigInteger Num_N1 = ProductN.divide(N1); 
		BigInteger Num_N2 = ProductN.divide(N2);
		BigInteger Num_N3 = ProductN.divide(N3);

		BigInteger Inverse_Num_N1 = Num_N1.modInverse(N1); // Inverse of Num_N1
		BigInteger Inverse_Num_N2 = Num_N2.modInverse(N2); // Inverse of Num_N2
		BigInteger Inverse_Num_N3 = Num_N3.modInverse(N3); // Inverse of Num_N3

		BigInteger X1 = (C1.multiply(Num_N1)).multiply(Inverse_Num_N1); // X1 = (C1 * Num_N1 * Inverse_Num_N1)
		BigInteger X2 = (C2.multiply(Num_N2)).multiply(Inverse_Num_N2);	// X2 = (C2 * Num_N2 * Inverse_Num_N2)
		BigInteger X3 = (C3.multiply(Num_N3)).multiply(Inverse_Num_N3); // X3 = (C3 * Num_N3 * Inverse_Num_N3)

		BigInteger X = (X1.add(X2).add(X3)).mod(ProductN); // X = X1 + X2 + X3

		BigInteger messageInt = findCubeRoot(X); // Finding the cube root of X
		byte[] messageBytes = messageInt.toByteArray(); // Converting the message to bytes
		message = new String(messageBytes); // Converting the bytes to string
		return message; // Return the message
	}

	/*
	 * This function finds the cube root of a BigInteger
	 * @param X The BigInteger
	 * @return The cube root of X
	 * @note This method uses binary search to find the cube root of X
	 * Binary search goes through the following steps:
	 * 		1. Calculate the midpoint
	 * 		2. Adjust the search range based on the comparison
	 * 		3. Repeat until the low and high are equal
	 * 		4. Return the low
	 */
	public static BigInteger findCubeRoot(BigInteger X) {
	    if (X.equals(BigInteger.ZERO)) // Cube root of 0 is 0
	        return BigInteger.ZERO;
	    else if (X.compareTo(BigInteger.ZERO) < 0) // Negative numbers don't have real cube roots
	        throw new IllegalArgumentException("Cube root is not defined for negative numbers");
	    BigInteger low = BigInteger.ZERO; // Lowest possible cube root
	    BigInteger high = X; // Highest possible cube root

	    while (low.compareTo(high) < 0) { // While low < high
	        BigInteger mid = low.add(high).divide(BigInteger.valueOf(2)); // Calculate the midpoint
	        BigInteger midCubed = mid.pow(3); // Calculate the cube of the midpoint
	        if (midCubed.compareTo(X) < 0) // If mid^3 < X
	            low = mid.add(BigInteger.ONE);
	        else  // If mid^3 > X
	            high = mid;
	    }
	    return low; // Return the low
	}
}