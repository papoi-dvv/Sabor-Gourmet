package com.saborgourmet.config;

import com.saborgourmet.model.Usuario;
import com.saborgourmet.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.dev.reset-admin:false}")
    private boolean resetAdminOnStartup;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios.isEmpty()) {
                logger.warn("La tabla 'usuario' está vacía. Considera ejecutar data-database.sql o data-inicial.sql para crear usuarios de prueba (ver archivos /data-*.sql)");
                return;
            }

            logger.info("Usuarios encontrados en la base de datos:");
            for (Usuario u : usuarios) {
                String stored = u.getContrasena();
                boolean looksLikeBcrypt = stored != null && stored.startsWith("$2a$");
                boolean matchesPassword123 = false;
                boolean matchesPassword = false;
                try {
                    matchesPassword123 = passwordEncoder.matches("Password123", stored);
                    matchesPassword = passwordEncoder.matches("password", stored);
                } catch (Exception ex) {
                    // ignore
                }
                logger.info(" - {} (rol={}, estado={}, bcrypt={}, matches 'Password123'={}, matches 'password'={})",
                        u.getNombreUsuario(), u.getRol(), u.getEstado(), looksLikeBcrypt, matchesPassword123, matchesPassword);
            }

            // Consejo si admin existe pero no coincide
            if (resetAdminOnStartup) {
                // En modo desarrollo, reescribimos la contraseña de TODOS los usuarios al valor 'password'
                // Solo hacerlo en dev para evitar sobrescribir credenciales reales en producción.
                logger.warn("app.dev.reset-admin=true -> reescribiendo contraseñas de todos los usuarios a 'password' (modo dev)");
                for (Usuario u : usuarios) {
                    try {
                        boolean matchesLower = passwordEncoder.matches("password", u.getContrasena());
                        if (!matchesLower) {
                            u.setContrasena(passwordEncoder.encode("password"));
                            usuarioRepository.save(u);
                            logger.info(" - contraseña reescrita para usuario: {}", u.getNombreUsuario());
                        }
                    } catch (Exception ex) {
                        // Si hay algún formato extraño, reescribimos de todas formas
                        u.setContrasena(passwordEncoder.encode("password"));
                        usuarioRepository.save(u);
                        logger.info(" - contraseña reescrita (por excepción) para usuario: {}", u.getNombreUsuario());
                    }
                }
            } else {
                usuarioRepository.findByNombreUsuario("admin").ifPresent(admin -> {
                    boolean matches123 = false;
                    boolean matchesLower = false;
                    try { matches123 = passwordEncoder.matches("Password123", admin.getContrasena()); } catch (Exception ex) {}
                    try { matchesLower = passwordEncoder.matches("password", admin.getContrasena()); } catch (Exception ex) {}
                    if (!matches123 && !matchesLower) {
                        logger.warn("Usuario 'admin' existe pero no coincide con 'Password123' ni con 'password'.");
                        logger.warn("Para restablecerla automáticamente en arranque, establece la propiedad application 'app.dev.reset-admin=true' (solo en entorno de desarrollo).");
                    }
                });
            }
        } catch (Exception ex) {
            logger.error("Error revisando usuarios: ", ex);
        }
    }
}
