package EventManagementSystem.Participants;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "*")
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

    @DeleteMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable int id) {
        participantService.delete(id);
        return "Participant deleted successfully";
    }
    @PutMapping("/{id}")
    public Participant updateParticipant(@PathVariable int id, @RequestBody Map<String, Object> data) {
        Participant participant = participantService.readById(id);

        if (participant == null) {
            throw new RuntimeException("Participant not found with ID: " + id);
        }
        if (data.containsKey("firstName")) {
            participant.setFirstName((String) data.get("firstName"));
        }
        if (data.containsKey("lastName")) {
            participant.setLastName((String) data.get("lastName"));
        }
        if (data.containsKey("email")) {
            participant.setEmail((String) data.get("email"));
        }
        if (data.containsKey("age")) {
            participant.setAge((Integer) data.get("age"));
        }
        if(data.containsKey("tShirtSize")) {
            participant.setTShirtSize((String) data.get("tShirtSize"));
        }
        if(data.containsKey("gender")) {
            participant.setGender((String) data.get("gender"));
        }
        participantService.update(participant);
        return participant;
    }
}
