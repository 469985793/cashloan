package com.xindaibao.cashloan.system.security.authentication.encoding;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.xindaibao.cashloan.core.common.util.crypto.Crypto;

/**
 * security安全处理：用户密码加密
 * @author
 */
public class PasswordEncoder extends BasePasswordEncoder {

    private Crypto     crypto;
    
    private SaltSource saltSource;

    private Object getSalt() {

        UserDetails user = null;
        if (SecurityContextHolder.getContext() != null
            && SecurityContextHolder.getContext().getAuthentication() != null
            && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        }

        return saltSource.getSalt(user);
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    public String encodePassword(String rawPass) throws DataAccessException {

        return encodePassword(rawPass, getSalt());
    }

    public String encodePassword(String rawPass, Object salt) throws DataAccessException {

        if (salt == null) {
            salt = getSalt();
        }
        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
        return crypto.encrypt(saltedPass);
    }

    public boolean isPasswordValid(String encPass, String rawPass) throws DataAccessException {

        return isPasswordValid(encPass, rawPass, getSalt());
    }

    public boolean isPasswordValid(String encPass, String rawPass, Object salt)
                                                                               throws DataAccessException {

        if (salt == null) {
            salt = getSalt();
        }
        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass, salt);

        return pass1.equals(pass2);
    }

}
