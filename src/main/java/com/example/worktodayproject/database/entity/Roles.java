package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "public")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Roles extends BaseEntity implements GrantedAuthority {

    @Column(name = "role")
    String role;
    @ManyToMany(mappedBy = "roles")
    Set<Users> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return role;
    }
}
