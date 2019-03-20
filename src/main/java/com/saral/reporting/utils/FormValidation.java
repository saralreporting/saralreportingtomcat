package com.saral.reporting.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.saral.reporting.model.ReportUserMaster;

public class FormValidation implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "^[a-z0-9@]{3,20}$";
	String MOBILE_PATTERN = "[0-9]{10}";
	String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

	@Override
	public void validate(Object target, Errors errors) {

		ReportUserMaster reportUserMaster = (ReportUserMaster) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "signNO", "required.signNO", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilenumber", "required.mobilenumber",
				"Phone is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation", "required.designation",
				"Designation is required.");
		// stateId
		// roleId
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userDetail", "required.userDetail",
				"User Detail is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateId", "required.stateId", "State is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "required.roleId", "Roles are required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retypePassword", "required.retypePassword",
				"Confirm Password is required.");
		// input string conatains characters only
		if (!(reportUserMaster.getSignNO() != null && reportUserMaster.getSignNO().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(String.valueOf(reportUserMaster.getSignNO()));
			if (!matcher.matches()) {
				errors.rejectValue("signNO", "signNO.containNonChar", "Enter a valid Username. User Name can have numbers characters and a special character @");
			}
		}

// email validation in spring    
		if (!(reportUserMaster.getEmail() != null && reportUserMaster.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(reportUserMaster.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect", "Enter a correct email");
			}
		}

// phone number validation    
		if (!(reportUserMaster.getMobilenumber() != null && reportUserMaster.getMobilenumber().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			System.out.println(reportUserMaster.getMobilenumber().getClass());
			matcher = pattern.matcher(reportUserMaster.getMobilenumber());
			if (!matcher.matches()) {
				errors.rejectValue("mobilenumber", "mobilenumber.incorrect", "Enter a correct phone number");
			}
		}

		// password validation
		if (!(reportUserMaster.getPassword() != null && reportUserMaster.getPassword().isEmpty())) {
			pattern = Pattern.compile(PASSWORD_PATTERN);

			matcher = pattern.matcher(reportUserMaster.getPassword());
			if (!matcher.matches()) {
				errors.rejectValue("password", "password.incorrect",
						"Password must be Minimum eight characters, at least one letter and one number");
			}
		}

		// password matching validation
		if ((!(reportUserMaster.getRetypePassword() != null && reportUserMaster.getRetypePassword().isEmpty()))
				&& (!(reportUserMaster.getPassword() != null
						&& reportUserMaster.getPassword().isEmpty()))) {
			if (!reportUserMaster.getPassword().equals(reportUserMaster.getRetypePassword())) {
				errors.rejectValue("retypePassword", "password.mismatch", "Password does not match");
			}
		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ReportUserMaster.class.isAssignableFrom(clazz);
	}
}