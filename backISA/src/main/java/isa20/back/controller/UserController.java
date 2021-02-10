package isa20.back.controller;

import isa20.back.dto.*;
import isa20.back.dto.request.LogInRequest;
import isa20.back.dto.request.RatingRequest;
import isa20.back.dto.request.SignUpRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.dto.response.ConsultingDTO;
import isa20.back.model.Consulting;
import isa20.back.model.Dermatologist;
import isa20.back.model.DrugReservation;
import isa20.back.model.Examination;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.model.User;
import isa20.back.repository.UserRepository;
import isa20.back.service.UserService;


import java.awt.print.Pageable;
import java.util.List;


import javax.validation.Valid;


import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
@RequestMapping("/api/userController")
public class UserController
{
	
	@Autowired
	private UserService UserService;

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/test")
	public ResponseEntity< ? > test () {
		
		return new ResponseEntity< String >( " keru ", HttpStatus.ACCEPTED );
	}
	
	
	@PostMapping("/test2")
	public void test2 (@RequestBody final String string) {
		
		System.out.println( " stigla je  :::: " + string );
	}
	
	
	
	@GetMapping("/getMyInfo")
	public ResponseEntity< User > getMyInfo() {
		return UserService.getMyInfo();
		
	}
	
	@PostMapping("/user")
	public ResponseEntity< ? > updateUserInfo(@RequestBody  @Valid final SignUpRequest request) {
		
		return UserService.updateUserInfo(request);
	}
	
	
	@GetMapping("/getMyReservations")
	public List< DrugReservation > getMyReservations() {
		
		return this.UserService.getMyReservations();
	}
	
	@GetMapping("/cancelReservation/{reservationId}")
	public ResponseEntity< ApiResponse > cancelReservation(@PathVariable long reservationId) {
		
		return this.UserService.cancelReservation(reservationId);
	}

	
	@GetMapping("/reservedConsultings")
	public List< ConsultingDTO > getMyConsultings() {
		
		return this.UserService.getMyReservedConsultings();
	}
	
	
	@GetMapping("/cancelConsulting/{consultingId}") 
	public ResponseEntity< ? > cancelConsulting(@PathVariable long consultingId) {
		
		
		return this.UserService.cancelConsulting(consultingId);
		
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity< ? > updatePassword(@RequestBody  @Valid final SignUpRequest request) {
		
		System.out.println( "POGODIO API" );
		
		return this.UserService.updatePassword(request);	
		
	}
	
	
	@GetMapping("/examinations/reservation/{examinationId}")
	public ResponseEntity< ? > makeExaminationReservation( @PathVariable Long examinationId) {
		
		return this.UserService.makeExamReservation(examinationId);
	}

	@GetMapping("/reservedExaminations")
	public List<Examination> getMyReservedExaminations() {
		
		return this.UserService.getMyReservedExaminations();
	}
	
	@GetMapping("/cancelExamination/{examinationId}")
	public ResponseEntity< ApiResponse > cancelExamination(@PathVariable Long examinationId) {
		
		return this.UserService.cancelExamination( examinationId );
				
	}
	
	
	@GetMapping("/consultings/finished")
	public List< ConsultingDTO > getMyFinishedConsultings() {
		
		return UserService.getMyConsultings();
	}
	
	
	@GetMapping("/getMyPharmacists")
	public List<Pharmacist> getMyPharmacists(){
		return this.UserService.getMyPharmacists();
	}
	
	@PostMapping("/ratePharmacist")
	public ResponseEntity< ApiResponse > ratePharmacist(@RequestBody RatingRequest request) {
		return this.UserService.ratePharmacist( request );
	}
	
	@PostMapping("/overrideRatePharmacist")
	public ResponseEntity< ApiResponse > overrideRatePharmacist(@RequestBody RatingRequest request) {
		return this.UserService.overrideRatePharmacist( request );
	}
	
	
	@GetMapping("/getMyDermatologists")
	public List<Dermatologist> getMyDermatologists(){
		return this.UserService.getMyDermatologists();
	}
	
	
	@PostMapping("/rateDermatologist")
	public ResponseEntity< ApiResponse > rateDerm(@RequestBody RatingRequest request) {
		return this.UserService.rateDermatologist( request );
	}
	
	@PostMapping("/overrideRateDermatologist")
	public ResponseEntity< ApiResponse > overrideRateDerm(@RequestBody RatingRequest request) {
		return this.UserService.overrideRateDermatologist( request );
	}
	
}
