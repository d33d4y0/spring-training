package com.github.d33d4y0.training.jpa.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.jpa.dto.CustomerDto;
import com.github.d33d4y0.training.jpa.entity.Address;
import com.github.d33d4y0.training.jpa.entity.CreditCardEntity;
import com.github.d33d4y0.training.jpa.entity.CustomerEntity;
import com.github.d33d4y0.training.jpa.entity.PhoneEntity;
import com.github.d33d4y0.training.jpa.entity.PromotionCodeEntity;
import com.github.d33d4y0.training.jpa.repository.CreditCardRepository;
import com.github.d33d4y0.training.jpa.repository.CustomerRepository;
import com.github.d33d4y0.training.jpa.repository.PhoneRepository;
import com.github.d33d4y0.training.jpa.repository.PromotionCodeRepository;
import com.github.d33d4y0.training.jpa.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CreditCardRepository cardRepo;
	@Autowired
	private PromotionCodeRepository promoRepo;
	@Autowired
	private PhoneRepository phoneRepo;

	@PostConstruct
	private void initCustomer() {
		customerRepo.deleteAll();
		cardRepo.deleteAll();
		promoRepo.deleteAll();
		phoneRepo.deleteAll();

		PromotionCodeEntity code1 = new PromotionCodeEntity("FIFTY", 50d);
		PromotionCodeEntity code2 = new PromotionCodeEntity("NEW_CUST", 100d);
		PromotionCodeEntity code3 = new PromotionCodeEntity("VIP", 200d);
		Iterable<PromotionCodeEntity> promos = promoRepo.saveAll(Arrays.asList(code1, code2, code3));
		Map<String, PromotionCodeEntity> promoMap = new HashMap<>();
		for (PromotionCodeEntity promo : promos) {
			promoMap.put(promo.getCode(), promo);
		}

		CustomerEntity customer1 = new CustomerEntity();
		customer1.setFirstName("D33d4y");
		customer1.setLastName("Jpa");
		customer1.setAddress(new Address("Thailand", "Bangkok", "Rama9", "10280"));
		customer1.setCreditCard(new CreditCardEntity(customer1.getFirstName() + " " + customer1.getLastName(),
				"4242424242424242", "123"));
		customer1.setPromoCodes(Arrays.asList(promoMap.get("FIFTY"), promoMap.get("NEW_CUST")));

		CustomerEntity customer2 = new CustomerEntity();
		customer2.setFirstName("Srping");
		customer2.setLastName("Jpa");
		customer2.setAddress(new Address("Thailand", "Bangkok", "Thapra", "10160"));
		customer2.setCreditCard(new CreditCardEntity(customer2.getFirstName() + " " + customer2.getLastName(),
				"1234567891234567", "456"));
		customer2.setPromoCodes(Arrays.asList(promoMap.get("NEW_CUST")));

		CustomerEntity customer3 = new CustomerEntity();
		customer3.setFirstName("Day");
		customer3.setLastName("Test");
		customer3.setAddress(new Address("Thailand", "Bangkok", "Thapra", "10160"));
		customer3.setCreditCard(new CreditCardEntity(customer3.getFirstName() + " " + customer3.getLastName(),
				"1111111111111111", "789"));
		customer3.setPromoCodes(Arrays.asList(promoMap.get("VIP")));
		customerRepo.saveAll(Arrays.asList(customer1, customer2, customer3));
		
		PhoneEntity phone1 = new PhoneEntity("+66982536544", customer1);
		PhoneEntity phone2 = new PhoneEntity("+66985231499", customer1);
		PhoneEntity phone3 = new PhoneEntity("+66684523215", customer2);
		PhoneEntity phone4 = new PhoneEntity("+66985256454", customer3);
		phoneRepo.saveAll(Arrays.asList(phone1,phone2,phone3,phone4));
	}

	@Override
	public List<CustomerDto> findByLastName(String lastName) {
		List<CustomerEntity> entities = customerRepo.findByLastName(lastName);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public CustomerDto findById(long id) {
		CustomerEntity entity = customerRepo.findById(id).orElse(new CustomerEntity());
		CustomerDto customer;
		if (entity != null) {
			customer = new CustomerDto(entity);
		} else {
			customer = new CustomerDto();
		}
		return customer;
	}

	@Override
	public List<CustomerDto> findByPostalCode(String postalCode) {
		List<CustomerEntity> entities = customerRepo.findByAddress_PostalCode(postalCode);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public CustomerDto addCustomer(CustomerDto customer) {
		CustomerEntity entity = customerRepo.save(new CustomerEntity(customer));
		if (entity != null) {
			customer = new CustomerDto(entity);
		} else {
			customer = new CustomerDto();
		}
		return customer;
	}

	@Override
	public List<CustomerDto> findByLastNameAndPostalCode(String lastName, String postalCode) {
		List<CustomerEntity> entities = customerRepo.findByLastNameAndAddress_PostalCode(lastName, postalCode);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> findByLastNameOrPostalCode(String lastName, String postalCode) {
		List<CustomerEntity> entities = customerRepo.findByLastNameOrAddress_PostalCode(lastName, postalCode);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> findPagableByLastNameOrPostalCode(String lastName, String postalCode, int page, int size) {
		return customerRepo.findPagableByLastNameOrAddress_PostalCode(lastName, postalCode, PageRequest.of(page, size))
				.map(each -> new CustomerDto(each)).toList();
	}

	@Override
	public List<CustomerDto> findAll(Integer page, Integer size, String sort) {
		Sort sortBy = sort == null ? Sort.unsorted() : Sort.by(sort);
		Pageable pageRequest = (page == null) || (size == null) ? Pageable.unpaged()
				: PageRequest.of(page, size, sortBy);
		return customerRepo.findAll(pageRequest).map(each -> new CustomerDto(each)).toList();
	}

	@Override
	public CustomerDto findFirstByLastName(String lastName) {
		return new CustomerDto(customerRepo.findFirstByLastName(lastName));
	}

	public List<CustomerDto> findByFirstNameEndWith(String firstName) {
		List<CustomerEntity> entities = customerRepo.findByFirstNameEndWith(firstName == null ? "" : firstName);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public List<CustomerDto> findByProvince(String province) {
		List<CustomerEntity> entities = customerRepo.findByAddress_Province(province);
		List<CustomerDto> dtos = new LinkedList<>();
		if (entities != null) {
			entities.forEach(each -> dtos.add(new CustomerDto(each)));
		}
		return dtos;
	}

	@Override
	public CreditCardEntity findByCardNumber(String card) {
		return cardRepo.findByCardNumber(card);
	}

}
