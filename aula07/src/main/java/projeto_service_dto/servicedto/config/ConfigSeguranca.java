package projeto_service_dto.servicedto.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import projeto_service_dto.servicedto.security.JwtAuthenticationFilter;
import projeto_service_dto.servicedto.security.JwtAuthorizationFilter;
import projeto_service_dto.servicedto.security.JwtUtil;


@Configuration
@EnableWebSecurity
public class ConfigSeguranca {
	
	/*@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.anyRequest().authenticated())
		.httpBasic();
		return http.build(); // diz que todas as requisiçoes devem ser validadas
	}*/
	
/*	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // desabilita uma segurança, já que não precisamos por causa do stateless (evita erros também, erros de não passar header que o csrf pede) 
		.cors((cors) -> cors.configurationSource(corsConfigurationsource()))
		.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
			requests.requestMatchers(HttpMethod.GET, "/usuarios").permitAll(); // todos podem fazer essa requisição
			requests.requestMatchers(HttpMethod.GET, "/funcionarios").hasRole("RH").anyRequest().authenticated();//apenas usuarios de role RH podem fazer essa requisição e as demais
		}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //não guarda estado de sessão do usuário
		return http.build();
	}*/
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.cors((cors) -> cors.configurationSource(corsConfigurationsource()))
		.httpBasic(Customizer.withDefaults())
		.authorizeHttpRequests(authorize -> 
	        authorize
	            .requestMatchers(HttpMethod.GET, "/funcionarios").permitAll()
	            .requestMatchers(HttpMethod.GET, "/enderecos/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
	            .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAuthority("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/funcionarios/nome").hasAnyAuthority("ADMIN", "USER")
	            .anyRequest().authenticated()
	    )
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		JwtAuthenticationFilter jwtAuthenticationFilter = 
				new JwtAuthenticationFilter(authenticationManager(
						http.getSharedObject(AuthenticationConfiguration.class)),
						jwtUtil);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		JwtAuthorizationFilter jwtAuthorizationFilter = 
				new JwtAuthorizationFilter(authenticationManager(
						http.getSharedObject(AuthenticationConfiguration.class)), 
						jwtUtil, 
						userDetailsService);
		
		http.addFilter(jwtAuthenticationFilter);
		http.addFilter(jwtAuthorizationFilter);
		
		return http.build();
	}
	
	
/*	@Bean
	InMemoryUserDetailsManager userDetailService() { // autorização basic
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("teste")
				.password("123456")
				.roles("RH")
				.build();
		return new InMemoryUserDetailsManager(user); // criando um usuario personalizado (pode criar mais de um)
	}*/
	
	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	

	@Bean
	CorsConfigurationSource corsConfigurationsource() {
		CorsConfiguration corsConfiguraion = new CorsConfiguration();
		corsConfiguraion.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		corsConfiguraion.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguraion.applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() { // para criptografar senhas
		return new BCryptPasswordEncoder();
	}
	
}
