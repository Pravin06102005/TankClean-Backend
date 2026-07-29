package com.tankclean.TankClean.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("No authenticated user found");
        }
        return authentication.getName();
    }

    public static String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().isEmpty()) {
            throw new RuntimeException("No authenticated role found");
        }
        return authentication.getAuthorities().iterator().next().getAuthority();
    }

    public static boolean isAdmin() {
        return "ADMIN".equals(getCurrentUserRole());
    }
}
