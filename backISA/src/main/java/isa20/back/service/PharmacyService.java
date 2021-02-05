package isa20.back.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.bind.annotation.RequestBody;


import com.querydsl.core.types.Predicate;


import isa20.back.dto.request.ConsultingReservationRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.exception.AppException;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Consulting;
import isa20.back.model.ConsultingReservation;
import isa20.back.model.Dermatologist;
import isa20.back.model.Examination;
import isa20.back.model.Patient;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.repository.ConsultingRepo;
import isa20.back.repository.ConsultingReservationRepository;
import isa20.back.repository.DermatologistRepository;
import isa20.back.repository.ExaminationRepository;
import isa20.back.repository.PatientRepository;
import isa20.back.repository.PharmacistRepository;
import isa20.back.repository.PharmacyRepository;
import javassist.expr.NewArray;

@Service
public class PharmacyService
{
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	
	@Autowired
	PharmacistRepository pharmacistRepo;
	
	@Autowired
	ConsultingReservationRepository consultingReservationRepo;

	@Autowired
	ConsultingRepo consultingRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	DermatologistRepository dermatologistRepo;
	
	@Autowired
	ExaminationRepository examinationRepo;
	
	public ResponseEntity< List<Pharmacy> > getAllPharmacies() {
		
		
		List< Pharmacy > pharmacies = pharmacyRepo.findAll();
		
		if(pharmacies.isEmpty())
			throw new ResourceNotFoundException( "Pharmacy list is empty" );
		
		return  ResponseEntity.ok().body( pharmacies );
	}
	
	
	public  Iterable< Pharmacy > searchPharm(Predicate predicate ) {
		
		
	//	pharmacyRepo.findAll(PageRequest.of( page, size, Sort.by( "id" ).ascending()  ))
		
	//	pharmacyRepo.findAll( Sort.by( properties ). );
		
		return this.pharmacyRepo.findAll(predicate);
	}
	
	
	public List<Pharmacy> sortPharmacies(String propertie , String order ) {
		
		List<Pharmacy> sorted;
		
		
		
		if(order.equals( "asceding" )) 
			sorted = pharmacyRepo.findAll(Sort.by( propertie ).ascending());
		else
			sorted = pharmacyRepo.findAll( Sort.by( propertie ).descending() );
		
		return sorted;
	}
	
	
	
	public List<Pharmacy> getAvailablePharmacies( ConsultingReservationRequest request) {
		
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		//proveriti prvo da li pacijent tada ima zakazan pregled
		
		Patient patient = patientRepo.findById( userService.getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "user dont exist" ) );
		if(!patient.getConsultings().isEmpty())
			for(Consulting c : patient.getConsultings() ) {
				
				if(c.getStartTime().isBefore( request.getVreme() ) && c.getEndTime().isAfter( request.getVreme() ))
					throw new AppException( "You already have consulting at that time" );
					
				if(c.getStartTime().isBefore( request.getVreme().plusMinutes( 20 ) ) && c.getEndTime().isAfter( request.getVreme().plusMinutes( 20 ) ))
					throw new AppException( "You already have consulting at that time" );
			}
		
		
		//nadjemo sve koji su tada na odmoru
		List<Pharmacist> lista = this.pharmacistRepo.findAllByVacations_VacationFromLessThanEqualAndVacations_VacationToGreaterThanEqual( request.getVreme(),request.getVreme());
		
		if(lista.isEmpty()) {
			lista = pharmacistRepo.findAll();
		}
		else {
			//izbacimo sve koji su na odmoru
			
			List<Long> list = lista.stream().map( Pharmacist:: getId ).collect( Collectors.toList() );
			
			lista = this.pharmacistRepo.findAllByIdNotIn( list );
			
		}
		
		List<Pharmacist> found = new ArrayList< Pharmacist >();
		
		//proveri da li radno vreme
		for( Pharmacist pharm : lista) {
			if(request.getVreme().isBefore(  LocalDateTime.parse(request.getDate()+" "+pharm.getWorkingHoursFrom(), formatter) ) || request.getVreme().isAfter(  LocalDateTime.parse(request.getDate()+" "+pharm.getWorkingHoursTo(), formatter) ))
			{	
				found.add( pharm );
		
			}	
			
		}

		lista.removeAll( found );
		
		
		if(lista.isEmpty())
			throw new ResourceNotFoundException( "nemaa nistaaa" );
			
						
		//proveri da li neki od ovih ima zakazan pregled tada
	
		List<Consulting> savetovanja = consultingRepo.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual( request.getVreme(),request.getVreme() );
			
		if(!savetovanja.isEmpty()) {
			
			List<Long> consultingIds = new ArrayList< Long >();
			
			List<Pharmacist> found1 = new ArrayList< Pharmacist >();
			
			consultingIds = savetovanja.stream().map( Consulting:: getId ).collect( Collectors.toList() );
			
			found1 = pharmacistRepo.findByConsultingsIdIn( consultingIds );
			
			lista.removeAll( found1 );
		
		}
		
		
		
		//proveri da li ima pregled u narednih 20 minuta
		
		savetovanja = consultingRepo.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual( request.getVreme().plusMinutes( 20 ),request.getVreme().plusMinutes( 20 ));
		
	if(!savetovanja.isEmpty()) {
			
			List<Long> consultingIds = new ArrayList< Long >();
			
			List<Pharmacist> found1 = new ArrayList< Pharmacist >();
			
			consultingIds = savetovanja.stream().map( Consulting:: getId ).collect( Collectors.toList() );
			
			found1 = pharmacistRepo.findByConsultingsIdIn( consultingIds );
			
			lista.removeAll( found1 );
		
		}
		
	
		
			
		List<Pharmacy> apoteke = pharmacyRepo.findAllByPharmacistsIn( lista );
				
