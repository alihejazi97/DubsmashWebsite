package com.ali.controller;

import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import java.util.ArrayList;
import java.util.UUID;

public class TokenServiceImp implements TokenService {
    ArrayList<TokenImp> tokenStore = new ArrayList();

    @Override
    public Token allocateToken(String userId) {
        tokenStore.add(new TokenImp(userId));
        return tokenStore.get(-1);
    }

    @Override
    public Token verifyToken(String s) {
        for (TokenImp token : tokenStore){
            if (token.getKey().equals(s)){
                return token;
            }
        }
        return null;
    }
}

class TokenImp implements Token{

    String key;
    String userId;
    Long creationD;

    public TokenImp(String userId){
        this.userId = userId;
        key = UUID.randomUUID().toString();
        creationD = System.currentTimeMillis();
    }

    @Override
    public String getKey() {
        return UUID.randomUUID().toString();
    }

    @Override
    public long getKeyCreationTime() {
        return creationD;
    }

    @Override
    public String getExtendedInformation() {
        return userId;
    }
}
