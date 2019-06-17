package com.henu.paperadmin.utils;

import com.henu.paperadmin.secuity.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecuityUtils {
    public static CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
