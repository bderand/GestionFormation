# GestionFormation

# package : com.intiFormation.couche (ex : com.intiFormation.service)

##### Couche Dao ###################
IClassDao -> ClassDao
  - new classDao;
  
##### Couche Service ###################
IClassService -> ClassService
  - new classService;

##### Couche Config ###################
Security 
IClassConfig -> ClassConfig
  - new classConfig;

 BCryptPasswordEncoder passEncode;
 UserDetailsService customUserService;
 AuthenticationManager authenticationManager;
 jwtUtil jwtokenUtil;
  
##### Couche Controller ###################
IClassController -> ClassController
  - new classController;
