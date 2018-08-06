package cn.org.jcdp.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
        .withUser("bill").password("abc123").roles("ADMIN").and()
        .withUser("bob").password("abc123").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.anonymous().disable()
	  	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests().antMatchers("/oauth/token").permitAll();
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore){
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        };
    }
	
}