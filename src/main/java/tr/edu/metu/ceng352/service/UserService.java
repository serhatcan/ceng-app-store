package tr.edu.metu.ceng352.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.*;
import tr.edu.metu.ceng352.repository.AppRepository;
import tr.edu.metu.ceng352.repository.CategoryRepository;
import tr.edu.metu.ceng352.repository.OsVersionRepository;
import tr.edu.metu.ceng352.repository.UserRepository;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private OsVersionRepository osVersionRepository;

    @PostConstruct
    protected void initialize() {
        /*tr.edu.metu.ceng352.model.User user = userRepository.save(new tr.edu.metu.ceng352.model.User("Omer", "Can", "omer", "omer.can@gmail.com", "12312312317", "12345", Role.ROLE_DEVELOPER));
		Category category = categoryRepository.save(new Category("Eğitim", "Çılgın oyun kategorisi"));*/
        /*tr.edu.metu.ceng352.model.User user = userRepository.findByEmail("omer.can@gmail.com");
        Category category = categoryRepository.findById(1L);
        OsVersion osVersion = osVersionRepository.findById(2L);
        App app = new App("Facenote", "Note socially", 2.0 , user, category, osVersion);
        appRepository.save(app);*/
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
            For login email has used instead of username, but to use springs user details service
		    we did not changed the name of the function
		 */
        tr.edu.metu.ceng352.model.User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createUser(user);
    }

    public void signin(tr.edu.metu.ceng352.model.User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(tr.edu.metu.ceng352.model.User user) {
        return new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(createAuthority(user)));
    }

    private org.springframework.security.core.userdetails.User createUser(tr.edu.metu.ceng352.model.User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(tr.edu.metu.ceng352.model.User user) {
        return new SimpleGrantedAuthority(user.getRole().toString());
    }

    public tr.edu.metu.ceng352.model.User getCurrentUser() {
        tr.edu.metu.ceng352.model.User user = new tr.edu.metu.ceng352.model.User();
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(principal != null) {
            if(principal instanceof String) {
                user = userRepository.findByEmail((String) principal);
            } else if(principal instanceof UserDetails) {
                UserDetails ud = (UserDetails) principal;
                user = userRepository.findByEmail(ud.getUsername());
            }
            return user;
        }
        throw new UsernameNotFoundException("user not found");
    }

    public Double getCurrentUsersMoney(Long userId) {
        return userRepository.getCurrentMoney(userId);
    }

    public Boolean becomeDeveloper(Long userId) {
        userRepository.becomeDeveloper(userId);
        return true;
    }
}
