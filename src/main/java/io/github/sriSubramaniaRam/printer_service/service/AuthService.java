package io.github.sriSubramaniaRam.printer_service.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class AuthService {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private AuthService self; // proxy for saving OTP to cache

	public Mono<Boolean> sendOtpEmail(String toEmail) {
		String otp = generateOtp();
		self.saveOtp(toEmail, otp);
		String subject = "OTP for Nearby Printer Service";
		String body = "Your OTP code is: " + otp;

		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			messageHelper.setTo(toEmail);
			messageHelper.setSubject(subject);
			messageHelper.setText(body);
		};

		return Mono.fromRunnable(() -> mailSender.send(preparator))
				.doOnTerminate(() -> System.out.println("OTP sent to " + toEmail)).thenReturn(true)
				.onErrorReturn(false);
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = random.nextInt(999999);
		return String.format("%06d", otp);
	}

	@CachePut(key = "#email", value = "otps")
	public String saveOtp(String email, String otp) {
		return otp;
	}
}
