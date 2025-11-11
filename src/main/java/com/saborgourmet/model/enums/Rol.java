package com.saborgourmet.model.enums;

public enum Rol {
    ADMIN("ROLE_ADMIN"),
    MOZO("ROLE_MOZO"),
    COCINERO("ROLE_COCINERO"),
    CAJERO("ROLE_CAJERO");

    private final String authority;

    Rol(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