		return apoteke;
		
		
	}
		
	
	public List<Pharmacist> getAvailablePharmacists( ConsultingReservationRequest request) {
		
		
		Pharmacy pharmacy = pharmacyRepo.findById( request.getPharmacyId() ).orElseThrow( () -> new ResourceNotFoundException( "Pharmacy with this id doesnt exist" ) );
		
		List<Pharmacist> pharmacist = new ArrayList< Pharmacist >( pharmacy.getPharmacists());
		
		
		//nadjemo sve koji su na odmoru i izbacimo
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		List<Pharmacist> onVacation = this.pharmacistRepo.findAllByVacations_VacationFromLessThanEqualAndVacations_VacationToGreaterThanEqual( request.getVreme(),request.getVreme());
		
		if(!onVacation.isEmpty()) 
			pharmacist.removeAll( onVacation );
		
		//nadjemo sve koji tada ne rade i izbacimo 
		List<Pharmacist> found = new ArrayList< Pharmacist >();
		
		for( Pharmacist pharm : pharmacist) {
			if(request.getVreme().isBefore(  LocalDateTime.parse(request.getDate()+" "+pharm.getWorkingHoursFrom(), formatter) ) || request.getVreme().isAfter(  LocalDateTime.parse(request.getDate()+" "+pharm.getWorkingHoursTo(), formatter) ))
			{	
				found.add( pharm );
		
			}	
			
		}
		if(!found.isEmpty())
			pharmacist.removeAll( found );
		
		
		//izbaci ove sto tada imaju savetovanja
		List<Consulting> savetovanja = consultingRepo.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual( request.getVreme(),request.getVreme() );
		
		
		if(!savetovanja.isEmpty()) {
			
			List<Long> consultingIds = new ArrayList< Long >();
			
			List<Pharmacist> found1 = new ArrayList< Pharmacist >();
			
			consultingIds = savetovanja.stream().map( Consulting:: getId ).collect( Collectors.toList() );
			
			found1 = pharmacistRepo.findByConsultingsIdIn( consultingIds );
			
			pharmacist.removeAll( found1 );
		
		}
		
		
		//proveri da li ima pregled u narednih 20 minuta
		
		savetovanja = consultingRepo.findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual( request.getVreme().plusMinutes( 20 ),request.getVreme().plusMinutes( 20 ));
		
	if(!savetovanja.isEmpty()) {
			
			List<Long> consultingIds = new ArrayList< Long >();
			
			List<Pharmacist> found1 = new ArrayList< Pharmacist >();
			
			consultingIds = savetovanja.stream().map( Consulting:: getId ).collect( Collectors.toList() );
			
			found1 = pharmacistRepo.findByConsultingsIdIn( consultingIds );
			
			pharmacist.removeAll( found1 );
		
		}
		
		
		
		return pharmacist;
		
	}
		
	
	public ResponseEntity< ApiResponse > makeReservation( ConsultingReservationRequest request) {
		
		// vidi kako to sve povezati da li nam treba rezervacija da li nam treba samo savetovanje;
		
		Consulting consulting = new Consulting(request.getVreme() , request.getVreme().plusMinutes( 20 ));
		
		Patient patient = patientRepo.findById( userService.getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id doesnt exist" ) );
		
		Pharmacist pharmacist = pharmacistRepo.findById( request.getPharmacistId() ). orElseThrow( ()-> new ResourceNotFoundException( "Pharmacist with this id doesnt exist" ) );
		
		patient.getConsultings().add( consulting );
		
		pharmacist.getConsultings().add( consulting );
		
		consultingRepo.save( consulting );
		
		patientRepo.save( patient );
		
		pharmacistRepo.save( pharmacist );
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "Reservation made" ) , HttpStatus.OK);
		
	}
	
	
	public List<Examination> getAvailableExaminations(Long pharmacyId) {
		
		Pharmacy pharmacy = pharmacyRepo.findById(pharmacyId).orElseThrow( () -> new ResourceNotFoundException( "Pharmacy with this id doesn't exist" ) );
		
		System.out.println( "pronasao apoteku  : "  + pharmacy.getName());
		
		for (Dermatologist derm : pharmacy.getDermatologists())
			System.out.println( "ime: " + derm.getName() );
		
		List<Examination > examinations = examinationRepo.findByStatusEqualsAndDermatologistIn( 0, pharmacy.getDermatologists() );
		
		for(Examination exam : examinations)
			System.out.println( "exam id : " + exam.getId() );
		
		return examinations;
	}
	
	
	public List<Pharmacy> getSortedAvailablePharmacies(String propertie ,String order , ConsultingReservationRequest request) {
		
		List<Pharmacy> pharmacies = getAvailablePharmacies( request );
			
		List<Long> ids = pharmacies.stream().map( Pharmacy::getId ).collect( Collectors.toList() );
		
		if(order.equals( "ascending" )) {
			System.out.println( "ascENDINGGG" );
			pharmacies = pharmacyRepo.findAllByIdIn( ids , Sort.by( propertie ).ascending() );
		}	else {
			System.out.println( "descendinggggggggggggggggg" );
			pharmacies = pharmacyRepo.findAllByIdIn( ids , Sort.by( propertie ).descending() );
		}
		
		return pharmacies;
	}
	
}