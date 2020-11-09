package com.wing.backend.forutonamanager.SecurityTestUtil;

import com.wing.backend.forutonamanager.RestDoc.RestDocsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@Transactional
public class TestBase {
    @Autowired
    protected MockMvc mockMvc;
}
