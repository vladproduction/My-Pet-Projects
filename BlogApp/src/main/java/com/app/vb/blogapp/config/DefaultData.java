package com.app.vb.blogapp.config;

import com.app.vb.blogapp.model.Account;
import com.app.vb.blogapp.model.Authority;
import com.app.vb.blogapp.model.Post;
import com.app.vb.blogapp.repository.AuthorityRepository;
import com.app.vb.blogapp.service.AccountService;
import com.app.vb.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Post> posts = postService.getAll();

        if(posts.isEmpty()){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user@gmail.com");
            account1.setPassword("passuser");
            Set<Authority> authoritiesUserRole = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authoritiesUserRole::add);
            account1.setAuthorities(authoritiesUserRole);

            Account account2 = new Account();
            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@gmail.com");
            account2.setPassword("passadmin");
            Set<Authority> authoritiesAdminRole = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authoritiesAdminRole::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authoritiesAdminRole::add);
            account2.setAuthorities(authoritiesAdminRole);

            //to be transient persisting account first
            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("Title of post-1");
            post1.setBody("Body of the post-1 is here!");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Title of post-2");
            post2.setBody("Body of the post-2 is here!");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);

        }
    }
}
