package com.sivalabs.bookmarker.security

import org.springframework.security.core.authority.SimpleGrantedAuthority

class SecurityUser(user: com.sivalabs.bookmarker.entity.User)
    : org.springframework.security.core.userdetails.User(
            user.email, user.password, listOf(SimpleGrantedAuthority(user.role.name)))
