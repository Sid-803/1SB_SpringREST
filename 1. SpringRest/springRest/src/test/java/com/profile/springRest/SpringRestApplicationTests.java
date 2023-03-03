package com.profile.springRest;

import com.profile.springRest.controller.InvoiceRestController;
import com.profile.springRest.entity.Invoice;
import com.profile.springRest.repo.InvoiceRepo;
import com.profile.springRest.service.InvoiceService;
import com.profile.springRest.service.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(InvoiceRestController.class)
class SpringRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvoiceService invoiceService;

	@Test
	public void shouldReturnList() throws Exception{
		this.mockMvc.perform(get("/api/invoices")).andDo(print()).andExpect(status().isOk());
	}
	@Autowired
	private InvoiceRestController invoiceRestController;

	@Test
	void contextLoads() {
		assertThat(invoiceRestController).isNotNull();
	}

}
