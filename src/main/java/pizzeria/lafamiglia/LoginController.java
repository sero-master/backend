package pizzeria.lafamiglia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@RestController
@RequestMapping("/login")

public class LoginController {
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {

        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email AND u.pass1 = :password", Long.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        Long count = query.getSingleResult();

        if (count == 1) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}

