package com.sysmap.parrot.services.authentication;

import com.sysmap.parrot.services.authentication.dto.AuthenticateRequest;
import com.sysmap.parrot.services.authentication.dto.AuthenticateResponse;

public interface IAuthenticationService {
    public AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception;
}
