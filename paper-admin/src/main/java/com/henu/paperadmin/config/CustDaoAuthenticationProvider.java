/*
package com.henu.paperadmin.config;

import com.henu.paperadmin.utils.ToolUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class CustDaoAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if(authentication.getDetails() != null && authentication.getDetails() instanceof HashMap<?, ?>) {
            Map<String, String> map = (Map<String, String>) authentication.getDetails();
            //对密码进行RSA解密
            String pwd = authentication.getCredentials().toString();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            pwd = passwordEncoder.(pwd.getBytes());
            authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), pwd);
            ((AbstractAuthenticationToken) authentication).setDetails(map);
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
*/
