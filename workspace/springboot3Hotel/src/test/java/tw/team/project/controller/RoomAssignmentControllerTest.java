package tw.team.project.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tw.team.project.model.RoomAssignment;
import tw.team.project.service.RoomAssignmentService;


@SpringBootTest
@AutoConfigureMockMvc
public class RoomAssignmentControllerTest {

    @Mock
    private RoomAssignmentService roomAssignmentService;

    @InjectMocks
    private RoomAssignmentController roomAssignmentController;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFind() throws Exception {
        when(roomAssignmentService.find(any())).thenReturn(Collections.singletonList(new RoomAssignment()));

        mockMvc.perform(get("/pages/roomAssignment").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemove() throws Exception {
        when(roomAssignmentService.existById(any())).thenReturn(true);

        mockMvc.perform(delete("/pages/roomAssignment/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testModify() throws Exception {
        RoomAssignment roomAssignment = new RoomAssignment();
        roomAssignment.setId(1);

        when(roomAssignmentService.existById(any())).thenReturn(true);
        when(roomAssignmentService.update(any())).thenReturn(roomAssignment);

        mockMvc.perform(put("/pages/roomAssignment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomAssignment)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreate() throws Exception {
        RoomAssignment roomAssignment = new RoomAssignment();
        roomAssignment.setId(1);

        when(roomAssignmentService.existById(any())).thenReturn(false);
        when(roomAssignmentService.insert(any())).thenReturn(roomAssignment);

        mockMvc.perform(post("/pages/roomAssignment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomAssignment)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

//    @Test
//    public void testFindByDate() throws Exception {
//        RoomAssignment roomAssignment = new RoomAssignment();
//        roomAssignment.setId(1);
//
//        when(roomAssignmentService.findByDate(any())).thenReturn(roomAssignment);
//
//        mockMvc.perform(get("/pages/products/{date}", Date.valueOf("2022-04-11")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
}
