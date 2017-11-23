package com.dataart.service.security;

/**
 * Created by Роман on 29.10.2017.
 */;
public class UserDetailsServiceImpl{// implements UserDetailsService {

    /*@Autowired
    private UserDao userDao;

    //We will find the required user by his login
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //User user = userDao.findByUsername(username);

       // Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

       // for (Role role : user.getRoles()) {
       //     grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
       // }


        User user = userDao.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login=%s was not found", login)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
       // return new CurrentUser(user);
    }*/
}