package ar.com.api.disneychallenge.disneychallenge;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ar.com.api.disneychallenge.disneychallenge.entities.Usuario;
import ar.com.api.disneychallenge.disneychallenge.security.Crypto;

@SpringBootTest
class DisneyApplicationTests {

	//@Test
	//void contextLoads() {
	//}

	@Test
	void testearEncriptacion() {

		String contraseñaImaginaria = "pitufosasesinos";
		String contraseñaImaginariaEncriptada = Crypto.encrypt(contraseñaImaginaria, "palabra");

		String contraseñaImaginariaEncriptadaDesencriptada = Crypto.decrypt(contraseñaImaginariaEncriptada, "palabra");

		// assertTrue(contraseñaImaginariaEncriptadaDesencriptada.equals(contraseñaImaginaria));
		assertEquals(contraseñaImaginariaEncriptadaDesencriptada, contraseñaImaginaria);
	}

	@Test
	void testearContraseña() {
		Usuario usuario = new Usuario();

		usuario.setUsername("Diana@gmail.com");
		usuario.setPassword("qp5TPhgUtIf7RDylefkIbw==");
		usuario.setEmail("Diana@gmail.com");

		assertFalse(!usuario.getPassword().equals(Crypto.encrypt("AbcdE23", usuario.getUsername().toLowerCase())));

	}

}