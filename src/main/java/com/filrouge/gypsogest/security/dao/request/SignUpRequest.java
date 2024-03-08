package com.filrouge.gypsogest.security.dao.request;

import com.filrouge.gypsogest.domain.enumeration.Role;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String username;
    private String password;
    private Role role;
}
