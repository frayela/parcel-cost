package com.example.springboot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;

@Configuration
@ComponentScan(basePackages={"com.example.*"})
@PropertySource("classpath:parcelCost.properties")
@AutoConfigureRestDocs(outputDir = "target/snippets")
@Controller
public class ParcelsController {

	@Value("${parcel.reject}")
	private int parcelReject;

	@Value("${parcel.heavy}")
	private int parcelHeavy;

	@Value("${parcel.small}")
	private int parcelSmall;

	@Value("${parcel.medium}")
	private int parcelMedium;

	@Value("${cost.heavy}")
	private String costHeavy;

	@Value("${cost.small}")
	private String costSmall;

	@Value("${cost.medium}")
	private String costMedium;

	@Value("${cost.large}")
	private String costLarge;

	@Value("${input.voucher.code}")
	private String iVoucher;

	@Value("${input.weight.cost}")
	private String iWeight;

	@Value("${input.height.volume}")
	private String iHeight;

	@Value("${input.width.volume}")
	private String iWidth;

	@Value("${input.length.volume}")
	private String iLength;

    @GetMapping("/parcels")
  	public String greetingForm(Model model) {
    	model.addAttribute("parcels", new Parcels());

    	return "parcels";
  	}

  	@PostMapping("/parcels")
  	public String greetingSubmit(@RequestParam Map<String, String> map, @ModelAttribute Parcels parcels, Model model) {
    	model.addAttribute("parcels", parcels);
    	
    	int weight = (map.get(iWeight) != null && map.get(iWeight) != "") ? Integer.parseInt(map.get(iWeight)) : 0;
		int height = (map.get(iHeight) != null && map.get(iHeight) != "") ? Integer.parseInt(map.get(iHeight)) : 0;
		int width = (map.get(iWidth) != null && map.get(iWidth) != "") ? Integer.parseInt(map.get(iWidth)) : 0;
		int length = (map.get(iLength) != null && map.get(iLength) != "") ? Integer.parseInt(map.get(iLength)) : 0;
		
		float weightCost = 0.0f;
		float discount = parcels.getDiscount();
		int vol = height * width * length;


		if (weight > parcelReject) {
			System.out.println("reject");
		} else if (weight > parcelHeavy) {
			weightCost = Float.parseFloat(costHeavy) * weight;
			if (vol >= parcelMedium) { // large parcel
				parcels.setCost((vol*Float.parseFloat(costLarge) + weightCost)-parcels.getDiscount());	
			} else if (vol < parcelSmall) { // small parcel
				parcels.setCost((vol*Float.parseFloat(costSmall) + weightCost)-parcels.getDiscount());
			} else if (vol < parcelMedium) { // medium parcel
				parcels.setCost((vol*Float.parseFloat(costMedium) + weightCost)-parcels.getDiscount());
			} 
			System.out.println("volume: "+vol+" heavy parcel "+parcels.getCost());	
		} else {
			
			if (vol >= parcelMedium) { // large parcel
				parcels.setCost((vol*Float.parseFloat(costLarge))-parcels.getDiscount());
				System.out.println("volume: "+vol+" large parcel "+parcels.getCost());	
			} else if (vol < parcelSmall) { // small parcel
				parcels.setCost((vol*Float.parseFloat(costSmall))-parcels.getDiscount());
				System.out.println("volume: "+vol+" small parcel "+parcels.getCost());
			} else if (vol < parcelMedium) { // medium parcel
				parcels.setCost((vol*Float.parseFloat(costMedium))-parcels.getDiscount());
				System.out.println("volume: "+vol+" medium parcel "+parcels.getCost());
			}
		}

    	return "parcels";
  	}
  
}
