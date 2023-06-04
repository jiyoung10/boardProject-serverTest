package com.example.ollethboardproject.configuration.auditor;

import com.example.ollethboardproject.domain.entity.Member;
import com.example.ollethboardproject.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
     //   log.info("auditing! {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(principal->ClassUtil.castingInstance(principal, Member.class).get())
                .map(Member::getNickName);
    }
}
