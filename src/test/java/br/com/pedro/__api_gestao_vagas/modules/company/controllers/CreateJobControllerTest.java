package br.com.pedro.__api_gestao_vagas.modules.company.controllers;

import br.com.pedro.__api_gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.pedro.__api_gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.pedro.__api_gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.pedro.__api_gestao_vagas.modules.utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should be able to create a job.")
    public void create_job() throws Exception {
        CompanyEntity company = CompanyEntity.builder()
                .description("company_description")
                .email("company@company.com")
                .password("1234567890")
                .username("company")
                .name("comapny")
                .build();

        company = this.companyRepository.saveAndFlush(company);

        CreateJobDTO createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.objectToJson(createJobDTO))
                        .header("Authorization", Utils.generateToken(company.getId(), "SODRAS123")))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Should not be able to create a job withou a registered company.")
    public void error_company_not_found() throws Exception {
        CreateJobDTO createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();


        mvc.perform(MockMvcRequestBuilders.post("/company/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.objectToJson(createJobDTO))
                .header("Authorization", Utils.generateToken(UUID.randomUUID(),
                        "SODRAS123")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Company not found."));
    }
}
