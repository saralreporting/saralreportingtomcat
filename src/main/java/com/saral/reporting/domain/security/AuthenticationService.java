package com.saral.reporting.domain.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saral.reporting.DAO.LoginDAO;
import com.saral.reporting.model.ReportUserMaster;
import com.saral.reporting.service.ReportUserMasterService;

@Service
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private LoginDAO userDAO;

	@Autowired
	ReportUserMasterService reportUserMasterService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ReportUserMaster reportUserMaster = reportUserMasterService.findBySignNO(username);

		if (reportUserMaster == null) {

			System.out.println("i am here");
			List<Object[]> values = userDAO.getUserInfo(username);

			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			if (values.size() > 0) {
				for (Object[] result : values) {
					String sign_role = (String) result[9];
					authorities.add(new SimpleGrantedAuthority("ROLE_" + sign_role));
				}

				System.out.println(authorities);
				Object[] l1 = values.get(0);
				UserDetails ud = new User((String) l1[0], (String) l1[10], authorities);
				return ud;

			}
			else {
				UserDetails ud = new User("abc1234","abc1234", authorities);
				return ud;
			}

		} else {
		System.out.println("2nd case our report");
			List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			List<String> roles = reportUserMaster.getRoleName();
			for(String l : roles){
				authorities.add(new SimpleGrantedAuthority("ROLE_" + l.toUpperCase()));
			}
			UserDetails ud = new User(reportUserMaster.getSignNO(), reportUserMaster.getSecuredPassword(), authorities);
			return ud;
		}
		

	}
}
