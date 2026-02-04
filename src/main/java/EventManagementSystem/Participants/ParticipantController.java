package EventManagementSystem.Participants;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantService.readAllParticipants();
    }

    @GetMapping("/id/{id}")
    public Participant getParticipantById(@PathVariable int id) {
        return participantService.readById(id);
    }

    @GetMapping("/email/{email}")
    public Participant getParticipantByEmail(@PathVariable String email) {
        return participantService.readByEmail(email);}
    @PostMapping
    public Participant createParticipant(@RequestBody Map<String, Object> data) {
        String firstName = (String) data.get("firstName");
        String lastName = (String) data.get("lastName");
        String email = (String) data.get("email");
        int age = (Integer) data.get("age");
        String gender = (String) data.get("gender");
        String tShirtSize = (String) data.get("tShirtSize");

        Participant participant = ParticipantFactory.createParticipant(
                firstName, lastName, age, email,gender, tShirtSize
        );
        participantService.create(participant);
        return participant;
    }

    @DeleteMapping("/{id}")
    public String deleteParticipant(@PathVariable int id) {
        participantService.delete(id);
        return "Participant deleted successfully";
    }
}
