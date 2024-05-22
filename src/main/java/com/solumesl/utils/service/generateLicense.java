package com.solumesl.utils.service;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

import com.verhas.licensor.ExtendedLicense;

public class generateLicense {
	private final Logger log = LoggerFactory.getLogger(generateLicense.class);
	private String licenseId;
	private String licenseKey = "";
	private String stores;
	private String days;
	private String months;
	private String years;
	private String expirationDate = "";

	public generateLicense(String licenseId, String stores, String days, String months, String years) {

		log.info("New license request with License Id: {}, Stores: {}, Days: {}, Months: {}, Years: {}", licenseId, stores, days, months, years);

		try {

			// Create a new calendar to create calculate the expiryDate
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();

			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(days));	// Add days
			calendar.add(Calendar.MONTH, Integer.parseInt(months));			// Add months
			calendar.add(Calendar.YEAR, Integer.parseInt(years));			// Add years
			calendar.add(Calendar.DAY_OF_MONTH, 1); 						// Add one day

			// Prepare arguments for the license generator
			Date expiryDate = calendar.getTime();
			String serverType = "CENTRAL";
			UUID licenseUUId = UUID.fromString(licenseId);
			String useLayoutDesigner = "true";
			String paymentLicenseType = "PAYMENT";

			// Generate license
			String ringsPath = "";
			ExtendedLicense license = new ExtendedLicense();
			URL res = getClass().getClassLoader().getResource("./keypair/rings");
			if (Objects.isNull(res)) {
				ringsPath = "/app/keypair/rings";
			} else {
				File file = Paths.get(res.toURI()).toFile();
				ringsPath = file.getAbsolutePath();
			}

			license.setLicenseId(licenseUUId);
			license.setFeature("paymentLicenseType", paymentLicenseType);
			license.setFeature("serverType", serverType);
			license.setFeature("numberOfStroe", stores);
			license.setFeature("useLayoutDesigner", useLayoutDesigner);
			license.setExpiry(expiryDate);
			license.loadKey(ringsPath, "solum");

			// Get license key
			String licenseKey = license.encodeLicense("solum1!");
			this.licenseKey = Base64.getEncoder().encodeToString(licenseKey.getBytes());

			log.info("License with License Id: {} created!", licenseId);

			// Return expirationDate
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date printExpiryDate = DateUtils.addDays(expiryDate,-1);
			String expirationDate = simpleDateFormat.format(printExpiryDate);
			this.expirationDate = expirationDate;

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.licenseId = licenseId;
		this.stores = stores;
		this.days = days;
		this.years = years;
		this.months = months;

	}

	public String getLicenseId() {
		return licenseId;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public String getStores() {
		return stores;
	}

	public String getDays() {
		return days;
	}

	public String getmonths() {
		return months;
	}

	public String getyears() {
		return years;
	}

	public String getExpirationDate() {
		return expirationDate;
	}


}
