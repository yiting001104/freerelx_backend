package tw.team.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.RoomInformation;
import tw.team.project.service.RoomInformationService;


@RestController
@RequestMapping("/hotel")
public class RoomInformationController {

	@Autowired
	private RoomInformationService roomInformationService;

	@PostMapping("/roomInformation/findAll")
	public String findAll() throws JSONException {
		List<RoomInformation> roomInformation = roomInformationService.findAllRoomInformation();
		JSONArray array = new JSONArray();
		
		if (roomInformation != null && !roomInformation.isEmpty())
		for (RoomInformation person : roomInformation) {
			JSONObject item = new JSONObject()
					.put("room_information_id", person.getId())
					.put("room_type_id", person.getRoomType())
					.put("room_level_id", person.getRoomLevel())
					.put("bed_type", person.getBedType())
					.put("max_occupancy", person.getMaxOccupancy())
					.put("room_price", person.getPrice())
					.put("room_photo", person.getPhoto())
					.put("room_depiction", person.getDepiction());
			array.put(item);
		}
		return array.toString();
	}
	
	  @GetMapping("/roomInformation/{pk}")
	  public ResponseEntity<?> findRoomById(@PathVariable(name = "pk") Integer id) {
		  RoomInformation room = roomInformationService.findRoomById(id);
	  	if(room!=null) {
	  		ResponseEntity<RoomInformation> ok = ResponseEntity.ok(room);
	  		return ok;
	  	} else {
	  		ResponseEntity<Void> notfound = ResponseEntity.notFound().build();
	  		return notfound;
	  	}
	  }
	
	
	
	

	
	

//	@PostMapping("/RoomInformation/add")
//	public String postRoom(@RequestParam("roomType") String roomType, @RequestParam("level") String level,
//			@RequestParam("bedType") String bedType, @RequestParam("numberOfPeople") Integer numberOfPeople,
//			@RequestParam("price") Integer price, Model model) {
//		
//		RoomInformation room = new RoomInformation();
//		room.setRoomType(roomType);
//		room.setLevel(level);
//		room.setBedType(bedType);
//		room.setNumberOfPeople(numberOfPeople);
//		room.setPrice(price);
//		
//		RoomInformation afterSaveRoom = roomInformationService.saveRoom(room);
//		model.addAttribute("newRoomType", afterSaveRoom.getRoomType());
//		
//		return "roomInformation/addFinishPage";
//	}
//
//		 @GetMapping("/room-information/save")
//		 public RoomInformation saveTest(){
//			 RoomInformation room1 = new RoomInformation();
//			 room1.setRoomType("Stand Guestroom");
//			 room1.setLevel("Superior Room");
//			 room1.setBedType("Queen Bed");
//			 room1.setNumberOfPeople(2);
//			 room1.setPrice(12800);
//			 RoomInformation res = roomInformationRepository.save(room1);
//			 return res;
//		 }

//	@GetMapping("/RoomInformation/add")
//	public String addRoom() {
//		return "roomInformation/addPage";
//	}
//
//	@GetMapping("/RoomInformation/update")
//	public String updateFind(@RequestParam Integer id, Model model) {
//		RoomInformation room = roomService.findRoomById(id);
//
//		model.addAttribute("RoomInformationData", room);
//		return "roomInformation/editPage";
//	}
//
//	@PostMapping("/RoomInformation/update")
//	public String postUpdate(@ModelAttribute("RoomInformationData") RoomInformation room) {
//		roomService.updateRoom(room);
//
//		return "redirect:/RoomInformation/list";
//	}
//
//	@GetMapping("/RoomInformation/delete")
//	public String deleteRoom(@RequestParam Integer id) {
//		roomService.deleteRoomById(id);
//		return "redirect:/RoomInformation/list";
//	}

}