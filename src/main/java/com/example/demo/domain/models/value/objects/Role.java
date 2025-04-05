package com.example.demo.domain.models.value.objects;

import java.util.Objects;
public final class Role {
    private final Id id;
    private final String roleName;
    private final String roleDescription;
    private final boolean roleStatus;

    public Role(Id id, String roleName, String roleDescription, boolean roleStatus) {
        this.id = id;
        this.roleName = Objects.requireNonNull(roleName);
        this.roleDescription = roleDescription;
        this.roleStatus = roleStatus;
    }

    public Id getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public boolean isRoleStatus() {
        return roleStatus;
    }
}
