package za.co.absa.africanacity.AbsaBillingExtract.util.integration;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.absa.africanacity.AbsaBillingExtract.util.AbsaFileUploadUtil;

import javax.ws.rs.core.Response;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class AbsaFileUploadUtilIT {

    public static final String BASE_URL = "http://localhost:7555/file";
    private AbsaFileUploadUtil absaFileUploadUtil;


    @Before
    public void setUp() throws Exception {
        absaFileUploadUtil = new AbsaFileUploadUtil();
    }

    @Test
    public void testFileUpload() throws Exception {

        File file = new File(getClass().getClassLoader().getResource("test/sample.txt").getFile());

        log.info("To upload sample file " + file.getAbsoluteFile());
        Response result = absaFileUploadUtil.uploadFile(file.getAbsoluteFile(), BASE_URL);

        Assert.assertEquals(200, result.getStatus());
    }

    @After
    public void tearDown() throws Exception {
    }
}