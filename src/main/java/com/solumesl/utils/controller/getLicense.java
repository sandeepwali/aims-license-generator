package com.solumesl.utils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solumesl.utils.service.generateLicense;

@Controller
public class getLicense {

	@GetMapping("/license")
	@ResponseBody
	public generateLicense getLicense(
			@RequestParam(name="licenseId", required=true) String licenseId,
			@RequestParam(name="stores", required=false, defaultValue="0") String stores,
			@RequestParam(name="days", required=false, defaultValue="0") String days,
			@RequestParam(name="months", required=false, defaultValue="0") String months,
			@RequestParam(name="years", required=false, defaultValue="0") String years
			) {
		return new generateLicense(licenseId, stores, days, months, years);
	}
}
