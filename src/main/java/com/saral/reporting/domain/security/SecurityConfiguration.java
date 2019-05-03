package com.saral.reporting.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationService authenticationService;

	private static final String[] PUBLIC_MATCHERS = { "/css/**", "/js/**", "/image/**", "/welcome", "/DesignReptPage",
			"/fetchReportList", "/DesignReport", "/DesignReportCol", "/fetchReportName", "/DesignAdminReptPage",
			"/fetchReportName/**", "/havingfunctionslist", "/viewSelectedReport/**", "/fetchReportsName/**",
			"/fetchReportList/**", "/reportExport", "/reportExportPDF", "/reportExportCSV", "/reportExportLocal",
			"/deleteSelectedReport", "/viewSelectedReportData/**", "/DesignAdminDeptReptPage",
			"/DesignReportFetchServices/**", "/fetchOrganizations/**", "/fetchApplInfoCol", "/fetchWhereConditions",
			"/fetchHavingConditions", "/SaveReport/**", "/DesignReptPage", "/file/**,/zip", "/havingfunctionslist",
			"/addHavingFunction", "/updateHavingFunctons/**", "/saveHavingFunctons/**", "/deleteHavingFunctons/**","/updateReportUserMaster/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).access("hasRole('ROLE_DHE')  or hasRole('ROLE_GO')  ")
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/welcome")

				.usernameParameter("sign_no").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/login?logout");

		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).access(
				"hasRole('ROLE_DHEAD') or hasRole('ROLE_SADMN') or hasRole('ROLE_CTZN') or hasRole('ROLE_SAPR') or hasRole('ROLE_SDEF') or hasRole('ROLE_CM') or hasRole('ROLE_DEO')  or hasRole('ROLE_DISP') or hasRole('ROLE_SCA') or hasRole('ROLE_SGA') or hasRole('ROLE_HRR')  or hasRole('ROLE_GOVT')  or hasRole('ROLE_CM') or hasRole('ROLE_ADMIN')")
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/welcome").failureUrl("/login?error")

				.usernameParameter("sign_no").passwordParameter("password");

		http.csrf().disable();

	}

	@Autowired
	public void GlobalConfigure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService);

	}

}
