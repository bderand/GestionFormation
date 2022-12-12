# GestionFormation

# package : com.intiFormation.<couche> (ex : com.intiFormation.service)

##### Couche Dao ###################
I<Class>Dao -> <Class>Dao
  - new <class>Dao;
  
##### Couche Service ###################
I<Class>Service -> <Class>Service
  - new <class>Service;

##### Couche Config ###################
Security 
I<Class>Config -> <Class>Config
  - new <class>Config;

 BCryptPasswordEncoder passEncode;
 UserDetailsService customUserService;
 AuthenticationManager authenticationManager;
 jwtUtil jwtokenUtil;
  
##### Couche Controller ###################
I<Class>Controller -> <Class>Controller
  - new <class>Controller;
