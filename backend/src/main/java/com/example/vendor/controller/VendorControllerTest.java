@SpringBootTest
@AutoConfigureMockMvc
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllVendors() throws Exception {
        mockMvc.perform(get("/vendors"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVendorById() throws Exception {
        mockMvc.perform(get("/vendors/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateVendor() throws Exception {
        String body = "{\"name\":\"Test\",\"email\":\"test@test.com\"}";

        mockMvc.perform(post("/vendors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete("/vendors/1"))
                .andExpect(status().isOk());
    }
}