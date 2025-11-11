package com.saborgourmet.aspect;

import com.saborgourmet.service.BitacoraService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Aspect
@Component
public class AuditoriaAspect {
    @Autowired(required = false)
    private BitacoraService bitacoraService;

    @After("execution(* com.saborgourmet.service.*Service.crearCliente(..)) || " +
           "execution(* com.saborgourmet.service.*Service.crearPedido(..)) || " +
           "execution(* com.saborgourmet.service.*Service.crearPlato(..)) || " +
           "execution(* com.saborgourmet.service.*Service.crearMesa(..))")
    public void auditarCreacion(JoinPoint joinPoint) {
        String accion = "CREAR";
        String tabla = extraerTabla(joinPoint);
        auditarAccion(accion, tabla, "Registro creado");
    }

    @After("execution(* com.saborgourmet.service.*Service.actualizar*(..)) || " +
           "execution(* com.saborgourmet.service.*Service.cambiarEstado(..))")
    public void auditarActualizacion(JoinPoint joinPoint) {
        String accion = "ACTUALIZAR";
        String tabla = extraerTabla(joinPoint);
        auditarAccion(accion, tabla, "Registro actualizado");
    }

    @After("execution(* com.saborgourmet.service.*Service.eliminar*(..))")
    public void auditarEliminacion(JoinPoint joinPoint) {
        String accion = "ELIMINAR";
        String tabla = extraerTabla(joinPoint);
        auditarAccion(accion, tabla, "Registro eliminado");
    }

    private void auditarAccion(String accion, String tabla, String descripcion) {
        if (bitacoraService != null) {
            try {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String usuario = auth != null ? auth.getName() : "ANONIMO";
                // Aquí se registraría en bitácora
                // bitacoraService.registrar(usuario, accion, tabla, null, descripcion);
            } catch (Exception e) {
                // Log error silenciosamente
            }
        }
    }

    private String extraerTabla(JoinPoint joinPoint) {
        String metodo = joinPoint.getSignature().getName();
        if (metodo.contains("Cliente")) return "CLIENTE";
        if (metodo.contains("Pedido")) return "PEDIDO";
        if (metodo.contains("Plato")) return "PLATO";
        if (metodo.contains("Mesa")) return "MESA";
        if (metodo.contains("Usuario")) return "USUARIO";
        return "GENERAL";
    }
}
