package com.example.emplyee_manager_sysetm.controler;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.emplyee_manager_sysetm.Dto.EmployeDtoName;
import com.example.emplyee_manager_sysetm.Dto.EmployeeDto;
import com.example.emplyee_manager_sysetm.Dto.EmployeeDtoEmail;
import com.example.emplyee_manager_sysetm.Dto.EmployeeFilesDto;
import com.example.emplyee_manager_sysetm.service.EmployerService;

import jakarta.validation.Valid;

@RestController
public class employeControle<EmplyeeEntity> {
	private final EmployerService service;

	public employeControle(EmployerService service) {
		this.service = service;
	}

	@GetMapping("/api/employee")
	public List<EmployeeDto> GetEmployes() {
		return service.getEmployees();
	}

	@GetMapping("/api/employe/name/{name}")
	public List<EmployeDtoName> getEmployeeName(@PathVariable String name) {
		return service.SearchFindByName(name);
	}

	@GetMapping("/api/employee/email/{email}")
	public EmployeeDtoEmail getEmployeeEmail(@PathVariable String email) {
		System.out.println("Controler Reciverd");
		return service.SearchFindByEmail(email);
	}

	@GetMapping("/api/employee/{id}")
	public EmployeeDto getSingleemp(@PathVariable long id) {
		return service.getSingleEmployeById(id);
	}

	@GetMapping("/api/employee/paging")
	public List<EmployeeDto> getpagnation(@RequestParam int page, @RequestParam int size) {
		return service.getPagenation(page, size);
	}

	@GetMapping("/api/employee/Sort/{sortName}")
	public List<EmployeeDto> getSortList(@PathVariable String sortName) {
		return service.getStoredr(sortName);
	}

	@GetMapping("/api/employee/sorting")
	public List<EmployeeDto> getPagaingSorting(@RequestParam int page, @RequestParam int size,
			@RequestParam String sort) {
		return service.getPagenationSorting(page, size, sort);
	}

	@PostMapping("/api/employee/user/creat")
	public void CreateUserAccount(@Valid @RequestBody EmployeeDto emp) {
		service.CreateUserEmployee(emp);
	}

	@PostMapping("/api/employee/admin/creat")
	public void CreateAdminAccount(@Valid @RequestBody EmployeeDto emp) {
		service.CreateAdminEmploye(emp);
	}

	@PostMapping("/api/upload")
	public EmployeeFilesDto UploadPdfFiles(@RequestParam MultipartFile file) throws IOException {
		return service.uploadFile(file);
	}

	@PostMapping("/api/upload/image")
	public EmployeeFilesDto UploadFileImage(@RequestParam MultipartFile file) throws IOException {
		return service.UploadFilesInImage(file);

	}

	@PutMapping("/api/employee/{id}")
	public void updateemploye(@PathVariable long id, @RequestBody EmployeeDto emp) {
		service.updateEmployee(id, emp);
	}

	@DeleteMapping("/api/employee/{id}")
	public void deleteEmploye(@PathVariable Long id) {
		service.deleteEmployeById(id);
	}
}
